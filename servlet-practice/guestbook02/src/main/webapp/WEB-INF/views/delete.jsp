<%@page import="com.poscoict.guestbook.dao.GuestbookDAO"%>
<%@page import="com.poscoict.guestbook.vo.GuestbookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	int no = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");
	
	GuestbookVO vo = new GuestbookVO();
	vo.setNo(no);
	vo.setPasswd(password);
	new GuestbookDAO().delete(vo);
	response.sendRedirect("/guestbook01");
%>