package com.example.goodsom.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.example.goodsom.controller.user.UserSession;

public class LoginInterceptor implements HandlerInterceptor {

	public List<String> loginInessential
	    = Arrays.asList("/user/login.do", "/user/register.do", "/sendSMS.do", "/email/send", "/email/verifyCode");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	UserSession userSession = (UserSession)request.getSession().getAttribute("userSession");

        if (userSession != null) { 
        	return true; 
        } else {
            response.sendRedirect("/user/login.do");
            return false;
        }
    }

}
