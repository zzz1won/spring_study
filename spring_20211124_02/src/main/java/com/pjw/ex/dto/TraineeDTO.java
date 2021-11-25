package com.pjw.ex.dto;

import java.sql.Date;
import lombok.Data;

@Data
public class TraineeDTO {
	
	private long t_number;  //번호지정해둘 아이!
	private String t_name;
	private int t_age;
	private String t_phone;
	private String t_gender;
	private Date t_birth;		//date의 import는 sql로!
	private String t_address;

}
