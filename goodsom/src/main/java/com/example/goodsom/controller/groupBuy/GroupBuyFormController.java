package com.example.goodsom.controller.groupBuy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_g;
import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.LikeService;

@Controller
@SessionAttributes("groupBuyForm")
@RequestMapping("/groupBuy")
public class GroupBuyFormController implements ApplicationContextAware {
	
	private static final String GROUPBUY_FORM = "groupBuy/groupBuy_form";
	private static final String GROUPBUY_DETAIL = "groupBuy/groupBuy_detail";
	private WebApplicationContext context;	
	private String uploadDir;

	@Override					// life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext)
		throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath("/resources/images/");
	}
	
	@Autowired
	private GroupBuyService groupBuyService;
	@Autowired
	private LikeService likeService;

	@ModelAttribute("groupBuyForm")
	public GroupBuyForm formBacking(HttpServletRequest request,
									@ModelAttribute("groupBuySession") LineGroupBuyForm groupBuySession,
									Model model) // 없으면 null?
			throws Exception {
		String reqPage = request.getServletPath();
		String groupBuyId = request.getParameter("groupBuyId");
		System.out.println("reqPage: " + reqPage + ", groupBuyId: " + groupBuyId);
//		list -> form : create
		if(groupBuyId == null) { 
			return new GroupBuyForm();	
//		detail -> form :  update or show(after create) GroupBuy
		} else {
			return new GroupBuyForm(groupBuyService.getGroupBuy(Integer.valueOf(groupBuyId)));
		}
	}
	
	@RequestMapping(value="/form.do")
	public String groupBuyForm(){
		return GROUPBUY_FORM;
	}
	
	// form -> detail : create & update
	@RequestMapping(value= {"/create.do", "/update.do"}, method=RequestMethod.POST)
	public String updateOrSubmit(HttpServletRequest request, @RequestParam("useExistingImage") String useExistingImage, 
			@Valid @ModelAttribute("groupBuyForm") GroupBuyForm groupBuyForm,
			BindingResult result, Model model, SessionStatus sessionStatus) {
		int groupBuyId;
		HttpSession session = request.getSession();
		UserSession user  = (UserSession)session.getAttribute("userSession");
		String reqPage = request.getServletPath();
		
		groupBuyForm.getGroupBuy().initGroupBuy(user.getUser());
		
//		이미지 validation
		if (useExistingImage.equals("no")) {
//			'기존 이미지 사용'체크박스 선택 안 했는데 파일 업로드로도 사진 선택 안 했을 시
			if (groupBuyForm.getGroupBuy().getReport().get(0).isEmpty()) {
				result.rejectValue("groupBuy.report", "notSelected");
			} else {
//				이미지 총 용량 validation (5MB이하만 가능하도록)
				List<MultipartFile> files = groupBuyForm.getGroupBuy().getReport();
				long totalSize = 0;
				for (MultipartFile file : files) {
					totalSize += file.getSize();
				}
				if (totalSize > 1024*1024*5) {
					result.rejectValue("groupBuy.report", "oversize");
				}
			}
			
		}

		if(result.hasErrors()) {
			if(reqPage.trim().equals("/groupBuy/update.do")) {
				model.addAttribute("groupBuyId", groupBuyForm.getGroupBuy().getGroupBuyId());
				return GROUPBUY_FORM;
			}else {
				return GROUPBUY_FORM;
			}
		}
		
//		시간 세팅
		groupBuyForm.getGroupBuy().timeSet();
//		time validation
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		if(groupBuyForm.getGroupBuy().getEndDate().before(today)) {
			result.rejectValue("groupBuy.endDate", "time");
		}
		if (result.hasErrors()) {
			if(reqPage.trim().equals("/groupBuy/update.do")) {
				model.addAttribute("groupBuyId", groupBuyForm.getGroupBuy().getGroupBuyId());
				return GROUPBUY_FORM;
			} else {
				return GROUPBUY_FORM;
			}
		}
		
//		이미지 파일이 저장될 경로
		String imagePath = request.getContextPath() + "/resources/images/";
		
		if (reqPage.trim().equals("/groupBuy/update.do")) { 	//		update
			GroupBuy oldGroupBuy = groupBuyService.getGroupBuy(groupBuyForm.getGroupBuy().getGroupBuyId());
			List<Image_g> groupBuyImgs = new ArrayList<Image_g>();
//			기존이미지 선택 x
			if (useExistingImage.equals("no")) {
//			기존 파일 삭제 후 파일 업로드
				System.out.println("공동구매 udpate를 위해 삭제할 이미지파일이 있는 uploadDir: " + uploadDir);
				for (Image_g oldGroupBuyImg : oldGroupBuy.getImgs_g()) {
					String[] oldFileName = oldGroupBuyImg.getUrl().split("/");	// /resources/images/사진이름
					for (int i = 0; i < oldFileName.length; i++) {
						System.out.println("oldFileName[" + i + "]: " + oldFileName[i]);
					}
					if (deleteFile(uploadDir + oldFileName[3])) {
						System.out.println("파일 삭제 성공! 이제부터 파일 업로드.");
					}
				}
//				파일 업로드 기능
				List<String> savedFileNames = uploadFile(groupBuyForm.getGroupBuy().getReport());
//				GroupBuy객체에 setImgs_g()
				int fileNo = 1;
				for (String savedFileName: savedFileNames) {
					groupBuyImgs.add(new Image_g(oldGroupBuy.getGroupBuyId(), fileNo++, imagePath + savedFileName));
				}
			
			} else { 
//				기존이미지 선택 O
				groupBuyImgs = oldGroupBuy.getImgs_g();
			}
//			db: groupBuy update & option 삭제 후, 다시 생성
			groupBuyId = groupBuyService.updateGroupBuy(groupBuyForm.getGroupBuy(), groupBuyImgs);
			groupBuyService.updateOptions(groupBuyForm.getGroupBuy());
			
			int likeCheck = likeService.likeCheckOfGroupBuyByUserId(user.getUser().getUserId(), groupBuyId);
			if (likeCheck == 1) {
				model.addAttribute("like", true);
			} else if (likeCheck == 0) {
				model.addAttribute("like", false);
			} else {
				System.out.println("[GroupBuyUpdate후]likeService.likeCheckOfGroupBuyByUserId()오류!");
				model.addAttribute("like", false);
			}
		} else { 	// create
//			파일 업로드 기능
			List<String> savedFileNames = uploadFile(groupBuyForm.getGroupBuy().getReport());
//			db: groupBuy create 후, id 받아오기
//			GroupBuy객체에 setImgs_g()
			List<Image_g> groupBuyImgs = new ArrayList<Image_g>();
			for (int i = 1; i <= savedFileNames.size(); i++) {
				groupBuyImgs.add(new Image_g(0, i, imagePath + savedFileNames.get(i-1)));
			}
			groupBuyService.createGroupBuy(groupBuyForm.getGroupBuy(), groupBuyImgs);
			groupBuyId = groupBuyForm.getGroupBuy().getGroupBuyId();
			
//			받아온 id와 option 파라미터를 Option객체에 세팅 후, create option
			groupBuyService.createOptions(groupBuyForm.getGroupBuy());
			model.addAttribute("like", false);
		}
//		스케줄러 => create / update 시 resultDate로 설정
		groupBuyService.deadLineScheduler();
		
//		detail에 필요한 파라미터 세팅
		GroupBuy groupBuy = groupBuyService.getGroupBuy(groupBuyId);
		if(user.getUser().getUserId() == groupBuy.getUserId()) {
			model.addAttribute("isWriter", true);
		}else {
			model.addAttribute("isWriter", false);
		}
//		좋아요 수
		groupBuy.setLikeCount(likeService.likeCheckOfGroupBuyByUserId(user.getUser().getUserId(), groupBuyId));
//		좋아요 기능에 필요
		model.addAttribute("loginUserId", user.getUser().getUserId());
		
		model.addAttribute("groupBuy", groupBuy);
		model.addAttribute("writer", user.getUser().getNickname());
		model.addAttribute("dDay", groupBuy.getDday(groupBuy.getEndDate().getTime()));
		
		// LineGroupBuyForm 값 지정
		LineGroupBuyForm lineGroupBuyForm = new LineGroupBuyForm();
		lineGroupBuyForm.setGroupBuyId(groupBuyId);
		lineGroupBuyForm.setGroupBuy(groupBuy);
		session.setAttribute("lineGroupBuyForm", lineGroupBuyForm);
		model.addAttribute("lineGroupBuyForm", lineGroupBuyForm);
		
		sessionStatus.setComplete();
		
		return GROUPBUY_DETAIL;
	}
		
