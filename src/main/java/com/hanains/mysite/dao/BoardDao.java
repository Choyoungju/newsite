package com.hanains.mysite.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hanains.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
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
	
	public BoardVo get( Long no ) {
		BoardVo vo = null;
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String sql = 
				" select no, title, content, member_no" +
				"   from board" +
				"  where no=?";
			PreparedStatement pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. execute SQL
			ResultSet rs = pstmt.executeQuery();
			if( rs.next() ) {
				vo = new BoardVo();
				vo.setNo( rs.getLong( 1 ) );
				vo.setTitle( rs.getString( 2 ) );
				vo.setContent( rs.getString( 3 ) );
				vo.setMemberNo( rs.getLong( 4 ) );
			}
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		
		return vo;
	}

	public void increaseViewCount( Long no ) {
		try{
			//1. get Connection
			Connection conn = getConnection();
			
			//2. prepare statement
			String sql = 
				" update board" +
				"    set view_cnt = view_cnt + 1" +		
				"  where no=?";
			PreparedStatement pstmt = conn.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. execute SQL
			pstmt.executeUpdate();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}		
	}
	
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();

			String sql =
				"   select a.no," +
				"          a.title," +
				"          a.member_no," +
				"          b.name as member_name," +
				"          a.view_cnt," +
				"          to_char(a.reg_date, 'yyyy-mm-dd hh:MM:ss')" +
				"     from board a," +
				"          member b" +
				"    where a.member_no = b.no" +
				" order by a.reg_date desc";
			
			ResultSet rs = stmt.executeQuery( sql );
			while( rs.next() ){
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				Long memberNo = rs.getLong( 3 );
				String memberName = rs.getString( 4 );
				int viewCount = rs.getInt( 5 );
				String regDate = rs.getString( 6 );
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMemberNo(memberNo);
				vo.setMemberName(memberName);
				vo.setViewCount(viewCount);
				vo.setRegdate(regDate);
				
				list.add( vo );
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch( SQLException ex ) {
			System.out.println( "SQL Error:" + ex );
		}
		
		return list;
	}
	
	public void update( BoardVo vo ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql =
				" update board" +
				"    set title=?," +
				"        content=?" +
				"  where no=?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getTitle() );
			pstmt.setString( 2, vo.getContent() );
			pstmt.setLong( 3, vo.getNo() );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
	
	public void delete( Long no ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = "delete from board where no = ?";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setLong( 1, no );
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
	
	public void insert( BoardVo vo ) {
		try {
			//1. Connection 가져오기
			Connection connection = getConnection();
			
			//2. Statement 준비
			String sql = 
				" insert" +
				"   into board" +
				" values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE )";
			PreparedStatement pstmt = connection.prepareStatement( sql );
			
			//3. binding
			pstmt.setString( 1, vo.getTitle() );
			pstmt.setString( 2, vo.getContent() );
			pstmt.setLong( 3, vo.getMemberNo() );
			
			System.out.println(vo.getTitle() + vo.getContent() + vo.getMemberNo());
			
			//4. query 실행
			pstmt.executeUpdate();
			
			//5. 자원정리
			pstmt.close();
			connection.close();
			
			
			
		} catch( SQLException ex ) {
			System.out.println( "SQL 오류-" + ex );
		}		
	}
}
