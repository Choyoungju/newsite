package com.hanains.mysite.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.hanains.mysite.vo.BoardVo;



@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void update( BoardVo vo ) {
		sqlSession.update( "board.update", vo );
	}
	
	public List<BoardVo> getList( String searchKeyword, Long page, Integer pageSize ) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "searchKeyword", searchKeyword );
		map.put( "page", page );
		map.put( "pageSize", pageSize );
		
		List<BoardVo> list = sqlSession.selectList( "board.selectList", map );
		
		return list;
	}

	public Long getCount( String searchKeyword ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "searchKeyword", searchKeyword );
		
		Long count = sqlSession.selectOne( "board.selectCount", map );
		
		
		
		return count;
	}	
	
	public void insert( BoardVo vo ) {
		Long groupNo = vo.getGroupNo();
		if( groupNo != null ) { // 답글인 경우
			sqlSession.update( "board.updateOrderNo", vo.getOrderNo() );
		}
		
		sqlSession.insert( "board.insert", vo );
	}
	
	public void delete( Long no, Long memberNo ) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put( "no", no );
		map.put( "memberNo", memberNo );
		
		sqlSession.delete( "board.delete", map );
	}
	
	public void updateViewCount( Long no ) {
		sqlSession.update( "board.updateViewCount", no );
	}
	
	public BoardVo get( Long no ) {
		BoardVo vo = sqlSession.selectOne( "board.selectByNo", no );
		return vo;
	}
}
