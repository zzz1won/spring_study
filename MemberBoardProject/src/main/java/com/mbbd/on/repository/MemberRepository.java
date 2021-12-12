package com.mbbd.on.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbbd.on.dto.MemberDTO;

@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;

	/*
	 * public int save(MemberDTO member) { 
	 * return sql.insert("member.save", member);
	 * }
	 */

	public void save(MemberDTO member) {
		System.out.println("MemberRepository.member(): " + member);
		sql.insert("member.save", member);
		
	}

	public String idDuplicate(String m_id) {
		return sql.selectOne("member.idDuplicate",m_id);
	}

	public MemberDTO login(MemberDTO member) {	
		return sql.selectOne("member.login",member);
	}

	public List<MemberDTO> memberAll() {
		return sql.selectList("member.memberAll");
	}

	//마이페이지
	public MemberDTO mypage(long m_number) {
		return sql.selectOne("member.mypage", m_number);
	}

/*
	public MemberDTO update(long m_number) {
		System.out.println("memberRepository에서 update m_number :"+m_number);
		return sql.selectOne("member.mupdate", m_number);
	}*/

	public void update(MemberDTO member) {
		System.out.println("memberRepository에서 update :"+member);
		sql.update("member.update", member);
		
	}

	public void mupdate(MemberDTO member) {
		System.out.println("memberRepository에서 mupdate :"+member);
		sql.update("member.mupdate", member);
	}

	public MemberDTO delete(long m_number) {
		System.out.println("memberRepository에서 delete:"+m_number);
		return sql.selectOne("member.delete",m_number);
	}
	
	


}
