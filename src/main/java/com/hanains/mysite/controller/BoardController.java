package com.hanains.mysite.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import com.hanains.mysite.annotation.Auth;
import com.hanains.mysite.service.BoardService;
import com.hanains.mysite.service.UserService;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.GuestBookVo;
import com.hanains.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	


	@Autowired
	private BoardService boardService;
	
	
	private static final Log LOG = LogFactory.getLog( BoardController.class );
	private static final String SAVE_PATH = "/temp/";
	
	@Auth
	@RequestMapping( "/modify/{no}" )
	public String modify( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.view( no );
		model.addAttribute( "board", vo );
		return "/board/modify";
	}

	@Auth
	@RequestMapping("/write")
	public String insert( HttpSession session){
//		
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		if(authUser == null){
//			return "redirect:/user/loginform";
//		}
		
		return "/board/write";
		
	}
	
	@RequestMapping("/insert")
	public String join(@ModelAttribute BoardVo vo){
		boardService.write(vo);
		return "redirect:/board/upload";
	}
	
	@Auth
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardVo vo){
		boardService.update(vo);
		return "redirect:/board/";
	}
	
	
	
	
	@RequestMapping( "" )
	public String list( Model model ) {
		List<BoardVo> list = boardService.list();
		model.addAttribute( "list", list );
		return "/board/list";
	}
	
	@Auth
	@RequestMapping( "/delete/{no}" )
	public String delete( UserVo authUser, @PathVariable( "no" ) Long no ) {
		boardService.delete( no, authUser.getNo() );
		return "redirect:/board";
	}	
	
	@Auth
	@RequestMapping( "/view/{no}" )
	public String view( @PathVariable( "no" ) Long no, Model model ) {
		BoardVo vo = boardService.view( no );
		model.addAttribute( "board", vo );
		return "/board/view";
	}
	
	@RequestMapping( "/upload" )
	public String upload(  @RequestParam( "uploadFile" ) MultipartFile multipartFile, Model model, @ModelAttribute BoardVo vo) {
        

		System.out.println("업로드 리퀘스트 매핑은 불러오는데 파일 빔");
		
		
		// 파일 처리
		if( multipartFile.isEmpty() == false ) {
			
	        String fileOriginalName = multipartFile.getOriginalFilename();
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
	        String fileName = multipartFile.getName();
	        Long size = multipartFile.getSize();
	        
	        String saveFileName = genSaveFileName( extName );
	
	        LOG.debug( " ######## fileOriginalName : " + fileOriginalName );
	        LOG.debug( " ######## fileName : " + fileName );
	        LOG.debug( " ######## fileSize : " + size );
	        LOG.debug( " ######## fileExtensionName : " + extName );
	        LOG.debug( " ######## saveFileName : " + saveFileName );        
	
	        writeFile( multipartFile, SAVE_PATH, saveFileName );
	        
	        String url = "/profile-images/" + saveFileName;
	        model.addAttribute( "profileUrl", url );
	        
	        System.out.println("업로드 리퀘스트 매핑");
	       
		}
		
		boardService.write(vo);
        return "redirect:/board/";
	}
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();
			fos = new FileOutputStream( path + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}



	
	
}
