package com.mbbd.on.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mbbd.on.dto.MemberDTO;
import com.mbbd.on.repository.MemberRepository;

@Service

public class MemberService {

	@Autowired
	private MemberRepository mr;
		
	/* 회원가입처리 메소드...
	 * public int save(MemberDTO member) { int result = mr.save(member); return
	 * result; }
	 */

	/* 1207-프사가져오려고 만든 메소드 */
	public void save(MemberDTO member) throws IllegalStateException, IOException {
		// dto에 담긴 파일을 가져옴 
		MultipartFile m_file = member.getM_file();
		// 파일 이름을 가져옴(파일이름을 DB에 저장하기 위해)
		String m_filename = m_file.getOriginalFilename();
		// 파일명 중복을 피하기 위해 파일이름앞에 현재 시간값을 붙임. 
		m_filename = System.currentTimeMillis() + "-" + m_filename;
		System.out.println("MemberService.m_filename(): " + m_filename);
		// 파일 저장 경로 셋팅
		String savePath = "C:\\Users\\exo_g\\Documents\\spring_sts\\MemberBoardProject\\src\\main\\webapp\\resources\\upload\\"+m_filename;
		// mfile이 비어있지 않다면(즉 파일이 있으면) savePath에 저장을 하겠다.
		if(!m_file.isEmpty()) {
			m_file.transferTo(new File(savePath));
			}
		// 여기까지의 내용은 파일을 저장하는 과정 
		//DB에 저장하기위해 DTO에 파일이름을 담는다.		
		member.setM_filename(m_filename);
		
		// DB의 board_table에 파일이름을 저장할 b_filename 이라는 컬럼 추가(타입은 varchar(300))
		mr.save(member);
			}
	
	
	// 회원가입-id 중복체크
	// idDuplicate ajax
	public String idDuplicate(String m_id) {
		String result = mr.idDuplicate(m_id);
		if (result == null)	{
			return "ok"; 	// id 중복조회, 해당 데이터 없으니 ok.
		}	else 	{
			return "no";	// 중복된 데이터가 있으므로 no.
		}
		
	}

	//로그인
	@Autowired
	private HttpSession session;
	
	public String login(MemberDTO member) {
		MemberDTO loginMember = mr.login(member);
		// 이 작업 전에 private HttpSession session; @autowired 해주기
		if (loginMember != null) { 
			session.setAttribute("loginDTO", loginMember);
			// session.setAttribute("loginNumber", loginMember.getM_number());
			System.out.println("로그인성공");
			return "redirect:/board/paging"; //추후 /board/findAll.jsp 로 변경하기 //완료
		} else	{
		//로그인 실패
			System.out.println("로그인에 실패했습니다"); //추후 함수넣어서 아이디 혹은 비번이 잘못됐다고 알려주기.
		return "member/login";
		}
	}

	//관리자용 페이지
	public List<MemberDTO> memberAll() {
		List<MemberDTO> memberList = mr.memberAll();
		for (MemberDTO m: memberList) {
			System.out.println(m);
		}
		return memberList;
	}

	//마이페이지 출력
	public MemberDTO mypage(long m_number) {
		MemberDTO member = mr.mypage(m_number);
		return member;
	}

	//마이페이지 수정
	/*
	 * public void mypage(MemberDTO member) { mr.mypage(member); }
	 */


	/*
	public MemberDTO update(long m_number) {
		MemberDTO member = mr.update(m_number);
		System.out.println("memberService에서 update memberDTO :"+member+" m_number :"+m_number);
		return member;
	} */
	


	public void update(MemberDTO member) {
		System.out.println("서비스에서 업데이트...된거야만거야");
		mr.update(member);
	}


	public void mupdate(MemberDTO member) {
		System.out.println("memberService에서 mupdate");
		mr.mupdate(member);
	}


	public MemberDTO update(long m_number) {
		// TODO Auto-generated method stub
		return null;
	}


	public MemberDTO delete(long m_number) {
		MemberDTO member = mr.delete(m_number);
		return member;
	}






	

	
	
	
	
	
	
	
	
	
	
	
	
	}