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
import org.springframework.util.StopWatch;

import com.hanains.mysite.vo.GuestBookVo;


@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	
//	public List<GuestBookVo> getList(){
//		
//
//		
//		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
//		Connection connection = null;
//		Statement stmt= null;
//		ResultSet rs= null;
//		
//		try{
//			//1.드라이버 로딩(클래스 로딩)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2.DB연결
//			String dbUrl="jdbc:oracle:thin:@localhost:1521:xe";
//			connection = DriverManager.getConnection(dbUrl,"webdb","webdb");
//			
//			//3.statement 생성
//			stmt = connection.createStatement();
//			
//			String sql="select no, name, password, message, to_char(reg_date,'YYYY-MM-DD HH:MI:SS')as reg_date from guestbook ORDER BY no desc";
//			rs = stmt.executeQuery(sql);
//			
//			while(rs.next()){
//				Long no=rs.getLong(1);
//				String name=rs.getString(2);
//				String password=rs.getString(3);
//				String message = rs.getString(4);
//				String reg_date = rs.getString(5);
//				
//				GuestBookVo vo= new GuestBookVo();
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setPassword(password);
//				vo.setMessage(message);
//				vo.setReg_date(reg_date);
//				
//				list.add(vo);
//				
//				
//			
//			}
//			
//			
//			
//		}catch(ClassNotFoundException ex){
//			System.out.println("드라이버 연결 오류.:"+ex);
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}finally{
//			
//			try{
//				if(rs != null){
//					rs.close();
//				}
//				if(stmt != null){
//					stmt.close();
//				}
//				if(connection != null){
//					connection.close();
//				}
//				
//			}catch(SQLException ex){
//				ex.printStackTrace();
//			}
//		}
//		return list;
//	}

	
	
	public List<GuestBookVo> getList() {
		
		String taskName= "GuestBookDao.getList()";
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(taskName);
		
		List<GuestBookVo> list = sqlSession.selectList( "guestbook.select" );
		
		stopWatch.stop();
		System.out.println("Excution time : " + taskName + " " + stopWatch.getTotalTimeMillis());
		return list;
	}
	
	public void insert(GuestBookVo vo){
		
		sqlSession.insert("guestbook.insert",vo);
	}
	
	
	
	public void delete(GuestBookVo vo){
		sqlSession.delete("guestbook.delete", vo);
	}
}