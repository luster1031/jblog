package com.poscoict.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private BlogRepository blogrepository;

	@Autowired
	private CategoryRepository categoryrepository;

	//	카테고리 추가
	public void updateCategory(CategoryVo vo, String id) {
		categoryrepository.insert(vo, id);
	}
	
	//	카테고리 삭제
	public boolean deleteCategory(Long category_no) {
		return categoryrepository.deleteCategory(category_no)== 1;
	}

	//	카테고리 정보 보여주기
	public void getContentCategory(String id, Model model) {
		BlogVo vo = blogrepository.getContent(id);
		Map<String, Object> map = new HashMap<>();
		List<CategoryVo> categoryList = categoryrepository.getCategory(id);
		List<Long> countPost = categoryrepository.getPostCount(id);
		map.put("blog", vo);
		map.put("category", categoryList);
		map.put("id", id);
		map.put("count", countPost);
		model.addAttribute("map", map);
	}

	

}
