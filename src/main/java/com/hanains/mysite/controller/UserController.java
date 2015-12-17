package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@RequestMapping("/modifyform")
	public String modifyform(){

		return "/user/modifyform";
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
	

	@RequestMapping( "/update" )
	public String modify(@ModelAttribute UserVo userVo ) {
		
		userService.update( userVo );
		System.out.println("흠");
		return "redirect:/";
	}
	
//	@RequestMapping( "/modify" )
//	public String modify(  UserVo authUser, @ModelAttribute UserVo userVo ) {
//		userVo.setNo( authUser.getNo() );
//		userService.update( userVo );
//		System.out.println("흠");
//		return "redirect:/";
//	}
	
	
}
