package com.hanains.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.dao.GuestBookDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	public List<BoardVo> list() {
		List<BoardVo> list = boardDao.getList();
		return list;
	}
	
	public void write( BoardVo vo ){
		boardDao.insert( vo );
	}
	
	public void update( BoardVo vo ){
		boardDao.update( vo );
	}	

	public void delete( Long no, Long memberNo ){
		boardDao.delete(memberNo);
	}	

	public BoardVo view( Long no ){
		BoardVo vo = boardDao.get( no );
		boardDao.increaseViewCount(no);
		return vo;
	}
	
}