//	파일명 랜덤생성 메서드
	private List<String> uploadFile(List<MultipartFile> reports) {
		List<String> savedNames = new ArrayList<String>();
		for (MultipartFile report: reports) {
			/* 서버에 파일 업로드*/
//		uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
			UUID uuid = UUID.randomUUID();
//		랜덤생성 + 파일이름 저장
//		String savedName = uuid.toString() + "_" + report.getOriginalFilename();
			String savedName = "Goodsom_groupBuy_" + uuid.toString();
//		임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
			File file = new File(uploadDir + savedName);
			try {
				report.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			savedNames.add(savedName);
		}
		return savedNames;
			
	}
	
//	파일명 삭제 메서드
	private boolean deleteFile(String oldFileSavedName) {
//		서버에 저장된 업로드된 파일을 삭제
		boolean result = new File(oldFileSavedName).delete();
		return result;
	}
	
	@ModelAttribute("hourData")
	protected List<Hour> referenceData1() throws Exception {
		List<Hour> hour = new ArrayList<Hour>();
		for (int i = 0; i <= 23; i++) {
			hour.add(new Hour(i, i));			
		}
		return hour;
	}
	
	@ModelAttribute("minuteData")
	protected List<Minute> referenceData2() throws Exception {
		List<Minute> minute = new ArrayList<Minute>();
		for (int i = 0; i <= 59; i++) {
			minute.add(new Minute(i, i));		
		}
		return minute;
	}
}
