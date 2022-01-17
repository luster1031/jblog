<%@ page
	import="com.poscoict.emaillist.dao.EmaillistDAO, com.poscoict.emaillist.vo.EmaillistVO,java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//	mysql 들어갈 때, 한글 안 깨지게
	request.setCharacterEncoding("utf-8");
	String firstName = request.getParameter("fn");
	String lastName = request.getParameter("ln");
	String email = request.getParameter("email");
	
	EmaillistVO vo = new EmaillistVO();
	vo.setFirstName(firstName);
	vo.setLastName(lastName);
	vo.setEmail(email);
	
	new EmaillistDAO().insert(vo);
	
	response.sendRedirect("/emaillist01");
%>
