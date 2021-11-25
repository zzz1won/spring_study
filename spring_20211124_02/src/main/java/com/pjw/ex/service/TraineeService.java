package com.pjw.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjw.ex.dto.TraineeDTO;
import com.pjw.ex.repository.TraineeRepository;

@Service
public class TraineeService {
	
	@Autowired
	private TraineeRepository tr;		//오타유의!!!!
	public void insert(TraineeDTO trainee) {

		System.out.println("TraineeService.insert()메서드 호출");
		System.out.println("객체값: "+trainee);
		
		// insert 메소드 호출 후 리턴값을 받아와서 리턴값을 출력해봅시다.
		
		int result = tr.insert(trainee);
		
		System.out.println(result);
		
		
		
	}
	
	public List<TraineeDTO> findAll() {
		
		List<TraineeDTO> tList = tr.findAll();
		
		//tList를 출력하는 sysout
		for(TraineeDTO t : tList) {
			
		System.out.println(t);
		}
		
		return tList;

	

	
	
	
	
	
	
	
	
	
	
	}
	// 메소드 설계가 안 될때는 아래 주석을 정리해서 남겨줘야한다.
	// 리턴타입: 
	// 매개변수: 
	// 리턴값: 
	public TraineeDTO findById(long t_number) {
		TraineeDTO trainee = tr.findByID(t_number);
		System.out.println("traineeService.findById():" + trainee);
		return trainee;
	}

	
	
}
