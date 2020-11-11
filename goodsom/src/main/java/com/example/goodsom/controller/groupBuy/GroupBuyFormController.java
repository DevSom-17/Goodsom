package com.example.goodsom.controller.groupBuy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.example.goodsom.controller.groupBuy.Hour;
import com.example.goodsom.controller.groupBuy.Minute;
import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.GroupBuyService;

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
	public String updateOrSubmit(HttpServletRequest request,
			@Valid @ModelAttribute("groupBuyForm") GroupBuyForm groupBuyForm,
			BindingResult result, Model model, SessionStatus sessionStatus) {
		int groupBuyId;
		HttpSession session = request.getSession();
		UserSession user  = (UserSession)session.getAttribute("userSession");
		String reqPage = request.getServletPath();
		
		groupBuyForm.getGroupBuy().initGroupBuy(user.getUser());
		
//		대표 이미지 선택 안 했을 시
		System.out.println("groupBuy.getReport: " + groupBuyForm.getGroupBuy().getReport().getSize());
		if (groupBuyForm.getGroupBuy().getReport().getSize() == 0) {
			result.rejectValue("groupBuy.report", "notSelected");
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
		
		if (reqPage.trim().equals("/groupBuy/update.do")) { 	//		update
//			기존 파일 삭제 후 파일 업로드
			GroupBuy oldGroupBuy = groupBuyService.getGroupBuy(groupBuyForm.getGroupBuy().getGroupBuyId());
			String[] oldFileName = oldGroupBuy.getImg().split("/");
			if (deleteFile(uploadDir + oldFileName[4])) {
				System.out.println("파일 삭제 성공! 이제부터 파일 업로드.");
			}
//			파일 업로드 기능
			String savedFileName = uploadFile(groupBuyForm.getGroupBuy().getReport());
			groupBuyForm.getGroupBuy().setImg(request.getContextPath() + "/resources/images/"+ savedFileName);
//			db: groupBuy update & option 삭제 후, 다시 생성
			groupBuyId = groupBuyService.updateGroupBuy(groupBuyForm.getGroupBuy());
			groupBuyService.updateOptions(groupBuyForm.getGroupBuy());
		} else { 	// create
//			파일 업로드 기능
			String savedFileName = uploadFile(groupBuyForm.getGroupBuy().getReport());
			groupBuyForm.getGroupBuy().setImg(request.getContextPath() + "/resources/images/"+ savedFileName);	
//			db: groupBuy create 후, id 받아오기
			groupBuyService.createGroupBuy(groupBuyForm.getGroupBuy());
			groupBuyId = groupBuyForm.getGroupBuy().getGroupBuyId();
			
//			받아온 id와 option 파라미터를 Option객체에 세팅 후, create option
			groupBuyService.createOptions(groupBuyForm.getGroupBuy());
		}
//		스케줄러 => create / update 시 resultDate로 설정
		groupBuyService.deadLineScheduler(groupBuyForm.getGroupBuy().getResultDate(), groupBuyId);
		
//		detail에 필요한 파라미터 세팅
		GroupBuy groupBuy = groupBuyService.getGroupBuy(groupBuyId);
		if(user.getUser().getUserId() == groupBuy.getUserId()) {
			model.addAttribute("isWriter", true);
		}else {
			model.addAttribute("isWriter", false);
		}
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
	private String uploadFile(MultipartFile report) {
//		uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
		UUID uuid = UUID.randomUUID();
//		랜덤생성 + 파일이름 저장
		String savedName = uuid.toString() + "_" + report.getOriginalFilename();
//		임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		File file = new File(uploadDir + savedName);
		try {
			report.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return savedName;
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
		for (int i = 1; i <= 12; i++) {
			hour.add(new Hour(i, i+"시"));			
		}
		return hour;
	}
	
	@ModelAttribute("minuteData")
	protected List<Minute> referenceData2() throws Exception {
		List<Minute> minute = new ArrayList<Minute>();
		minute.add(new Minute(00, "00분"));
		minute.add(new Minute(10, "10분"));
		minute.add(new Minute(20, "20분"));
		minute.add(new Minute(30, "30분"));
		minute.add(new Minute(40, "40분"));
		minute.add(new Minute(50, "50분"));
		return minute;
	}
	
	@ModelAttribute("amPm")
	protected List<String> referenceData3() throws Exception {
		List<String> amPm = new ArrayList<String>();
		amPm.add("am");
		amPm.add("pm");
		return amPm;
	}
}
