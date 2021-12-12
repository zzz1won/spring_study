package com.mbbd.on.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbbd.on.dto.CommentDTO;

@Repository
public class CommentRepository {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void save(CommentDTO comment) {
		sql.insert("Comment.commentSave",comment);	
	}

	public List<CommentDTO> commentAll(long b_number) {
		return sql.selectList("Comment.commentAll", b_number);
	}

}
