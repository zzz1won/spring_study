package com.mbbd.on.dto;

import java.sql.Timestamp;

import lombok.Data;
@Data
public class CommentDTO {

	private long c_number;
	private long b_number;
	private String c_writer;
	private String c_comment;
	private Timestamp c_date;
	
	
}
