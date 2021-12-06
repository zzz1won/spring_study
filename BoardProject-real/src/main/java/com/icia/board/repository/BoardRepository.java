package com.icia.board.repository;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.board.dto.BoardDTO;

@Repository
public class BoardRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void save(BoardDTO board) {
		 System.out.println("BoardReporitory의 save() "+board);
		 sql.insert("Board.save",board); 
		 }

	public List<BoardDTO> findAll() {
		// TODO Auto-generated method stub
		return sql.selectList("Board.findAll");
	}
	
	 public BoardDTO detailNo(long b_number) {
		 System.out.println("BoardReporitory의 detailNo()"); 
		 return sql.selectOne("Board.detailNo",b_number); }

	 
	public BoardDTO hits(long b_number) {
		 System.out.println("BoardRepository의 hits()");
		return sql.selectOne("Board.hits", b_number);
	}

	public BoardDTO delete(long b_number) {
		 System.out.println("BoardRepository의 delete()");		
		return sql.selectOne("Board.delete", b_number);
	}

	
	public void update(BoardDTO board) {
		System.out.println("br.board"+board);
		sql.update("Board.update",	board); 
	}
	
	
	// 1203
	public int boardCount() {
		return sql.selectOne("Board.count");
	}
	
	public List<BoardDTO> pagingList(int pagingStart) {
		return sql.selectList("Board.pagingList", pagingStart);
	}

	public List<BoardDTO> pagingList1(Map<String, Integer> pagingParam) {
		return sql.selectList("Board.pagingList1", pagingParam);
	}

	public List<BoardDTO> search(Map<String, String> searchParam) {
		return sql.selectList("Board.search",searchParam);
	}

	public void saveFile(BoardDTO board) {
		sql.insert("Board.saveFile", board);
		
	}

	
	
	/* 1201 작업해둔것!
	 * public void insert(BoardDTO board) {
	 * System.out.println("BoardReporitory의 insert() "+board);
	 * sql.insert("Board.insert",board); }
	 * 
	 * public List<BoardDTO> findAll() {
	 * System.out.println("BoardReporitory의 findAll()"); return
	 * sql.selectList("Board.findAll"); //여기는 왜 value를 안쓸까에 대한 이해도가 낮음. }
	 * 
	 * public BoardDTO detailNo(long b_number) {
	 * System.out.println("BoardReporitory의 detailNo()"); return
	 * sql.selectOne("Board.detailNo",b_number); }
	 * 
	 */
//	public void addHits(long b_number) {
//		System.out.println("BoardReporitory의 addHits() ");
//		sql.insert("Board.addHits",b_number);
//	}


}
