package com.icia.board.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.CommentDTO;
import com.icia.board.dto.PageDTO;
import com.icia.board.service.BoardService;
import com.icia.board.service.CommentService;

// 1202-01. 선생님과 수정! /Board/* 보드 이하 주소들을 모두 처리하는 역할을 줄 것.
@Controller

// 1202-03.
// 	@RequestMapping(value = "/board/save", method = RequestMethod.GET) 라고 쓸수있지만 매번 쓰기 힘드니까
@RequestMapping(value = "/board/*") // /board/* 로 요청하는 명령 추가

public class BoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private CommentService cs;

	/*
	 * 1202-03으로 인해 주석처리 // 1202-02. 경로설정으로 기존의 '2.글쓰기포맷'메소드를 변경해주는 메소드.
	 * 
	 * @RequestMapping(value = "/board/save", method = RequestMethod.GET) public
	 * String saveform() { // views/board폴더에 있는 save.jsp를 출력하게끔 변경해준다. return
	 * "board/save"; }
	 */

	// 1202-04. 경로설정으로 기존의 '2.글쓰기포맷'메소드를 변경해주는 메소드.
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String saveform() {
		// views/board폴더에 있는 save.jsp를 출력하게끔 변경해준다.
		return "board/save";
	}

	// 1202-05. 경로설정으로 기존의 '3.글쓰기처리'메소드를 변경해주는 메소드.
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) {
		System.out.println("BoardController에서 save() " + board);

		bs.save(board); // 4. 글쓰면 글목록으로 이동해야하니까 redirect return "redirect:/findAll";
		return "redirect:/board/findAll?b_number=" + board.getB_number();
	}

	// 1202-06. 경로설정으로 기존의 '4.글목록 출력' 수정
	@RequestMapping(value = "findAll", method = RequestMethod.GET)
	public String findAll(Model model) {
		List<BoardDTO> bList = bs.findAll();
		System.out.println("BoardController에서 findAll() " + bList);
		model.addAttribute("boardList", bList);
		return "board/findAll";
	}

	// 1202-07. 글조회
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	/*
	 * 원래것... public String detail(@RequestParam(value="page", required=false,
	 * defaultValue="1")int page, Model model, @RequestParam("b_number") long
	 * b_number) { 1203 페이지 그대로 보내기위해 코드 수정!
	 */
	public String detail(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model,
			@RequestParam("b_number") long b_number) {
		bs.hits(b_number);
		BoardDTO board = bs.detailNo(b_number);
		System.out.println("BoardController에서 detail() " + board);
		model.addAttribute("board", board);
		model.addAttribute("page", page); // deatil.jsp로 갈 때 page 값을 갖고 가는 코드.
		
		//1206 댓글리스트 가져오기!
		List<CommentDTO> commentList = cs.findAll(b_number);
		model.addAttribute("commentList", commentList);
		
		return "board/detail";
	}

	// 1202-08. 글삭제

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(@RequestParam("b_number") long b_number) {
		bs.delete(b_number);
		System.out.println("BoardController에서 delete()" + b_number);
		return "redirect:/board/findAll";
	}

	// 1202-09. 글수정

	/*
	 * @RequestMapping(value="update",method=RequestMethod.GET) public String
	 * update(@RequestParam(value="page", required=false, defaultValue="1")int page,
	 * Model model, @ModelAttribute BoardDTO board) { bs.update(board);
	 * model.addAttribute("board",board); model.addAttribute("page", page);
	 * //update.jsp로 갈 때 page값을 갖고 가는 코드 return "board/update"; }
	 */

	// 선생님꺼!
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String updateForm(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model, @RequestParam("b_number") long b_number) {
		BoardDTO board = bs.detailNo(b_number);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "board/update";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@ModelAttribute BoardDTO board) {
		bs.update(board);		
		System.out.println(board);
		return "redirect:/board/detail?b_number=" + board.getB_number() + "&page" + page;
	}

	// 1203 페이징작업
	@RequestMapping(value = "paging", method = RequestMethod.GET)
	// value : 파라미터 이름, required : 필수여부, defaultValue : 기본값(page요청값이 없으면 1로 셋팅)
	public String paging(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		PageDTO paging = bs.paging(page);
		List<BoardDTO> boardList = bs.pagingList(page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		return "board/findAll";
	}

	// 검색어 메소드 
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String search(Model model, @RequestParam("searchtype") String searchtype,
			@RequestParam("keyword") String keyword)	{
		List<BoardDTO> boardList = bs.search(searchtype,keyword);
		model.addAttribute("boardList", boardList);
		return "board/findAll";
	}
	
	
	
	// 1206-02. 경로설정으로 기존의 '3.글쓰기처리'메소드를 변경해주는 메소드.
	@RequestMapping(value = "saveFile", method = RequestMethod.POST)
	public String savefile(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException {

		bs.saveFile(board); // 4. 글쓰면 글목록으로 이동해야하니까 redirect return "redirect:/findAll";
		return "redirect:/board/paging";
		/* return "redirect:/board/findAll"; */
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	/*
	 * //2. 글쓰기포맷 요청
	 * 
	 * @RequestMapping (value="/insert", method = RequestMethod.GET) public String
	 * insertform() { return "insert"; }
	 * 
	 * //3. 글쓰기처리
	 * 
	 * @RequestMapping (value = "/insert", method = RequestMethod.POST) public
	 * String write(@ModelAttribute BoardDTO board) {
	 * System.out.println("BoardController에서 insert() "+board);
	 * 
	 * bs.insert(board); //4. 글쓰면 글목록으로 이동해야하니까 redirect return "redirect:/findAll";
	 * return "redirect:/findAll?b_number="+board.getB_number(); }
	 * 
	 * 
	 * //5. findAll 글목록 출력
	 * 
	 * @RequestMapping (value = "/findAll", method = RequestMethod.GET) public
	 * String findAll(Model model) { List<BoardDTO> bList = bs.findAll();
	 * System.out.println("BoardController에서 findAll() "+bList);
	 * model.addAttribute("boardList", bList); return "findAll"; }
	 */

//	----------------------------------------------------------------------------------
	// 6. detail 글 상세보기 출력
//	@RequestMapping (value="/detail", method = RequestMethod.GET)
//	public String detail(Model model, @RequestParam("b_number") long b_number)	{
//		BoardDTO board = bs.detailNo(b_number);
//		bs.hits(b_number);
//		System.out.println("BoardController에서 detail() "+board);
//		model.addAttribute("board", board);
//		return "detail";
//	}

}