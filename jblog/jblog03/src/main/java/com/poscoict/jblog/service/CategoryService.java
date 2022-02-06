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
public class CategoryService {

	@Autowired
	private BlogRepository blogrepository;

	@Autowired
	private CategoryRepository categoryrepository;

	@Autowired
	private PostRepository postrepository;

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

	public void insertPost(String id, PostVo vo) {
		postrepository.insertByCategory(vo);
	}

	//	카테고리 초기값 설정
	public void DefaultContent(String id) {
		CategoryVo vo = new CategoryVo();
		vo.setDescription("카테고리를 지정하지 않았습니다.");
		vo.setName("미분류");
		updateCategory(vo, id);
	}

	

}
