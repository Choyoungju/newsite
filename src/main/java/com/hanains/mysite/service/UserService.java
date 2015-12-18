package com.hanains.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public void join(UserVo vo){
		userDao.insert(vo);
	}
	
	public UserVo login(UserVo vo){
		UserVo authUser = userDao.get(vo);
		
		return authUser;
	}
	

	public void update( UserVo vo ) {
		userDao.update(vo);
	}
	
	public UserVo getUser(String email){
		UserVo userVo = userDao.get(email);
		return userVo;
	}
	
	public UserVo getUser(Long  no){
		UserVo userVo = userDao.get(no);
		return userVo;
	}
}
