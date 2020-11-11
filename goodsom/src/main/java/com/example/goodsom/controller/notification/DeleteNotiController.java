package com.example.goodsom.controller.notification;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.controller.user.UserSession;
import com.example.goodsom.domain.Notification;
import com.example.goodsom.domain.User;
import com.example.goodsom.service.NotiService;

/**
 * @author Yejin Lee | HK
 * @since 2020.05.07 | 2020.06.29
 */
@Controller
public class DeleteNotiController {
	
	@Value("user/noti_list")
	private String formViewName;
	
	@Autowired
	NotiService notiService;
	
	@RequestMapping("/noti/delete.do")
	public ModelAndView handleRequest(HttpSession session,
			@RequestParam("notiId") int notiId,
			@RequestParam("type") int type) throws Exception {
		UserSession userSession  = (UserSession)session.getAttribute("userSession");
		User user = userSession.getUser();
		List<Notification> bidNotiList = null;
		List<Notification> groupBuyNotiList = null;
		
		ModelAndView mov = new ModelAndView();
		
		if(type==1) {
			notiService.deleteAuctionNoti(notiId);
		}else {
			notiService.deleteGroupBuyNoti(notiId);
		}
		
		bidNotiList = notiService.getAuctionNotiByUserId(user.getUserId());
		for(int i=0; i<bidNotiList.size(); i++) {
				bidNotiList.get(i).setContent("참여한 경매가 낙찰되었습니다. 결제를 진행해주세요!");
		}
			
		groupBuyNotiList = notiService.getGroupBuyNotiByUserId(user.getUserId());
		for(int i=0; i<groupBuyNotiList.size(); i++) {
			if( (groupBuyNotiList.get(i).getState()).equals("closed") ) {
				groupBuyNotiList.get(i).setContent("참여한 공동구매가 마감되었습니다.");
			}else {
				groupBuyNotiList.get(i).setContent("참여한 공동구매가 목표 인원을 달성하여 성사되었습니다!");
			}
		}
		
		mov.addObject("nickname", user.getNickname());
		mov.addObject("bidNotiList", bidNotiList);
		mov.addObject("groupBuyNotiList", groupBuyNotiList);
		mov.setViewName(formViewName);
		return mov;
	}
	
}
