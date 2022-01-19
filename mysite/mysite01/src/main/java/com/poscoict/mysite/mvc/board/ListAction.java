package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count = new BoardDao().CountList();
		int pageCount = 5; // 한 페이지에 페이징 몇 개?
		int listCount = 10; // 한 페이지에 리스트 몇 개?
		int currentPage = 1; // 현재 페이지
		int totalPage = ((int) Math.ceil(count / (double) listCount)); // 총 페이지
		int startPage = 1; // 시작 페이징
		int endPage = ((int) Math.ceil(totalPage / (double) pageCount)); // 끝나는 페이징
		int nextPage = -1; // -1이면 next가 없다.

		if (request.getParameter("page") != null)
			currentPage = Integer.parseInt(request.getParameter("page"));
		if (totalPage <= pageCount) {
			endPage = totalPage;
			startPage = 1;
		} else {
			endPage = ((int) Math.ceil(currentPage / (double) pageCount)) * pageCount;
			startPage = (endPage - pageCount) + 1;
		}
		nextPage = endPage + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}
		
		int prePage = startPage - 1; // 이전 페이징
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("pageCount", pageCount);
		m.put("listCount", listCount);
		m.put("currentPage", currentPage);
		m.put("totalPage", totalPage);
		m.put("startPage", startPage);
		m.put("endPage", endPage);
		m.put("prePage", prePage);
		m.put("nextPage", nextPage);
		m.put("totalList", count);
		m.put("startnum", ((totalPage- currentPage)+1 )* listCount);

		System.out.println(m.toString());
		List<BoardVo> list = new BoardDao().findAll(currentPage - 1, listCount);

		request.setAttribute("m", m);
		request.setAttribute("list", list);
		MvcUtil.forward("board/list", request, response);
	}

}
