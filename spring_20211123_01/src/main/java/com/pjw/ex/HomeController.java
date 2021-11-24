package com.pjw.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	// 리턴타입 : string 
	public String home() {
		// 기본주소 (/) 요청에 대해 home.jsp를 출력해줌.
		// 컨트롤러 메소드에서 string 리턴은 해당 'string값.jsp'를 출력하도록 처리 됨.
		// viewResolver가 해주는 기능.
		return "home";
	}
	//hello 주소를 받아줄 메서드 선언
	@RequestMapping(value="/hello")
	public String hello()	{
		System.out.println("hello 메서드 호출되었습니다");
		return "hi";
		//hi.jsp를 출력해라.
	}
	
	@RequestMapping(value="/intro")
	public String intro()	{
		System.out.println("intro 출력합니다.");
		return "intro";
	}	
	
	@RequestMapping(value="/inputs")
	public String inputs()	{
		System.out.println("inputs출력");
		return "inputs";
	}
	
	// 화면에서 전달한 파라미터를 받기
	@RequestMapping(value = "/inputparam", method=RequestMethod.POST)
	public String inputparam(@RequestParam("param1") String param1, 
							 @RequestParam("param2") String param2)	{
		System.out.println("inputparam 메서드");
		System.out.println("param1: "+param1 + "param2:"+param2);
		return "home";
	}

	
	
}
