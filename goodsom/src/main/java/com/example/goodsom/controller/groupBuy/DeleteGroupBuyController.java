package com.example.goodsom.controller.groupBuy;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.domain.Image_a;
import com.example.goodsom.domain.Image_g;
import com.example.goodsom.service.GroupBuyService;

/**
 * @author Seonmi Hwang | HK 
 * @since 2020.05.06	| @since 2020.06.22
 */

@Controller
public class DeleteGroupBuyController implements ApplicationContextAware {
	
	private static final String GROUPBUY_LIST = "groupBuy/groupBuy_list";
//	파일 업로드 위한 변수
	private WebApplicationContext context;	
	private String uploadDir;
	
	@Autowired
	GroupBuyService groupBuyService;
	
	@Override					// life-cycle callback method
	public void setApplicationContext(ApplicationContext appContext)
		throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath("/resources/images/");
	}
	
	// detail -> (delete후) list
	@RequestMapping("/groupBuy/delete.do")
	public ModelAndView groupBuyDelete(HttpServletRequest request, 
									@RequestParam("groupBuyId") int groupBuyId,
									SessionStatus status) {
		ModelAndView mav = new ModelAndView(GROUPBUY_LIST);
//		서버에서 경매 이미지들 삭제
		List<Image_g> groupBuyImgs = groupBuyService.getGroupBuy(groupBuyId).getImgs_g();
		for (Image_g groupBuyImg : groupBuyImgs) {
			String[] fileName = groupBuyImg.getUrl().split("/");	// /resources/images/사진이름
			if (deleteFile(uploadDir + fileName[3])) {
				System.out.println("파일 삭제 성공! 이제부터 파일 업로드.");
			}
		}
		// db
		groupBuyService.deleteOptions(groupBuyId);
		groupBuyService.deleteGroupBuy(groupBuyId);
		
		// 출력할 list
		List<GroupBuy> groupBuyList = null;
		groupBuyList = groupBuyService.getGroupBuyList();
		if (groupBuyList == null) {
			System.out.println("[DetailGroupBuyController] groupBuyList가 null");
		} else {
			mav.addObject("groupBuyList", groupBuyList);			
		}
		status.setComplete(); // 필요한가?
		return mav;
	}
	
//	파일명 삭제 메서드
	private boolean deleteFile(String oldFileSavedName) {
//		서버에 저장된 업로드된 파일을 삭제
		boolean result = new File(oldFileSavedName).delete();
		return result;
	}
}
