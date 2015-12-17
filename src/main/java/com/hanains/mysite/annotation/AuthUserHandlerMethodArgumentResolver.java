package com.hanains.mysite.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.hanains.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {
		// TODO Auto-generated method stub
		
		if(this.supportsParameter(parameter) == false){
			return WebArgumentResolver.UNRESOLVED;
		}
	
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null){
			return WebArgumentResolver.UNRESOLVED;
		}
	
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		return authUser;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		return (parameter.getParameterAnnotation(AuthUser.class)!=null) &&
				(parameter.getParameterType().equals(UserVo.class)==true);
	}

}
