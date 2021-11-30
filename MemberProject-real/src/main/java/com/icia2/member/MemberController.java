package com.icia2.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia2.member.dto.MemberDTO;
import com.icia2.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired //3.의존성주입
	private MemberService ms; //4.
	private MemberDTO member;
	
	// 1.회원가입 페이지 출력
	@RequestMapping (value = "/save", method = RequestMethod.GET)
	public String saveForm()	{
		return "save";
	}

	// 2.회원가입 처리
	@RequestMapping (value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute MemberDTO member)	{
		System.out.println("MemberController에서 save()"+member);
		int result = ms.save(member);
		
			if (result > 0) {
				return "index";
			} else	{
				return "save";
			}
	}

		
	// 5.로그인 페이지 출력
	@RequestMapping (value = "/login", method = RequestMethod.GET)
	public String loginForm()	{
			return "login";
	}

	// 6.로그인 처리
	@RequestMapping (value = "/loginForm", method = RequestMethod.POST)
	//로그인처리 1번방식
	public String login (@ModelAttribute MemberDTO member)	{

	//로그인처리 2번방식
//	public String login (@RequestParam("m_id") String m_id, @RequestParam("m_password") String m_password, )	{
		
		String resultpage = ms.login(member);
		
		return resultpage;

	}

	// 7. 로그아웃 처리
	@RequestMapping (value="/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//세션에 저장된 데이터를 지움.
		session.invalidate();
		return "index";
		
	}


	// 8. findAll
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET)
	public String findAll(Model model)	{
		//select * from member 결과를 mybatis가 List에 담아주고 그 리턴을 가져옴
		//ms.findAll();
		// db의 값은 List가 담아준다.
		List<MemberDTO> mList = ms.findAll();
		model.addAttribute("mList",mList);
		
		return "findAll";
	}

	
	// 9. detail
	@RequestMapping(value="/detail", method=RequestMethod.GET)
//	public String SelectId(@ModelAttribute MemberDTO member ${m_number})	{
	public String selectId(Model model, @RequestParam("m_number") long m_number)	{
		MemberDTO member = ms.selectId(m_number);
		model.addAttribute("member",member);
		
		return "detail";
		
	}
	

	// 10. delete
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Model model, @RequestParam("m_number") long m_number)	{
		MemberDTO member = ms.deleteId(m_number);
		model.addAttribute("member", member);
		return "redirect:/findAll";
	}

	//	11.update 화면 요청
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String updateForm(Model model, @RequestParam("m_number") long m_number )	{
		MemberDTO member = ms.selectId(m_number);
		model.addAttribute("member", member);
		return "update";
		
	}
	
	// 11-2 update 처리
	@RequestMapping(value="/updateId",method=RequestMethod.POST)
	public String update(@ModelAttribute MemberDTO member, Model model)	{
		ms.updateId(member);
		model.addAttribute("member",member);
		// 1. DB에서 데이터를 가지고와서 detail.jsp로
		// member = ms.selectId(member.getM_number());
		// model.addAttribute("member",member);
		// return "detail";
		
		// 2.컨트롤러의 detail 주소요청
		return "redirect:/detail?m_number="+member.getM_number();
//		return "/detail?m_number=${member.m_number}";
		
	}
	
	//12 아이디 중복체크
	// @ResponseBody : 컨트롤러에서 String 리턴을 하게 되면 스프링은 해당 String 값에 .jsp를 붙여서 화면을 출력
	// 하지만 @ResponseBody를 불이면 String 그대로를 리턴할 수 있음.
	@RequestMapping (value="/idDuplicate", method=RequestMethod.POST)
	public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id)	{
		System.out.println("MemberController.idDuplicate(): "+m_id);
		String result = ms.idDuplicate(m_id);
		return result; //ok or no 라는 값이 들어있을 것...
	}
	
	//13 ajax로 상세조회 하하 망할
	@RequestMapping(value="/detailAjax", method=RequestMethod.POST)
	public @ResponseBody MemberDTO detailAjax (@RequestParam("m_number") long m_number)	{
		MemberDTO member = ms.selectId(m_number);
		return member;
	}

}
