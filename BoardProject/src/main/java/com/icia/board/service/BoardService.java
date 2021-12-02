package com.icia.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.dto.BoardDTO;
import com.icia.board.repository.BoardRepository;

@Service

//1202.다 작성한 후 수정들어가는중이니 아침인 지금 깃업로드 하기...

public class BoardService {
	/*public interface BoardService {*/
	//interface. 추상메서드로. 추상메서드: 실행블록은 없고 메서드 선언부만 있는 것
	//interface 구현하는 구현클래스에서 실행블록을 작성함.

	@Autowired
	private BoardRepository br;
	
	public void insert(BoardDTO board) {
		System.out.println("boardService.insert()"+board);
		br.insert(board);
		
	}

	public List<BoardDTO> findAll() {
		List<BoardDTO> bList = br.findAll();
		return bList;
	}

	public BoardDTO detailNo(long b_number) {
		BoardDTO board = br.detailNo(b_number);
		return board;
	}

	//조회수 증가



}
