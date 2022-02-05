package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscoict.jblog.service.BlogService;

@Controller
@RequestMapping("jblog/{id}")
public class JblogController {
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping({"/{category}/{post}","","/{category}"})
	public String main(@PathVariable("id") String id
			,@PathVariable(required=false) Long category
			,@PathVariable(required=false) Long post
			, Model model) {
		//	post, category 초기값 설정
		category = category!=null?category:1;
		post = post!=null?post:0;
		System.out.println(category + " "+ post);
		blogservice.ContentBlog(id,category, post, model);
		
		return "blog/blog-main";
	}
	
}
