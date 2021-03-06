package com.example.goodsom.controller.auction;

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
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Auction;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.service.AuctionService;
import com.example.goodsom.service.LikeService;

/**
 * @author Hyekyung Kim | Yejin Lee 
 * @since 2020.05.08	| 2020.06.13
 */

@Controller
@SessionAttributes("auctionForm")
@RequestMapping("/auction/*.do")
public class AuctionFormController implements ApplicationContextAware  {
	
//	request handler가 보내줄 view이름 지정
	private static final String AUCTION_FORM = "auction/auction_form";
	private static final String AUCTION_DETAIL = "auction/auction_detail";
	private static final String AUCTION_LIST = "auction/auction_list";
//	파일 업로드 위한 변수
	private WebApplicationContext context;	
	private String uploadDir;
//	Service 객체
	@Autowired
	private AuctionService auctionService;
	@Autowired
	private LikeService likeService;

	@Override					// life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext)
		throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath("/resources/images/");
	}

	@ModelAttribute("auctionForm")
	public AuctionForm formBacking(HttpServletRequest request, Model model, SessionStatus sessionStatus) throws Exception{
		String reqPage = request.getServletPath();
		System.out.println(reqPage);
		String auctionId = request.getParameter("auctionId");
		if(auctionId == null) { //create: '/auction/form.do' 로 들어옴
			return new AuctionForm();
		} else { // update: '/auction/form.do?auctionId=' 로 들어옴
			Auction auction = auctionService.getAuction(Integer.valueOf(auctionId));
			System.out.println("수정 전 auction 객체: " + auction.toString());
			return new AuctionForm(auction);
		}
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showAuctionForm(@ModelAttribute("auctionForm") AuctionForm auctionForm) {
		return AUCTION_FORM;
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(HttpServletRequest request, @RequestParam("useExistingImage") String useExistingImage, 
			@Valid @ModelAttribute("auctionForm") AuctionForm auctionForm, BindingResult result,
			Model model, SessionStatus sessionStatus, HttpSession session) {
//		/auction/create.do인지 /auction/update.do인지 구분하기 위해 필요!
		String reqPage = request.getServletPath();
		String requestUrl = reqPage.trim();
		System.out.println("check여부: " + useExistingImage);
//		이미지 validation
		if (useExistingImage.equals("no")) {
//			'기존 이미지 사용'체크박스 선택 안 했는데 파일 업로드로도 사진 선택 안 했을 시
			if (auctionForm.getAuction().getReport().get(0).isEmpty()) {
				result.rejectValue("auction.report", "notSelected");
			} else {
//				이미지 총 용량 validation (5MB이하만 가능하도록)
				List<MultipartFile> files = auctionForm.getAuction().getReport();
				long totalSize = 0;
				for (MultipartFile file : files) {
					totalSize += file.getSize();
				}
				if (totalSize > 1024*1024*5) {
					result.rejectValue("auction.report", "oversize");
				}
			}
		}
//		AuctionForm객체 validation
		if (result.hasErrors()) {
			if (requestUrl.equals("/auction/update.do")) {
				model.addAttribute("auctionId", auctionForm.getAuction().getAuctionId());
				return AUCTION_FORM;
			} else {
				return AUCTION_FORM;
			}
		}
		
//		경매 create시 작성자 번호(userId)를 넣어야하고, view에서 작성자를 출력해야 하므로 현재 접속 중인 사용자의 정보를 Session에서 가져온다.
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		System.out.println(user.toString());
		
//		시간세팅
		auctionForm.getAuction().timeSet();
//		time validation
		Calendar calendar = Calendar.getInstance(); 
		Date today = calendar.getTime();
		if(auctionForm.getAuction().getEndDate().before(today)) {
			result.rejectValue("auction.endDate", "time");
		}
		if (result.hasErrors()) {
			if (requestUrl.equals("/auction/update.do")) {
				model.addAttribute("auctionId", auctionForm.getAuction().getAuctionId());
				return AUCTION_FORM;
			} else {
				return AUCTION_FORM;
			}
		}
		
//		이미지 파일이 저장될 경로
		String imagePath = request.getContextPath() + "/resources/images/";
		System.out.println("이미지 파일이 저장될 경로인 imagePath: " + imagePath);
		
//		경매 update/create 작업
		if (requestUrl.equals("/auction/update.do")) { // update
			Auction oldAuction = auctionService.getAuction(auctionForm.getAuction().getAuctionId());
			List<Image_a> auctionImgs = new ArrayList<Image_a>();
//			기존이미지 선택하지 않았을 경우
			if (useExistingImage.equals("no")) {
//				기존 파일 삭제 후 파일 업로드
				System.out.println("경매 udpate를 위해 삭제할 이미지파일이 있는 uploadDir: " + uploadDir);
				for (Image_a oldAuctionImg : oldAuction.getImgs_a()) {
					String[] oldFileName = oldAuctionImg.getUrl().split("/");	// /resources/images/사진이름
					for (int i = 0; i < oldFileName.length; i++) {
						System.out.println("oldFileName[" + i + "]: " + oldFileName[i]);
					}
					if (deleteFile(uploadDir + oldFileName[3])) {
						System.out.println("파일 삭제 성공! 이제부터 파일 업로드.");
					}
				}
//				서버에 파일 업로드 기능
				List<String> savedFileNames = uploadFile(auctionForm.getAuction().getReport());
				System.out.println(auctionForm.getAuction().toString());
				int fileNo = 1;
				for (String savedFileName: savedFileNames){
					auctionImgs.add(new Image_a(auctionForm.getAuction().getAuctionId(), fileNo++, imagePath + savedFileName));
				}
			} else { 
//				기존이미지 선택했을 경우
				auctionImgs = oldAuction.getImgs_a();
			}
			auctionForm.getAuction().setState("proceeding");
			int auctionId = auctionService.updateAuction(auctionForm.getAuction(), auctionImgs);

			Auction auction = auctionService.getAuction(auctionId);
//			해당 경매의 좋아요 수
			auction.setLikeCount(likeService.getLikeCountOfAuction(auctionId));
			model.addAttribute("auction", auction);
//			auction_detail.jsp에서 좋아요를 눌렀는지 여부 확인 위한 파라미터: like
			int likeCheck = likeService.likeCheckOfAuctionByUserId(user.getUser().getUserId(), auctionId);
			if (likeCheck == 1) {
				model.addAttribute("like", true);
			} else if (likeCheck == 0) {
				model.addAttribute("like", false);
			} else {
				System.out.println("[AuctionUpdate후]likeService.likeCheckOfAuctionByUserId()오류!");
				model.addAttribute("like", false);
			}
			model.addAttribute("dDay", auction.getDday(auction.getEndDate().getTime()));
		} else { // create
//			파일 업로드 기능
			List<String> savedFileNames = uploadFile(auctionForm.getAuction().getReport());
//			Auction객체에 setImgs_a()
			List<Image_a> auctionImgs = new ArrayList<Image_a>();
			for (int i = 1; i <= savedFileNames.size(); i++) {
				auctionImgs.add(new Image_a(0, i, imagePath + savedFileNames.get(i-1)));
			}
            auctionForm.getAuction().initAuction(user.getUser());
			System.out.println("[AuctionFormController] auctionForm 값: " + auctionForm.toString());
			auctionService.createAuction(auctionForm.getAuction(), auctionImgs);

//			auction_detail.jsp에서 좋아요를 눌렀는지 여부 확인 위한 파라미터
			model.addAttribute("like", false);
			
			Auction auction = auctionForm.getAuction();
			model.addAttribute("auction", auction);
			model.addAttribute("dDay", auction.getDday(auction.getEndDate().getTime()));
		}
		
//		스케줄러 => create / update 시 endDate로 설정
		auctionService.deadLineScheduler();
		
//		view에 전송할 attribute
		session.setAttribute("bidForm", new BidForm());
		model.addAttribute("date_maxBid", "");
		model.addAttribute("user_maxBid", "아직 입찰자가 없습니다.");
		
//		작성자만 수정/삭제 버튼 보이게 하기 위해 isWriter, 작성자 출력 위해 writer값을 넘겨준다.
		model.addAttribute("isWriter", true);
		model.addAttribute("writer", user.getUser().getNickname());
		model.addAttribute("bidForm", session.getAttribute("bidForm"));
//		목록에서의 좋아요 기능을 위한 파라미터: loginUserId
		model.addAttribute("loginUserId", user.getUser().getUserId());
		sessionStatus.setComplete();
		return AUCTION_DETAIL;
	}
	
	@RequestMapping(value="/auction/delete.do")
	public ModelAndView auctionDelete(HttpServletRequest request,
			@RequestParam("auctionId") int auctionId, SessionStatus status){
//		서버에서 경매 이미지들 삭제
		List<Image_a> auctionImgs = auctionService.getAuction(auctionId).getImgs_a();
		for (Image_a auctionImg : auctionImgs) {
			String[] fileName = auctionImg.getUrl().split("/");	// /resources/images/사진이름
			if (deleteFile(uploadDir + fileName[3])) {
				System.out.println("파일 삭제 성공!");
			}
		}
//		DB에서 경매 삭제 (테이블: Auctions, Images_a)
		UserSession user  = (UserSession)request.getSession().getAttribute("userSession");
		int loginUserId = user.getUser().getUserId();
		List<Auction> auctionList = auctionService.deleteAuction(auctionId, loginUserId);
		
		ModelAndView mav = new ModelAndView(AUCTION_LIST);
		mav.addObject("loginUserId", loginUserId);
		mav.addObject("auctionList", auctionList);
		status.setComplete();
		return mav;
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
			String savedName = "Goodsom_auction_" + uuid.toString();
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
