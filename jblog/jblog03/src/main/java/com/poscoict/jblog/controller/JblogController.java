package com.poscoict.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.vo.BlogVo;

@Controller
@RequestMapping("jblog/{id}")
public class JblogController {
	@Autowired
	private BlogService blogservice;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	//main-page
	@RequestMapping({"/{category}/{post}","","/{category}"})
	public String main(@PathVariable("id") String id
			,@PathVariable(required=false) Long category
			,@PathVariable(required=false) Long post
			, Model model) {
		//	post, category 초기값 설정
		category = category!=null?category:1;
		post = post!=null?post:0;
		blogservice.ContentBlog(id,category, post, model);
		return "blog/blog-main";
	}
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id
			, Model model) {
		blogservice.BasicAdmin(id,model);
		return "blog/blog-admin-basic";
	}
	
	//	블로그 관리
	@Auth
	@RequestMapping("/admin/basic/update")
	public String adminBasicUpdate(
			BlogVo blogvo
			,@RequestParam(value="logo-file") MultipartFile multipartFile
			,@PathVariable("id") String id
			, Model model) {
		String url = fileUploadService.BasicUpdate(multipartFile,id);
		blogvo.setLogo(url);
		blogservice.updateBlog(blogvo);
		System.out.println("업데이트");
		return "redirect:/jblog/"+id;
	}
}
