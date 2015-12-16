package com.hanains.mysite.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;

@Repository
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> getList() {

		List<BoardVo> list = sqlSession.selectList( "board.getList" );
		return list;
	}

	
	

	public void increaseViewCount( Long no ) {
		sqlSession.update("board.increaseViewCount", no);
	}
	
	public BoardVo get( Long no ) {
		BoardVo boardVo = 	sqlSession.selectOne("board.get", no);

		return boardVo;
	}

	
	public void update( BoardVo vo ) {
		sqlSession.update("board.update", vo);	
	}

	public void delete( Long no ) {
		sqlSession.delete("board.delete", no);
	}

	public void insert( BoardVo vo ) {
		sqlSession.insert("board.insert", vo);
	}



}
