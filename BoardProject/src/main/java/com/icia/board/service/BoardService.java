package com.icia.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.board.dto.BoardDTO;
import com.icia.board.dto.PageDTO;
import com.icia.board.repository.BoardRepository;

@Service

//1202.다 작성한 후 수정들어가는중이니 아침인 지금 깃업로드 하기...

 public class BoardService {
/*	public interface BoardServiceImpl implements BoardService {*/
	//interface. 추상메서드로. 추상메서드: 실행블록은 없고 메서드 선언부만 있는 것
	//interface 구현하는 구현클래스에서 실행블록을 작성함.

	@Autowired
	private BoardRepository br;
	
	public void save(BoardDTO board) {
		System.out.println("boardService.save()"+board);
		br.save(board);		
	}
	
	public List<BoardDTO> findAll() {
	/*	List<BoardDTO> bList = br.findAll();
		return bList;*/
		return br.findAll();
	}
	
	public BoardDTO detailNo(long b_number) {
		BoardDTO board = br.detailNo(b_number);
		return board;
	}

	public BoardDTO hits(long b_number) {
		BoardDTO board = br.hits(b_number);
		return board;
		
	}

	public BoardDTO delete(long b_number) {
		BoardDTO board = br.delete(b_number);
		System.out.println("boardService.delete()"+board);
		return board;
	}

	public void update(BoardDTO board) {
		System.out.println("bs"+board);
		br.update(board);
	}
	
// 1203. 페이징처리 1-1


// 1203. 페이징처리 1-2 인터페이스를 해야 실행이 되는것 같다
		//변수가 아니라 상수선언, 대문자로 보통 사용한다. 볼드이탤릭처리.
		private static final int PAGE_LIMIT = 3; // 한페이지에 보여질 글 개수 
		private static final int BLOCK_LIMIT = 3; // 한화면에 보여질 페이지 개수
		
	
		public List<BoardDTO> pagingList(int page) {
		// TODO Auto-generated method stub
		int pagingStart = (page-1) * PAGE_LIMIT;
		Map<String, Integer> pagingParam = new HashMap<String, Integer>();
		pagingParam.put("start", pagingStart);
		pagingParam.put("limit", PAGE_LIMIT);
	//	List<BoardDTO> pagingList = br.pagingList(pagingStart);
		List<BoardDTO> pagingList = br.pagingList1(pagingParam);
		for(BoardDTO b: pagingList) {
			System.out.println(b.toString());
		}
		return pagingList;
	}

		// 필요한 총 페이지 갯수 계산
		// 현재 사용자가 요청한 페이지가 2페이지라면 화면에는 1,2,3을 보여주고
		// 요청 페이지가 6페이지라면 화면에 4,5,6을 보여준다.
		// 글이 20개라면 요청 페이지가 7페이지가 되고, 7을 보여준다. (8,9는 굳이 띄우지않음) 
		// 요청 페이지가 9페이지라면 7,8,9 다 띄우고... 
		
		public PageDTO paging(int page) {
		int boardCount = br.boardCount(); // 전체 글 갯수 조회
		int maxPage = (int)(Math.ceil((double)boardCount / PAGE_LIMIT)); // 전체 페이지 계산
		
		// 2페이지 요청했으면 1페이지, 4페이지를 요청했으면 4페이지, 8페이지를 요청했으면 7페이지 값을 갖도록 계산
		// startPage
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
			Map<String, String> searchParam = new HashMap<String, String>();
			searchParam.put("type", searchtype);
			searchParam.put("word", keyword);
			List<BoardDTO> boardList = br.search(searchParam);
					//Ripository에서 받아올때 List로 받아올테니 요래 선언 해줍니다.
			//System.out.println("bs의 searchtype"+searchtype+" keyword "+keyword);
			return boardList;
		}
	
	// interface는 new가 되지않는다. @service 선언하지않아야.
	// 어노테이션 서비스는 구현페이지에
	
	
	
	
	
	
	
	
	
	
	
	
	
	//1201 작업해둔거!
/*	public void insert(BoardDTO board) {
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
*/


}