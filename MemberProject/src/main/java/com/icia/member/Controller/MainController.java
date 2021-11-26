package com.icia.member.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping (value = "/", method = RequestMethod.GET)
	public String index()	{
		return "index";
	}
	
	@RequestMapping (value = "/insertform", method = RequestMethod.GET)
	public String insert()	{
		System.out.println("console에 보이는: insertfrom 호출");		
		return "insert";
	}
	
	@RequestMapping (value = "/insert", method = RequestMethod.POST)
	public String m_param()	{@M
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
