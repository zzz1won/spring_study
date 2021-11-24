package com.pjw.study;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	
	public String index() {
		
		return "index";
	}

	@RequestMapping(value="/login")
	public String login()	{
		System.out.println("로그인페이지");
		return "login";
	}
	
	@RequestMapping(value = "/inputparam", method=RequestMethod.POST)
	public String inputparam(Model model, @RequestParam("id") String id, 
							 @RequestParam("password") String password)	{
		System.out.println("아이디: "+id + "비밀번호:"+password);
		
		//id를 model에 담아서 welcome.jsp로 가져가기
		model.addAttribute("idValue", id);
		
		//password를 pwValue에 담고 welcome.jsp에서 출력
		model.addAttribute("pwValue", password);
		
		//+추가 val 변수를 hello에 담고 welcome.jsp에서 출력
		String val = "이제 곧 끝나요";
		model.addAttribute("hello",val);
		
		
		
		return "welcome";
	}
	
}