package com.hanains.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.service.GuestbookService;
import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.GuestBookVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	private static final Log LOG = LogFactory.getLog( BoardController.class );

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping( "" )
	public String list( Model model ) {
		List<GuestBookVo> list = guestbookService.list();
		model.addAttribute( "list", list );
		LOG.warn( "######### 방명록 리스트" );
		return "/guestbook/list";
	}
	

	
	@RequestMapping( "/insert" )
	public String insert( @ModelAttribute GuestBookVo vo ) {

		if(vo.getMessage().trim().length()==0 ||vo.getName().trim().length()==0 || vo.getPassword().trim().length()==0){
			return "redirect:/guestbook?result=fail";
		}
		
		guestbookService.write( vo );
		LOG.warn( "######### 방명록 삽입" );
		return "redirect:/guestbook";
	}
	

	
	@RequestMapping("/deleteform")
	public String deleteform(){
		return "/guestbook/deleteform";
	}
	
	@RequestMapping("/delete")
	public String delete(@ModelAttribute GuestBookVo vo){
		guestbookService.delete(vo);
		LOG.warn( "######### 방명록 삭제" );
		return "redirect:/guestbook/";
	}



		
		
}