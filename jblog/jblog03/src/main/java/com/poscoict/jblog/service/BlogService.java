package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogrepository;
	
	@Autowired
	private CategoryRepository categoryrepository;
	
	@Autowired
	private PostRepository postrepository;

	public void ContentBlog(String id, Model model) {
		Map<String, Object> map = new HashMap<>();
		BlogVo vo = blogrepository.getContent(id);
		List<CategoryVo> categoryList = categoryrepository.getCategory(id);
		List<PostVo> postvo = postrepository.getPost(categoryList.get(0).getNo());
		map.put("blog", vo);
		map.put("category", categoryList);
		map.put("post", postvo);
		System.out.println(map.toString());
		model.addAttribute("map", map);
	}

	public void DefaultContent(String id) {
		blogrepository.DefaultContent(id);
	}


}
