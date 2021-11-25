package com.pjw.ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pjw.ex.dto.TraineeDTO;
import com.pjw.ex.service.TraineeService;

@Controller	
public class HomeColtroller {
	
	
	//스프링이 관리하는 객체를 사용하기 _ 의존성 주입(DI, Dependency Injection)
	@Autowired
	private TraineeService ts;	

		@RequestMapping(value = "/", method = RequestMethod.GET)
		public String index()	{
			return "index";
		}
		
		@RequestMapping(value = "/insertform", method = RequestMethod.GET)
		public String insertForm()	{
			return "insert";
		}
		
		@RequestMapping(value ="/insert", method = RequestMethod.POST)
		public String insert(@ModelAttribute TraineeDTO trainee) {	//@ModelAttribute 주석처리 필수!
			System.out.println(trainee);
			
			//TraineeService에 있는 insert메소드 호출하면서 trainee 객체를 넘긴다면
			
			ts.insert(trainee);
			
			return "index";
		}
		
//		@RequestMapping(value="/findAll", method=RequestMethod.GET)
//		public String findAll()	{
//			//select * from trainee 결과를 mybatis가 List에 담아주고 그 리턴을 가져옴
//			//ts.findAll();
//			// db의 값은 List가 담아준다.
//			List<TraineeDTO> tlist = ts.findAll();
//			
//			return "findAll()";
//		}
		
		
		@RequestMapping(value="/findAll", method=RequestMethod.GET)
		public String findAll(Model model)	{
			//select * from trainee 결과를 mybatis가 List에 담아주고 그 리턴을 가져옴
			//ts.findAll();
			// db의 값은 List가 담아준다.
			List<TraineeDTO> tList = ts.findAll();
			
			model.addAttribute("tList",tList);
			
			return "findAll";
		}
		
		
		
		@RequestMapping(value="/detail",method = RequestMethod.GET)
		public String findByID(Model model, @RequestParam("t_number") long t_number)	{
			System.out.println("findById:"+ t_number);
			
			TraineeDTO trainee = ts.findById(t_number);
			model.addAttribute("trainee",trainee);
			/*
			 * TraineeService.findById() 호출
			 * TraineeRepositoty.fifindById() 호출(mapper 호출시 sql.selectOne() 메서드 사용)
			 * trainee-mapper.findById 호출  _  mapper에서 parameterType="long"이라고 하면 됨
			 * 
			 * 호출하고 역순으로 리턴을 가져와서 - 리턴타입이 뭐가 되어야하는지 관건
			 * 결과 출력은 detail.jsp에서 해당 객체값을 출력하면 됩니다.
			 * 
			 */
						
			return "detail";
		}
		
		
		
		
		
}
