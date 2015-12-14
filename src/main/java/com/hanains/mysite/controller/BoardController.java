package com.hanains.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping( "/modify/{no}" )
	public String modify( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "vo", vo );
		return "/board/modify";
	}

	
	@RequestMapping( "" )
	public String list( Model model ) {
		List<BoardVo> list = boardService.list();
		model.addAttribute( "list", list );
		return "/board/list";
	}
	

	@RequestMapping( "/delete/{no}" )
	public String delete( UserVo authUser, @PathVariable( "no" ) Long no ) {
		boardService.deleteBoard( no, authUser.getNo() );
		return "redirect:/board";
	}	
	
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.viewBoard( no );
		model.addAttribute( "board", vo );
		return "/board/view";
	}



	
	
}
