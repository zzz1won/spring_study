package com.icia.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.board.dto.CommentDTO;
import com.icia.board.service.CommentService;

@Controller
@RequestMapping ("/comment/*")


public class CommentController {
	
	@Autowired
	private CommentService cs;

	@RequestMapping (value="save", method=RequestMethod.POST)
	/*
	 * public void comment(@RequestParam("c_writer") String c_writer,
	 * @RequestParam("c_contents") String c_contents,
	 * @RequestParam("b_number") long b_number)
	 */	
	public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO comment){
		System.out.println("CommentController.save()"+comment);
			
		// 새로운 댓글을 db에 저장하고 최신의 리스트를 db에서 가져와 ajax 쪽으로 리턴해야한다...
		// 이렇게 처리를 해야 화면 새로고침 없이 댓글이 실시간으로 추가되는 것처럼 보인다.
		/*
		 * 댓글작성하기
		 * 1. db에 댓글을 저장한다
		 * 2. db로부터 댓글목록을 가져온다.(모두 다 가져오는게 아니라 현재 게시글에 대한 댓글만 가져옴)
		 * 3. 댓글목록을 리턴한다.
		 * 
		 * commentService 인터페이스를 만들고
		 * commentServiceImpl 클래스에서 메서드 실행내용을 작성하세요.
		 */
		
		cs.save(comment);
		List<CommentDTO> commentList = cs.findAll(comment.getB_number());
		
		return commentList;
	}
}

