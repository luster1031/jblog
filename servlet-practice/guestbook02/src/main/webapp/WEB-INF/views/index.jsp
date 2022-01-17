<%@page import="com.poscoict.guestbook.vo.GuestbookVO"%>
<%@page import="com.poscoict.guestbook.dao.GuestbookDAO, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/gb?actionName=add"
		method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
			</tr>
		</table>

	</form>
	<h1>리스트</h1>
	<%
	List<GuestbookVO> list = (List<GuestbookVO>) request.getAttribute("list");
	int i = 0;
	int size = list.size();
	for (GuestbookVO vo : list) {
		System.out.println(vo.toString());
	%>
	<table width=510 border=1>
		<tr>

			<td><%=size - (i++)%></td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getReg_date()%></td>
			<td><a
				href="<%=request.getContextPath()%>/gb?actionName=deleteform?no=<%=vo.getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4><%=vo.getMessage().replace("\n", "<br/>")%></td>
		</tr>
	</table>
	<%
	}
	%>

	<br>
</body>
</html>