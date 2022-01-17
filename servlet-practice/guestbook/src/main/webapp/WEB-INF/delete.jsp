<%@ page import="com.poscoict.guestbook.dao.GuestbookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//mysql 들어갈 때, 한글 안 깨지게
	request.setCharacterEncoding("utf-8");
	Long no = request.getParameter("no");
	String passwd = request.getParameter("password");
	
	new GuestbookDAO().delete(no, passwd);
	response.sendRedirect("/guestbook01");
%>