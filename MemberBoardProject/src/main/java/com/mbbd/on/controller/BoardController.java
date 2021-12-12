package com.mbbd.on.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mbbd.on.dto.BoardDTO;
import com.mbbd.on.dto.CommentDTO;
import com.mbbd.on.dto.PageDTO;
import com.mbbd.on.service.BoardService;
import com.mbbd.on.service.CommentService;



@Controller
@RequestMapping(value="/board/*")
public class BoardController {

	@Autowired
	private BoardService bs;
	
	@Autowired
	private CommentService cs;
	
	
	// 게시판-글쓰기 출력
	@RequestMapping(value="save", method=RequestMethod.GET)
	public String saveForm()	{
		System.out.println("BoardController에서 save1()");
		return "board/save";
	}
	
	// 게시판- 글쓰기 처리
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String save(@ModelAttribute BoardDTO board) throws IllegalStateException, IOException	{
		System.out.println("BoardController에서 save2()"+board);
		bs.save(board);
		//글쓰기 후 글 목록으로 이동시키기
		return "redirect:/board/paging?b_number="+board.getB_number();
		//redirect로 boardAll을 호출 
	}
	
	//글 목록 전체 출력
	@RequestMapping (value="boardAll",method=RequestMethod.GET)
	public String boardAll(Model model)	{
		List<BoardDTO> boardList = bs.boardAll();
		System.out.println("BoardController에서 boardAll: "+boardList);
		model.addAttribute("boardList", boardList);
		return "board/boardAll";
	}
	
	// 페이징처리
	@RequestMapping(value="paging", method= {RequestMethod.GET, RequestMethod.POST})
	public String paging(@RequestParam(value="page", required=false, defaultValue="1")int page, Model model) {
		//value : 파라미터 이름, required : 필수여부, defaultValue : 기본값(page요청값이 없으면 1로 셋팅)
		PageDTO paging = bs.paging(page);
		List<BoardDTO> boardList = bs.pagingList(page);
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", paging);
		return "board/boardAll";
	}
	
	

	
	// 글 상세보기
	@RequestMapping(value="boardDetail", method=RequestMethod.GET)
	public String detail(@RequestParam(value="page", required=false, defaultValue="1") int page, Model model,
						@RequestParam("b_number") long b_number)	{
		
		bs.hits(b_number);	//조회수 올려주는 기능!
		BoardDTO board=bs.detailNo(b_number); //b_number에 해당하는 내용 출력해주는 기능!
		System.out.println("boardController에서 boardDetail기능"+board);
		model.addAttribute("board", board);
		model.addAttribute("page", page);	//boardDetail.jsp로 갈 때 page 값을 가져가는 코드★
		
		// ❤❤❤❤❤❤❤❤❤❤ 댓글기능 추가할 것
		List<CommentDTO> commentList = cs.commentAll(b_number);
		model.addAttribute("commentList", commentList);
		
		return "board/boardDetail";	
	}
	
	
	// 글 삭제
	@RequestMapping(value="boardDelete",method=RequestMethod.GET)
	public String delete (@RequestParam("b_number") long b_number, Model model)	{
		bs.delete(b_number);
		System.out.println("boardController에서 boardDelete기능"+b_number);
		return "redirect:/board/paging";
	}
	
	// 글 수정 출력
	@RequestMapping(value="boardUpdate", method=RequestMethod.GET)
	public String updateForm (@RequestParam("b_number") long b_number, Model model)	{
		BoardDTO board = bs.detailNo(b_number);
		System.out.println("boardController에서 boardUpdate기능");
		model.addAttribute("board", board);
		return "board/boardUpdate";
	}
	
	// 글 수정 처리
	@RequestMapping (value="boardUpdate", method=RequestMethod.POST)
	public String update (@ModelAttribute BoardDTO board) {
		System.out.println("c"+board);
		bs.bupdate(board);
		System.out.println("boardController에서 boardUpdate기능2");
		/*return "redirect:/board/boardDetail?b_number="+board.getB_number();*/
		return "redirect:/board/paging";
	}
	
	
	// 검색어 메소드
	@RequestMapping(value="search", method=RequestMethod.GET)
	public String seacrh (Model model, @RequestParam("searchType") String searchtype,
						@RequestParam("keyword") String keyword)	{
			List<BoardDTO> boardList = bs.search(searchtype,keyword);
			model.addAttribute("boardList", boardList);
		return "board/boardAll";
	}
	
	
	
}

