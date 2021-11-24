package com.pjw.ex;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
		
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	return "index";
	}

	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert()	{
		return "insert";
	}
	
	
	@RequestMapping(value = "/traineeparam", method = RequestMethod.POST)
	public void traineeparam (@RequestParam ("name") String name, @RequestParam ("age") String age,
			@RequestParam ("phone") String phone, @RequestParam ("gender") String gender, @RequestParam ("birth") String birth, 
			@RequestParam ("address") String address) 	{
	
		
		TraineeDTO t1 = new TraineeDTO();
		t1.setName(name);
		t1.setAge(age);
		t1.setPhone(phone);
		t1.setGender(gender);
		t1.setBirth(birth);
		t1.setAddress(address);
		System.out.println(t1);
		
	}
	
}
