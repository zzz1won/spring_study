package com.mbbd.on.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbbd.on.dto.BoardDTO;


@Repository
public class BoardRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	
	//글쓰기 요청 + 작성자 ㅠㅠ 제발
	
	//글쓰기 처리+파일첨부 기능
	public void save(BoardDTO board) {
		System.out.println("BoardRepository에서 save2(): "+board);
		sql.insert("Board.save", board);		
	}
	//글전체보기
	public List<BoardDTO> boardAll() {
		System.out.println("BoardRepository에서 boardAll");
		return sql.selectList("Board.boardAll");
	}
	
	//검색기능
	public List<BoardDTO> search(Map<String, String> searchParam) {
		return sql.selectList("Board.search",searchParam);
	}


	//페이징관련 ----------------------------------------------------------
	public int boardCount() {
		return sql.selectOne("Board.count");
	}
	
	public List<BoardDTO> pagingList(int pagingStart) {
		return sql.selectList("Board.pagingList", pagingStart);
	}

	public List<BoardDTO> pagingList1(Map<String, Integer> pagingParam) {
		return sql.selectList("Board.pagingList1", pagingParam);
	}
	
	
	// 조회수증가
	public BoardDTO hits(long b_number) {
		System.out.println("조회수증가 성공");
		return sql.selectOne("Board.hits", b_number);
	}
	// 글 상세조회
	public BoardDTO detailNo(long b_number) {
		System.out.println("글 상세조회 성공");
		return sql.selectOne("Board.detailNo", b_number);
	}
	// 글삭제
	public void delete(long b_number) {
		System.out.println("글 삭제성공");
		sql.delete("Board.delete", b_number);
	}

	// 글수정
	public void update(BoardDTO board) {
		System.out.println("boardRepository update" + board);
		sql.update("Board.bupdate", board);
	}



}
