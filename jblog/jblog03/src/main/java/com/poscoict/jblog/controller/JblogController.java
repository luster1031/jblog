package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscoict.jblog.service.BlogService;

@Controller
@RequestMapping("/{id}")
public class JblogController {
	@Autowired
	private BlogService blogservice;
	
	@RequestMapping("")
	public String main(@PathVariable("id") String id, Model model) {
		System.out.println(id + "블로그");
		blogservice.ContentBlog(id, model);
		return "blog/blog-main";
	}
	
}
