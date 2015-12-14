package com.hanains.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		System.out.println("Hello world");
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
	
}
