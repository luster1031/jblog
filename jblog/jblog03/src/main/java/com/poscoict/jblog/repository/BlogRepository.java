package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public void DefaultContent(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("title", "환영합니다.자신만의 글을 적어보세요!");
		map.put("logo", "/images/202214104258374.jpg");
		map.put("id", id);
		sqlSession.insert("blog.default", map);
	}

	public BlogVo getContent(String id) {
		return sqlSession.selectOne("blog.front",id);
	}

}
