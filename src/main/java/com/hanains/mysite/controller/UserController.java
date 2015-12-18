package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.annotation.AuthUser;
import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/joinform")
	public String joinform(){
		
		return "/user/joinform";
	}
	
	@RequestMapping("/loginform")
	public String loginform(){

		return "/user/loginform";
	}
	

	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo){
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	
	@RequestMapping("/joinsuccess")
	public String joinsucess(){

		return "/user/joinsuccess";
		
	}
	
	
	@RequestMapping("/login")
	public String login(HttpSession session, @ModelAttribute UserVo vo){
		UserVo authUser = userService.login(vo);
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String login(HttpSession session){

		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	

//	@RequestMapping( "/update" )
//	public String modify(@ModelAttribute UserVo userVo ) {
//		
//		userService.update( userVo );
//		System.out.println("흠");
//		return "redirect:/";
//	}
	

	@RequestMapping( value = "/modify", method = RequestMethod.POST )
	public String modify( @AuthUser UserVo authUser, @ModelAttribute UserVo userVo ) {
		userVo.setNo( authUser.getNo() );
		userService.update( userVo );
		return "redirect:/user/modifyform";
	}

	@RequestMapping( "/modifyform" )
	public String modifyForm( @AuthUser UserVo authUser, Model model ) {
		UserVo vo = userService.getUser( authUser.getNo() );
		model.addAttribute( "vo", vo );
		return "/user/modifyform";
	}
	
	
}
