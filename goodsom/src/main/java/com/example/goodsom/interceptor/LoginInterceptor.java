package com.example.goodsom.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.goodsom.controller.user.UserSession;

public class LoginInterceptor implements HandlerInterceptor {

	public List<String> loginEssential
    	= Arrays.asList("/**");

	public List<String> loginInessential
	    = Arrays.asList("/user/login.do", "/user/register.do");

	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");

        if (userSession != null) { 
        	return true; 
        } else {
        	request.getSession().setAttribute("interceptor", "interceptor");
            response.sendRedirect("/user/login.do");
            return false;
        }
    }

}
