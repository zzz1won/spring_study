package com.mbbd.on.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {

	
	private long m_number;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_email;
	private String m_phone;
	
	private MultipartFile m_file;
	// jsp에서 컨트롤러로 넘어올 때 파일 자체를 담는 필드
	// db에는 파일을 담는게 아니라 파일 이름을 담는다. 파일 자체는 별도의 경로에 저장한다.
	private String m_filename;
	
	
}
