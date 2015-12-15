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


	public List<BoardVo> getList() {
	
//		try {
//			Connection conn = getConnection();
//			Statement stmt = conn.createStatement();
//
//			String sql =
//					"   select a.no," +
//							"          a.title," +
//							"          a.member_no," +
//							"          b.name as member_name," +
//							"          a.view_cnt," +
//							"          to_char(a.reg_date, 'yyyy-mm-dd hh:MM:ss')" +
//							"     from board a," +
//							"          member b" +
//							"    where a.member_no = b.no" +
//							" order by a.reg_date desc";
//
//			ResultSet rs = stmt.executeQuery( sql );
//			while( rs.next() ){
//				Long no = rs.getLong( 1 );
//				String title = rs.getString( 2 );
//				Long memberNo = rs.getLong( 3 );
//				String memberName = rs.getString( 4 );
//				int viewCount = rs.getInt( 5 );
//				String regDate = rs.getString( 6 );
//
//				BoardVo vo = new BoardVo();
//				vo.setNo(no);
//				vo.setTitle(title);
//				vo.setMemberNo(memberNo);
//				vo.setMemberName(memberName);
//				vo.setViewCount(viewCount);
//				vo.setRegdate(regDate);
//
//				list.add( vo );
//			}
//
//			rs.close();
//			stmt.close();
//			conn.close();
//
//		}catch( SQLException ex ) {
//			System.out.println( "SQL Error:" + ex );
//		}
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
