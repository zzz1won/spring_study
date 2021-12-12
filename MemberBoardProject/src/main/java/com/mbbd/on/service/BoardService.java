package com.mbbd.on.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mbbd.on.dto.BoardDTO;
import com.mbbd.on.dto.CommentDTO;
import com.mbbd.on.dto.PageDTO;
import com.mbbd.on.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository br;
	
	//글쓰기 처리 메소드
	public void save(BoardDTO board)	throws IllegalStateException, IOException {
	// dto에 담긴 파일을 가져옴 
	MultipartFile b_file = board.getB_file();
	// 파일 이름을 가져옴(파일이름을 DB에 저장하기 위해)
	String b_filename = b_file.getOriginalFilename();
	// 파일명 중복을 피하기 위해 파일이름앞에 현재 시간값을 붙임. 
	b_filename = System.currentTimeMillis() + "-" + b_filename;
	System.out.println("b_filename: " + b_filename);
	// 파일 저장 경로 셋팅
	String savePath = "C:\\Users\\exo_g\\Documents\\spring_sts\\MemberBoardProject\\src\\main\\webapp\\resources\\upload\\"+b_filename;
	// bfile이 비어있지 않다면(즉 파일이 있으면) savePath에 저장을 하겠다.
	if(!b_file.isEmpty()) {
		b_file.transferTo(new File(savePath));
	}
	// 여기까지의 내용은 파일을 저장하는 과정 
	//DB에 저장하기위해 DTO에 파일이름을 담는다.		
	board.setB_filename(b_filename);		
	// DB의 board_table에 파일이름을 저장할 b_filename 이라는 컬럼 추가(타입은 varchar(300))
	br.save(board);
	}
	

	//수정사항등이 없기때문에 리턴만 진행
	public List<BoardDTO> boardAll() {
		System.out.println("BoardService에서 boardAll()");
		return br.boardAll();
	}

	//boardDetail
	// 조회수 증가
	public BoardDTO hits(long b_number) {
		BoardDTO board = br.hits(b_number);
		return board;
		
	}

	// 상세조회
	public BoardDTO detailNo(long b_number) {
		BoardDTO board = br.detailNo(b_number);
		return board;
	}

	// 글삭제
	public void delete(long b_number) {
		br.delete(b_number);
		System.out.println("boardService.delete기능: ");		
	}


	//page! 선생님 코드 갖다붙이면서... 내 변수명이랑 맞춰줄것!!! + 분석해보기
	
	private static final int PAGE_LIMIT = 5; // 한페이지에 보여질 글 개수 
	private static final int BLOCK_LIMIT = 3; // 한화면에 보여질 페이지 개수
	
	
	public List<BoardDTO> pagingList(int page) {
		int pagingStart = (page-1) * PAGE_LIMIT;
		Map<String, Integer> pagingParam = new HashMap<String, Integer>();
		pagingParam.put("start", pagingStart);
		pagingParam.put("limit", PAGE_LIMIT);
//		List<BoardDTO> pagingList = br.pagingList(pagingStart);
		List<BoardDTO> pagingList = br.pagingList1(pagingParam);
		for(BoardDTO b: pagingList) {
			System.out.println(b.toString());
		}
		return pagingList;
	}
	
	public PageDTO paging(int page) {
		int boardCount = br.boardCount();
		int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage > maxPage)
			endPage = maxPage; 
		PageDTO paging = new PageDTO();
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		System.out.println("paging.toString(): "+ paging.toString());
		
		return paging;
	}


	public List<BoardDTO> search(String searchtype, String keyword) {
		Map<String,String> searchParam = new HashMap<String, String>();
		searchParam.put("type", searchtype);
		searchParam.put("word", keyword);
		List<BoardDTO> boardList = br.search(searchParam);
		System.out.println("BoardService.search기능수행");
		return boardList;
	}


	public void bupdate(BoardDTO board) {
		System.out.println("boardService update" + board);
		br.update(board);		
	}





	
	

	
	
	

	
	
	
	
	
	
	
	
	
	
}
