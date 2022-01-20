package com.poscoict.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 어노테이션 적어줘야 한다. 
@Controller
public class helloController {
	
	//	요청 url을 여기에 mapping 한다.
	//	contextpath를 안 적어줘도 들어온다 -> 톰캣이 어플리케이션을 분리할 때 쓰이는 것
	@RequestMapping("/hello")
	public String hello() {
		return "/WEB-INF/views/hello.jsp";
	}
}
