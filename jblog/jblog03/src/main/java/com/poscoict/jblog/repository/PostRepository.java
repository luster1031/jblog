package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> getPost(Long category_no, Long start_num) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("category_no", (int) (long)category_no);
		map.put("start_num", ((int) (long)start_num)-1);
		List<PostVo> vo = sqlSession.selectList("post.findByid",map);
		System.out.println(vo.toString());
		return vo;
	}
	//	현재 포스트가 db에서 몇 번째 인지 - limit를 하기 위함
	public Long getPostNumber(Long post_no,Long category_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("category_no", category_no);
		map.put("post_no", post_no);
		return sqlSession.selectOne("post.findPostNum",map);
	}
	
	//	현재 카테고리 중 포스터의 최댓값
	public Long getMaxPost(Long category) {
		return sqlSession.selectOne("post.MaxPostNum",category);
	}
	
}
