package com.icia.board.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void insert(BoardDTO board) {
		System.out.println("BoardReporitory의 insert() "+board);
		sql.insert("Board.insert",board);
	}

	public List<BoardDTO> findAll() {
		System.out.println("BoardReporitory의 findAll()");
		return sql.selectList("Board.findAll");
		//여기는 왜 value를 안쓸까에 대한 이해도가 낮음.
	}

	public BoardDTO detailNo(long b_number) {
		System.out.println("BoardReporitory의 detailNo()");
		return sql.selectOne("Board.detailNo",b_number);
	}


//	public void addHits(long b_number) {
//		System.out.println("BoardReporitory의 addHits() ");
//		sql.insert("Board.addHits",b_number);
//	}


}
