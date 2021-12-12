package com.mbbd.on.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mbbd.on.dto.CommentDTO;
import com.mbbd.on.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository cr;
	
	public void save(CommentDTO comment) {
		cr.save(comment);		
	}

	public List<CommentDTO> commentAll(long b_number) {
		// TODO Auto-generated method stub
		return cr.commentAll(b_number);
	}


}
