package com.example.goodsom.controller.groupBuy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.service.GroupBuyService;
import com.example.goodsom.service.QuestionService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.07
 */

/*
@Controller
@SessionAttributes("groupBuySession")
*/
public class DeleteQuestionController {
	/*
	@Autowired
	QuestionService questionService;
	@Autowired
	GroupBuyService groupBuyService;
	
	@RequestMapping("/question/delete.do")
	public ModelAndView groupBuyDelete(HttpServletRequest request, 
									@RequestParam("questionId") int questionId,
									@ModelAttribute("groupBuySession") LineGroupBuyCommand groupBuySession,
									SessionStatus status) {
		ModelAndView mav = new ModelAndView("groupBuy/groupBuy_detail");
		questionService.deleteQuestion(questionId);
		mav.addObject("groupBuy", groupBuyService.getGroupBuy(groupBuySession.getGroupBuyId()));
		status.setComplete(); // remove groupBuySession from session
		return mav;
	}
	*/
}
