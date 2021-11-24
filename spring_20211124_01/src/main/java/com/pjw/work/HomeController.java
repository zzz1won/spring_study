package com.pjw.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {		
		return "index";
	}
	
	// join 주소요청
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join()	{
		return "join";
	}
		
	//memberjoin주소요청
	@RequestMapping(value="/inputparam", method = RequestMethod.POST)
	public String inputparam (Model model, @RequestParam("id") String id,
										  @RequestParam("pw") String pw,
										  @RequestParam("uname") String uname,
										  @RequestParam("email") String email,
										  @RequestParam("gender") String gender)
										  	{
		model.addAttribute("idValue", id);
		model.addAttribute("pwValue", pw);
		model.addAttribute("nameValue", uname);
		model.addAttribute("mailValue", email);
		model.addAttribute("genderValue", gender);

		//DTO(Data Transfer Object)
		MemberDTO m1 = new MemberDTO();
		m1.setId(id);
		m1.setPw(pw);
		m1.setUname(uname);
		m1.setEmail(email);
		m1.setGender(gender);
		
		System.out.println(m1);
		
		System.out.println("아이디: "+id + " 비밀번호:"+pw);
		System.out.println("이름: "+uname + " mail:"+email+" 성별"+gender);
		return "memberjoin";
	}
	
	
	@RequestMapping(value="/memberjoin", method = RequestMethod.POST)
	public String memberjoin ()	{
		return "memberjoin";
	}
	

	//다 하고 추가 requestparam 과정을 간편히
	@RequestMapping(value="/join2", method = RequestMethod.GET)
	public String join2()	{
		return "join2";
	}
	
	//modelAttribute로 DTO에 데이터를 담을 떄 주의할 점
	//JSP에서 사용한 name값과 DTO 필드값이 동일해야함!
	@RequestMapping(value="/inputparam2", method = RequestMethod.POST)
	public String inputparam2 (@ModelAttribute MemberDTO m1)	{
		
		System.out.println("JOIN2"+m1);
		return "memberjoin";
	}
	
	
	
	
}
