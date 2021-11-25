package com.pjw.ex.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pjw.ex.dto.TraineeDTO;

@Repository			//spring이 객체를 관리해주기 위해 import하
public class TraineeRepository {

	
	@Autowired
	private SqlSessionTemplate sql; //이 클래스를 이용해서 mapper를 호출한다.
	public int insert(TraineeDTO trainee) {
		return sql.insert("Trainee.insertTrainee", trainee);
		
	}
	public List<TraineeDTO> findAll() {
		
		return sql.selectList("Trainee.findAll");
	}

	/*
	 * @Autowired private SqlSessionTemplate sql; //이 클래스를 이용해서 mapper를 호출한다. public
	 * long insert(TraineeDTO trainee) { return
	 * sql.insert("findTrainee.findIdTrainee", trainee);
	 */
	/* 안천재 지원의 뻘짓....ㅎ 
	 * }
	 */
	
	public TraineeDTO findByID(long t_number) {
		return sql.selectOne("Trainee.findById", t_number);
	}

}
