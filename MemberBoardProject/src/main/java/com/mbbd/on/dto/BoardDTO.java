package com.mbbd.on.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data	//2021-12-07 dto
public class BoardDTO {

	private long b_number;
	private String b_title;
	private String m_id;
	private String b_writer;
	private String b_contents;
	private int b_hits;
	private Timestamp b_date;
	
	private MultipartFile b_file;
	private String b_filename;
	
}
