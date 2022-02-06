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

	public void ContentBlog(String id, Long category, Long post, Model model) {
		Map<String, Object> map = new HashMap<>();
		BlogVo vo = blogrepository.getContent(id);	// 포스트가 몇 번째인지 받아오기,
		List<CategoryVo> categoryList = categoryrepository.getCategory(id);
		Long postNumber = (long) 1;
		if (categoryList.size() == 0) {
			category = (long) 0;
		} else {
			// 현재 카테고리의 포스트no의 최댓값
			if (post == 0) {
				post = postrepository.getMaxPost(category);
				System.out.println("post없어서" + post);
			}
			// 포스트가 몇 번째인지 받아오기,
			postNumber = postrepository.getPostNumber(post, category);
			System.out.println("[post] : " + post);
			System.out.println("[number] : " + postNumber);
			
		}
		List<PostVo> postvo = postrepository.getPost(category, postNumber);
		map.put("blog", vo);
		map.put("category", categoryList);
		map.put("post", postvo);
		map.put("categoryNum", category);
		map.put("id", id);

		System.out.println(map.toString());
		model.addAttribute("map", map);
	}

	public void DefaultContent(String id) {
		blogrepository.DefaultContent(id);
	}

	public void BasicAdmin(String id, Model model) {
		Map<String, Object> map = new HashMap<>();

		BlogVo vo = blogrepository.getContent(id);
		map.put("blog", vo);
		model.addAttribute("map", map);
	}

	public void updateBlog(BlogVo blogvo) {
		blogrepository.updateContent(blogvo);
	}

}
