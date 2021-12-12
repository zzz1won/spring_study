package com.mbbd.on.controller;

import java.io.IOException;
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


import com.mbbd.on.dto.MemberDTO;
import com.mbbd.on.service.MemberService;

@RequestMapping(value = "/member/*") // 얘는 컨트롤러선언 위에 써야하나보다~

@Controller
public class MemberController {

	  
	@Autowired
	MemberService ms;

	// 회원가입 요청하는 메소드
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String saveForm() {
		return "member/save";
	}
	
	 // 프사추가하는 메소드...
	  @RequestMapping(value = "save", method = RequestMethod.POST)
	  public String savefile(@ModelAttribute MemberDTO member) throws IllegalStateException, IOException {
	  System.out.println("MemberController.save( ): " + member);
	  ms.save(member); // 4. 글쓰면 글목록으로 이동해야하니까 redirect 
		return "redirect:/board/paging";
	  }
	
	  // 회원가입시 id 중복체크 _ idDuplicate 만들어줘야 함수와 ajax 실행이 된다. 순서 알아둘 것.
		@RequestMapping (value="/idDuplicate", method=RequestMethod.POST)
		public @ResponseBody String idDuplicate(@RequestParam("m_id") String m_id)	{
			System.out.println("MemberController.idDuplicate(): "+m_id);
			String result = ms.idDuplicate(m_id);
			return result;
		}
		
	  
		// 로그인 화면 출력
		@RequestMapping (value="login",method=RequestMethod.GET)
		public String loginForm()	{
			return "member/login";
			
		}
	  
		// 로그인 처리
		  @RequestMapping (value="/loginForm", method=RequestMethod.POST)
			public String login(@ModelAttribute MemberDTO member/* , HttpSession session */)	{
			  String resultpage = ms.login(member);
				/* session.setAttribute("member", member); */
			  System.out.println(resultpage);
			  return resultpage;
		  }
	  


		  // 로그아웃 HttpSession의 하는일? + 얘는 지우고 끝인건가?
		  @RequestMapping(value="logout", method=RequestMethod.GET)
		  public String logout(HttpSession session) {
			session.invalidate();
			//세션에 저장된 데이터를 지운댄다...
			  return "redirect:/";
		  }
		  
		  
		  // 4. 일반회원 -1
		  @RequestMapping(value="memberAll",method=RequestMethod.GET)
		  public String memberAll(Model model)	{
			  List<MemberDTO> memberList = ms.memberAll();
			  model.addAttribute("memberList",memberList);
			return "member/memberAll";

		  }
		  
		// 마이페이지 수정 출력
		@RequestMapping(value="mypage", method= {RequestMethod.GET, RequestMethod.POST})
		public String memberUpdate (Model model, @RequestParam("m_number") long m_number)	{
			MemberDTO member = ms.mypage(m_number);
			model.addAttribute("member", member);
			System.out.println("memberController에서 mypage호출 memberDTO :"+member+" m_number :"+m_number);
			return "member/mypage";			
		}
			/*
		// 마이페이지 수정 처리
		@RequestMapping(value="updatepage", method=RequestMethod.POST)
		public String memberupdete (@RequestParam("m_number") long m_number, Model model)	{
			MemberDTO member = 	ms.update(m_number);
			model.addAttribute("member", member);
			System.out.println(member);
			System.out.println("memberController에서 updatepage호출 memberDTO :"+member+" m_number :"+m_number);
			return "/member/updatepage";
			/* return "/member/updatepage?m_number="+member.getM_number(); }*/
		
		//1211아하하^^
		//마이페이지 출력을 위해 로그인하는건 별개
		//update 페이지를 출력받아 정보를 가져온 후(get), 정보를 저장해서 다시 보낼 메소드를 생성한다.(post)
		@RequestMapping(value="updatepage", method=RequestMethod.GET)
		public String updatePage(Model model, @RequestParam("m_number") long m_number)	{
			MemberDTO member = ms.mypage(m_number);
			model.addAttribute("member", member);
			return "member/updatepage";
			
		}
		
		@RequestMapping(value="updatepage",method= RequestMethod.POST)
		public String memberUpdate (@ModelAttribute MemberDTO member, Model model)	{
			/*
			 * ms.mupdate(member); model.addAttribute("member",member);
			 * System.out.println("memberController에서 mupdate호출 memberDTO"+member);
			 */
			
			return "redirect:/member/mypage?m_number="+member.getM_number();
			
		}
		
		//1211 멤버All에서 멤버 삭제기능 추가
		@RequestMapping (value="delete", method=RequestMethod.GET)
		public String memberDelete (@RequestParam("m_number") long m_number, Model model)	{
			MemberDTO member = ms.delete(m_number);
			model.addAttribute("member", member);
			return "redirect:/member/memberAll";
		}
		  
		  
}	