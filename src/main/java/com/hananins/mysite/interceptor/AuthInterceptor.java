package com.hananins.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		System.out.println("Auth인터셉터 프리 콜 ");
		
		if(handler instanceof HandlerMethod == false){
			return true;
		}
		
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		if(auth == null){
			return true;
		}
		
		
		
		HttpSession session = request.getSession();

		if(session == null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}

		UserVo vo = (UserVo)session.getAttribute("authUser");
		if(vo == null){
			response.sendRedirect(request.getContextPath() + "/user/loginform");
			return false;
		}

		return true;

	}



}
