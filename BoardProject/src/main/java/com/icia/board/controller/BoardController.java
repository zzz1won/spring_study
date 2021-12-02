package com.icia.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icia.board.dto.BoardDTO;
import com.icia.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;

	
	//2. 글쓰기포맷 요청
	@RequestMapping (value="/insert", method = RequestMethod.GET)
	public String insertform()	{
		return "insert";
	}
	
	//3. 글쓰기처리
	@RequestMapping (value = "/insert", method = RequestMethod.POST)
	public String write(@ModelAttribute BoardDTO board)	{
		System.out.println("BoardController에서 insert() "+board);
		
		bs.insert(board);
	//4. 글쓰면 글목록으로 이동해야하니까 redirect
		return "redirect:/findAll";
		/* return "redirect:/findAll?b_number="+board.getB_number(); */
	}
	
	
	//5. findAll 글목록 출력
	@RequestMapping (value = "/findAll", method = RequestMethod.GET)
	public String findAll(Model model)	{
		List<BoardDTO> bList = bs.findAll(); 
		System.out.println("BoardController에서 findAll() "+bList);
		model.addAttribute("boardList", bList);
		return "findAll";
	}

//	//6. detail 글 상세보기 출력
//	@RequestMapping (value="/detail", method = RequestMethod.GET)
//	public String detail(Model model, @RequestParam("b_number") long b_number)	{
//		BoardDTO board = bs.detailNo(b_number);
//		bs.hits(b_number);
//		System.out.println("BoardController에서 detail() "+board);
//		model.addAttribute("board", board);
//		return "detail";
//	}




}