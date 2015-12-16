package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanains.mysite.exception.RepositoryException;
import com.hanains.mysite.vo.UserVo;



@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	public UserVo get( UserVo vo){
	UserVo userVo = 	sqlSession.selectOne("user.getbyEmailAndPassword", vo);
			
		return userVo;
	}

	
	public UserVo get(Long no){
		UserVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;
		
	}
	public void insert( UserVo vo ) {

		sqlSession.insert("user.insert",vo);
	}
	
	
	public void update( UserVo vo ) {
		sqlSession.update( "user.update", vo );
	}
	
	public UserVo get(String email){
		UserVo vo = sqlSession.selectOne("user.selectByEmail", email);
		return vo;
	}
}