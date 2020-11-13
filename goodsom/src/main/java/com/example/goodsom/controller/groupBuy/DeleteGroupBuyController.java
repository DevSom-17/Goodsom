package com.example.goodsom.controller.groupBuy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.domain.GroupBuy;
import com.example.goodsom.service.GroupBuyService;

/**
 * @author Seonmi Hwang | HK 
 * @since 2020.05.06	| @since 2020.06.22
 */

@Controller
public class DeleteGroupBuyController {
	
	private static final String GROUPBUY_LIST = "groupBuy/groupBuy_list";
	
	@Autowired
	GroupBuyService groupBuyService;
	
	// detail -> (delete후) list
	@RequestMapping("/groupBuy/delete.do")
	public ModelAndView groupBuyDelete(HttpServletRequest request, 
									@RequestParam("groupBuyId") int groupBuyId,
									SessionStatus status) {
		ModelAndView mav = new ModelAndView(GROUPBUY_LIST);
		
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
	
}
