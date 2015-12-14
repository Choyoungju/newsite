package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.hanains.mysite.exception.RepositoryException;
import com.hanains.mysite.vo.UserVo;



@Repository
public class UserDao {
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		
		try {
			//1.드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2.커넥션 만들기(ORACLE DB)
			String dbURL  = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection( dbURL, "webdb", "webdb" );
			
		} catch( ClassNotFoundException ex ){
			System.out.println( "드라이버 로딩 실패-" + ex );
		}
		
		return connection;
	}
	
	public UserVo get( String email, String password ) throws RepositoryException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		UserVo vo = null;
		
		try{
			//1. get Connection
			conn = getConnection();
			
			//2. prepare statement
			String sql = 
				" select no, name, email" +
				"   from member" +
				"  where email=?"+
				"    and password=?";
			pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, email );
			pstmt.setString( 2, password );
			
			//4. execute SQL
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				Long no = rs.getLong( 1 );
				String name = rs.getString( 2 );
				String email2 = rs.getString( 3 );
				
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(email2);
			}
			
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
			throw new RepositoryException(ex.toString());
		} finally {
			//5. clear resources
			try{
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
		
		return vo;
	}

	public void insert( UserVo vo ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. DB Connection
			conn = getConnection();
			
			//2. prepare statment
			String sql = 
				" insert" +
				" into member" +
				" values ( member_no_seq.nextval, ?, ?, ?, ? )";
			pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getName() );
			pstmt.setString( 2, vo.getEmail() );
			pstmt.setString( 3, vo.getPassword() );
			pstmt.setString( 4, vo.getGender() );
			
			//4. execute SQL
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "sql error:" + ex );
			ex.printStackTrace();
		} finally {
			//5. clear resources
			try{
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch( SQLException ex ) {
				ex.printStackTrace();
			}
		}
	}
}