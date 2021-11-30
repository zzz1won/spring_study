package com.icia2.member.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia2.member.dto.MemberDTO;

@Repository
public class MemberRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public int save(MemberDTO member)	{
		System.out.println("MemberRepository의 save"+member);
		return sql.insert("Member.save", member);
	}
	
	public MemberDTO login (MemberDTO member)	{
//		System.out.println("MemberRepository의 login"+member);
//		return sql.insert("login", member);
		return sql.selectOne("Member.login",member);
	}

	public List<MemberDTO> findAll() {	
		return sql.selectList("Member.findAll");
	}

	public MemberDTO selectId(long m_number) {
		return sql.selectOne("Member.detail",m_number);
	}

	public MemberDTO deleteId(long m_number) {
		return sql.selectOne("Member.delete",m_number);
//		return sql.delete("Member.delete",m_number);
	}

//	public MemberDTO updateId(long m_number) {
//		return sql.selectOne("Member.select", m_number);
//	}

	public MemberDTO updateId(MemberDTO member) {
		System.out.println("mr.member"+member);
		return sql.selectOne("Member.update", member);

		
	}

	//idDuplicate ajax
	public String idDuplicate(String m_id) {
		return sql.selectOne("Member.idDuplicate", m_id);
	}
}
