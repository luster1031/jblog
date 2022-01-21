package com.poscoict.helloweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	//	여러개 매핑
	@ResponseBody 
	@RequestMapping({"", "/main", "/main/a/b/c/d"})
	public String main() {
		return "MainController.main()";
	}
}
