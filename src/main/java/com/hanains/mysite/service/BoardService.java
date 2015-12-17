package com.hanains.mysite.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;


@Service
public class BoardService {
	private final int LIST_PAGESIZE = 5;
	private final int LIST_BLOCKSIZE = 5;
	
	@Autowired
	private BoardDao boardDao;
	
	public Map<String, Object> listBoard( String searchKeyword, Long page ){

		//1. calculate pager's basic data 
		long totalCount = boardDao.getCount( searchKeyword );
		long pageCount = (long)Math.ceil( (double)totalCount / LIST_PAGESIZE );
		long blockCount = (long)Math.ceil( (double)pageCount / LIST_BLOCKSIZE );
		long currentBlock = (long)Math.ceil( (double)page / LIST_BLOCKSIZE ); 
		
		//2. page validation
		if( page < 1 ) {
			page = 1L;
			currentBlock = 1;
		} else if( page > pageCount ) {
			page = pageCount;
			currentBlock = (int)Math.ceil( (double)page / LIST_BLOCKSIZE );
		}
		
		//3. calculate pager's data
		long startPage = (currentBlock == 0 ) ? 1 : ( currentBlock - 1 ) * LIST_BLOCKSIZE + 1;
		long endPage = ( startPage - 1 ) + LIST_BLOCKSIZE;
		long prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * LIST_BLOCKSIZE : 0;
		long nextPage = ( currentBlock < blockCount ) ? currentBlock * LIST_BLOCKSIZE + 1 : 0;

		//4. fetch list
		List<BoardVo> list = boardDao.getList( searchKeyword, page, LIST_PAGESIZE );
		
		//5. pack all information of list
		Map<String, Object> map = new HashMap<String, Object>();
		map.put( "list", list );
		map.put( "searchKeyword", searchKeyword );
		map.put( "firstItemIndex", totalCount - ( page - 1 ) * LIST_PAGESIZE );
		map.put( "currentPage", page );
		map.put( "startPage", startPage );
		map.put( "endPage", endPage );
		map.put( "pageCount", pageCount );
		map.put( "prevPage", prevPage );
		map.put( "nextPage", nextPage );
		
		return map;
	}
	
	public void writeBoard( BoardVo vo ){
		boardDao.insert( vo );
	}
	
	public void updateBoard( BoardVo vo ){
		boardDao.update( vo );
	}	

	public void deleteBoard( Long no, Long memberNo ){
		boardDao.delete( no, memberNo );
	}	

	public BoardVo viewBoard( Long no ){
		BoardVo vo = boardDao.get( no );
		boardDao.updateViewCount( no );
		return vo;
	}
}
