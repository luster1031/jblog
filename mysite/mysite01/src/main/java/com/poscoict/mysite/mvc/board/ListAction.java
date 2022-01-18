package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징
		int pageCount = 10;
		int currentPage = 2;
		int nextPage = -1; 	//	-1이면 next가 없다. 
		int startPage = 3;
		int prePage = 2;
		
		/*
			Map<K, V> m;
			m.put())
		*/
		
		MvcUtil.forward("board/list", request, response);
	}

}
