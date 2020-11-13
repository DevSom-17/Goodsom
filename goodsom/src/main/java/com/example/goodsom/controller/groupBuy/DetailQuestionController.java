package com.example.goodsom.controller.groupBuy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.goodsom.service.QuestionService;

/**
 * @author Seonmi Hwang
 * @since 2020.05.07
 */

/*
@Controller
@SessionAttributes("groupBuySession")
*/
public class DetailQuestionController {
	
	/*
	@Autowired
	QuestionService questionService;
	
	@RequestMapping("/question/detail.do")
	public ModelAndView questionDetail(HttpServletRequest request, 
									@RequestParam("questionId") int questionId,
									@ModelAttribute("groupBuySession") LineGroupBuyCommand groupBuySession) {
		ModelAndView mav = new ModelAndView("question/question_detail");
		mav.addObject("question", questionService.getQuestion(questionId));
		return mav;
	}
	
	*/
}
