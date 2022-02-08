package com.poscoict.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Controller
@RequestMapping("/jblog/{id:(?!assets).*}")
public class JblogController {
	@Autowired
	private BlogService blogservice;
	@Autowired
	private CategoryService categoryservice;

	@Autowired
	private FileUploadService fileUploadService;
	
	//main-page
	@RequestMapping({"/{category}/{post}","","/{category}"})
	public String main(@PathVariable("id") String id
            ,@PathVariable(required=false) Long category
            ,@PathVariable(required=false) Long post
			, Model model) {
		//post, category 초기값 설정
        category = category!=null?category:0;
        post = post!=null?post:0;
		blogservice.ContentBlog(id,category, post, model);
		return "blog/blog-main";
	}
	
	//	블로그 기본 정보
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
		return "redirect:/jblog/"+id;
	}
	
	//	블로그 카테고리 관리
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id
			, Model model) {
		categoryservice.getContentCategory(id, model);
		return "blog/blog-admin-category";
	}
	
	//	블로그 카테고리 추가
	@Auth
	@RequestMapping(value="/admin/category/update", method = RequestMethod.POST)
	public String adminCategoryUpdate(@PathVariable("id") String id
			, @ModelAttribute CategoryVo vo
			, Model model) {
		categoryservice.updateCategory(vo,id);
		return "redirect:/jblog/"+id+"/admin/category";
	}
	
	//	블로그 카테고리 삭제
	@Auth
	@RequestMapping("/admin/category/{index}")
	public String adminCategoryDelete(@PathVariable("id") String id
			,@PathVariable Long index
			, Model model) {
		categoryservice.deleteCategory(index);
		return "redirect:/jblog/"+id+"/admin/category";
	}
	
//	블로그 글 추가 폼
	@Auth
	@RequestMapping(value="admin/write", method = RequestMethod.GET)
	public String adminWriteForm(@PathVariable("id") String id
			, Model model) {
		categoryservice.getContentCategory(id, model);
		return "blog/blog-admin-write";
	}
	
//	블로그 글 추가
	@Auth
	@RequestMapping(value="admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id
			, @ModelAttribute PostVo vo
			, Model model) {
		categoryservice.insertPost(id, vo);
		return "redirect:/jblog/"+id;
	}
}
