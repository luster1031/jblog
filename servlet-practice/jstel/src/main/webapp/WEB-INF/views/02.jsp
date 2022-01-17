<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02</title>
</head>
<body>
	<h4>scope객체의 저장 범위</h4>
	${vo.no }<br/>
	${vo.name }<br/>
	<!-- Page scope 변수 부터 나온다.  -->

	=================================<br/>
	${sessionScope.vo.no }<br/>
	${sessionScope.vo.name }<br/>
</body>
</html>