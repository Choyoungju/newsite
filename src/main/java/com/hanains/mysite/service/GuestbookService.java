package com.hanains.mysite.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.GuestBookVo;
import com.hanains.mysite.vo.UserVo;

@Service
public class GuestbookService {

	
	@Autowired
	private GuestBookDao guestbookDao;
	
	public void write( GuestBookVo vo ) {
		guestbookDao.insert( vo );
		
	}
	

	
	public void delete( GuestBookVo vo ) {
		 guestbookDao.delete( vo );
		
	}
	
	public List<GuestBookVo> list() {
		List<GuestBookVo> list = guestbookDao.getList();
		return list;
	}
	

}