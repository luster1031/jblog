package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	//	카테고리 list 가지고 오기
	public List<CategoryVo> getCategory(String id) {
		return sqlSession.selectList("category.list",id);
	}

	//	카테고리 별 post 갯수
	public List<Long> getPostCount(String id) {
		return sqlSession.selectList("category.postCount",id);
	}
	
	//	카테고리 추가
	public void insert(CategoryVo vo, String id) {
		vo.setBlog_id(id);
		sqlSession.insert("category.insert",vo);
	}

	//	카테고리 순번 작은 것
	public Long getMaxCategory(String id) {
		return sqlSession.selectOne("category.MinPostNum",id);
	}

	//	카테고리 삭제
	public int deleteCategory(Long category_no) {
		return sqlSession.delete("category.delete", category_no);
	}

}
