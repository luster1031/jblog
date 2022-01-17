<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01</title>
</head>
<body>
	<h1>EL 연습</h1>
	<!-- 자바 코드 -->
	<%=request.getAttribute("ival") %>
	
	<h4>el로 값 출력</h4>
	${ival }<br/>
	${lval }<br/>
	${fval }<br/>
	${bval }<br/>
	${sval }<br/>
	
	
	<h4>object객체 출력</h4>
	---${obj }---<br/>
	${user.no }<br/>
	${user.name }<br/>
	
	<h4>map 객체 출력</h4>
	${m}<br/>
	${m.ival}<br/>
	${m.lval}<br/>
	${m.fval}<br/>
	${m.bval}<br/>
	
	
	
	<h4>산술 연산</h4>
	${3*4+6/2 }<br/>
	${ival + 10 }<br/>
	
	<h4>관계 연산</h4>
	${ival == 10}<br/>
	${ival <= 5}<br/>
	<!-- null 은 자바 코드인데, 지원은 해줌  -->
	${obj == null}<br/>
	${empty obj}<br/>
	${obj != null}<br/>
	${not empty obj}<br/>
	
	
	<h4>논리 연산</h4>
	<!-- href : attribute 
		이름= "값"쌍 
	-->
	${ival == 10 && lval <100 }<br/>
	${ival == 10 || lval <100 }<br/>
	
	
	<h4>요청 파라미터</h4>
	${param.a + 10 }<br/>
	----${param.email }----<br/>
</body>
</html>