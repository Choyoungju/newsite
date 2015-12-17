package com.hanains.mysite.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.UserVo;



@Controller("UserAPIController")
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return "안녕하세요";
	}
	
	@ResponseBody
	@RequestMapping("/json")
	public Object json(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", "success");
		map.put("message",null);
		map.put("data", new Boolean(false));
		
		return map;
	}

	@ResponseBody
	@RequestMapping("checkemail")
	public Object checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email){
		
	UserVo vo = userService.getUser(email);
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("result", "success");

	map.put("data", vo == null);
	
		return map;
	}
	
}
