package com.mbbd.on.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mbbd.on.dto.CommentDTO;
import com.mbbd.on.service.CommentService;

@Controller
@RequestMapping ("/comment/*")
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	@RequestMapping (value="commentSave", method=RequestMethod.POST)
	
		public @ResponseBody List<CommentDTO> save (@ModelAttribute CommentDTO comment)	{
			
			/* 순서...
			 * 댓글작성하기
			 * 1. db에 댓글을 저장한다
			 * 2. db로부터 댓글목록을 가져온다.(모두 다 가져오는게 아니라 현재 게시글에 대한 댓글만 가져옴)
			 * 3. 댓글목록을 리턴한다.
			 */
			System.out.println("CommentController.commentSave"+comment);
			cs.save(comment);
			List<CommentDTO> commentList = cs.commentAll(comment.getB_number());
		return commentList;
	}

}
