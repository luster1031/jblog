package com.poscoict.guestbook.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.guestbook.dao.GuestbookDAO;
import com.poscoict.guestbook.vo.GuestbookVO;



public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String actionName = request.getParameter("a");
		response.setContentType("text/html; charset=utf-8");
		
		response.getWriter().println("<h1>guestbook02</h2>");
		if("delete".equals(actionName)) {
			GuestbookVO vo = new GuestbookVO();
			vo.setPasswd(request.getParameter("password"));
			vo.setNo(Integer.parseInt(request.getParameter("no")));
			new GuestbookDAO().delete(vo);
			
			response.sendRedirect(request.getContextPath()+"/gb");
			
		}else if("add".equals(actionName)) {
			String name = request.getParameter("name");
			String pwd = request.getParameter("password");
			String message = request.getParameter("message");
			
			GuestbookVO vo = new GuestbookVO();
			vo.setMessage(message);
			vo.setName(name);
			vo.setPasswd(pwd);
			
			new GuestbookDAO().insert(vo);
			
			response.sendRedirect(request.getContextPath()+"/gb");
		}else if("deleteform".equals(actionName)) {
			String no = request.getParameter("no");
			
			request.setAttribute("no", no);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		}
		else {
			GuestbookVO vo = new GuestbookVO();
			List<GuestbookVO> list = new GuestbookDAO().findAll();
			
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
