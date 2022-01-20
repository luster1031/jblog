package com.poscoict.helloweb.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

// 어노테이션 적어줘야 한다. 
@Controller
public class helloController {

	// 요청 url을 여기에 mapping 한다.
	// contextpath를 안 적어줘도 들어온다 -> 톰캣이 어플리케이션을 분리할 때 쓰이는 것
	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}

	@RequestMapping("/hello2")
	public String hello2(String name, int no) {
		System.out.println("naem : " + name);
		System.out.println("int : " + no);
		return "/WEB-INF/views/hello2.jsp";
	}

	@RequestMapping("/hello3")
	public ModelAndView hello3(String id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("id", id);	// 모델에 넣기
		mav.setViewName("/WEB-INF/views/hello3.jsp");	//	modelandView에 넣어야함
		return mav;
	}
	
	@RequestMapping("/hello4")
	public String hello4(String id, Model model) {
		model.addAttribute("id",id);
		return "/WEB-INF/views/hello4.jsp";
	}
	
	@RequestMapping("/hello5")
	public String hello5() { 
		return "redirect:/hello";
	}
	
//	api만들 때, 많이 안 씀
	@ResponseBody
	@RequestMapping("/hello6")
	public String hello6() {
		//브라우저로 바로 가는 string 
		return "<h1>Hello World</h1>";
	}
	
	/*	절대 비추	*/
	// 할 수는 있지만, 보안에도 안 좋음 
	@RequestMapping("/hello7")
	public void hello(HttpServletResponse resp
			, HttpServletRequest req
			, HttpSession session
			, Writer out) throws IOException{
		String id = req.getParameter("id");
		out.write("<h1>Hello </h1>");
	}
}
