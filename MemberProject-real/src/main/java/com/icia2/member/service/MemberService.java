package com.icia2.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia2.member.dto.MemberDTO;
import com.icia2.member.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository mr;

	@Autowired //s1
	private HttpSession session;
	
		public int save (MemberDTO member) {
			//써보자
			System.out.println("memberService.save()"+member);
			int result = mr.save(member);
			return result;	
		}

		public String login(MemberDTO member) {
			MemberDTO loginMember = mr.login(member);
			if (loginMember != null)	{
			
				//로그인 성공(로그인 정보(id)를 세션에 저장 > 이걸 하기위해 s1로 이동
				session.setAttribute("loginId", loginMember.getM_id());
				session.setAttribute("loginNumber", loginMember.getM_number());
				return "main";
			}	else	{
				// 로그인 실패
				return "index";
			}
		}

		public List<MemberDTO> findAll() {
			
			List<MemberDTO> mList = mr.findAll();
			
			for (MemberDTO m : mList)	{
				System.out.println(m);
			}
			
			return mList;
		}

		//detail.jsp
		public MemberDTO selectId(long m_number) {

			MemberDTO member1 = mr.selectId(m_number);
			return member1;
		}
	
		//delete.jsp
		public MemberDTO deleteId(long m_number) {
			MemberDTO member = mr.deleteId(m_number);
			
			//해당 회원일 경우에만 삭제해야하는데...

			return member;
		}
		
		//update.jsp
//		public MemberDTO update(long m_number) {
//			MemberDTO member = mr.updateId(m_number);
//			return null;
//		}

		public void updateId(MemberDTO member) {
			System.out.println("ms"+member);
			mr.updateId(member);
			
		}

		// idDuplicate ajax
		public String idDuplicate(String m_id) {
			String result = mr.idDuplicate(m_id);
			if (result == null)
				return "ok";	//조회결과가 없기에 ok! 해당 아이디는 사용 가능
			else 
				return "no";	//조회결과가 있기에 해당아이디는 사용 불가능
		}
		
		
}
