<%@ page import="com.poscoict.guestbook.vo.GuestbookVO, com.poscoict.guestbook.dao.GuestbookDAO
, java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//mysql 들어갈 때, 한글 안 깨지게
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String message = request.getParameter("message");
	String passwd = request.getParameter("password");
	

	GuestbookVO vo = new GuestbookVO();
	vo.setName(name);
	vo.setMessage(message);
	vo.setPasswd(passwd);
	vo.toString();
	new GuestbookDAO().insert(vo);
	
	response.sendRedirect("/guestbook01");
%>