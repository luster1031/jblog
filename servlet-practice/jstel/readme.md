# EL & JSTL
+ el : 자바 코드가 아닌 문법 체계

----------------------------------

# 변수의  scope 연습
> _01Servlet.java<br>
> 01.jsp

+ el로 값 출력
	+ request 에 저장된 이름으로 접근
+ object 객체 출력
	+ null인 객체를 찍으면 어떻게 되나? -> 안 나옴
+ map 객체 출력
+ 산술 연산
+ 관계 연산
	```
		${obj == null}
	```
	이면 null은 자바 코드(자바 키워드)인데 지원을 해주긴 한다. 
	```
		${empty obj}
	```
	이게 훨씬 좋음	
+ 논리 연산
	+ attribute : 이름="값"
	+ <c:if> : el 결과가 참이면 실행 
		+ <c:if name="${name eq '이름'}">
+ 요청 파라미터를 빼 와야 할 때
	+ ${parma.(요청파라미터이름)}
	+ 안 나오면 안 찍힘
	+ <%= %> 없애야 한다. 

## Scope
### 1. scope 범위
+ 객체가 존재하는 범위

### 2. 객체가 오래 지속되는 순서
Application(Context) Scope > Session Scope > Request Scope > Page Scope 
+ Application

```
request.getServletContext().setAttribute("my", response);
```
<br>

### 3. EL이 이름으로 객체를 찾는 순서

> _02Servlet.java <br>
> 02.jsp 

+ Page Scope -→ Request Scope -→ Session Scope -→  Application(Context) Scope

+ 주의 : 같은 이름으로 여러 범위에 객체를 저장할 경우, 주의가 필요

+ session 객체 접근하기 위해서는 
	```
	${sessionScope.vo.no }
	```
	따로 써줘야 한다. 안 하면 page scope부터 접근 함<br>


## hearder.jsp el형식으로 바꾸기
```
<%=request.getContextPath()%>
↓↓
${pageContext.request.contextPath }
```

```
<%=authUser.getName()%>
↓↓
${authUser.name }
```
<br>

## JSTL
+ jstl/pom.xml추가

	```
	<!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
			<dependency>
				<groupId>jstl</groupId>
				<artifactId>jstl</artifactId>
				<version>1.2</version>
			</dependency>
	```
+ 프로젝트 Maven Dependencies에서 확인 ->  jstl-1.2.jar들어갔는 지
	
+ jstl태그 사용해서 

> _03Servlet.java <br>
> 03.jsp


<br>

+ taglib추가 

```
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
```

<br>

+ page context에 저장

```
	<%
	pageContext.setAttribute("count",3);
	%>
	↓↓
	<c:set var = "count" value = "3"/>
```

<br>

+ jsp:include

```
<jsp:include page="/WEB-INF/views/includes/header.jsp" />
↓↓↓↓
<c:import url="/WEB-INF/views/includes/header.jsp" />

```

<br>

+ if문

```
<c:choose>
	<c:when test="조건">
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
```


<br>

+ for 문

```
<c:forEach  begin="시작" end = "끝" step = "몇개씩 증가" var="이름">
</c:forEach>
```
<br>

+ replace

```
<%pageContext.setAttribute("newline", "\n"); %>
${fn:replace(vo.message,newline,"<br/>")}

```


-------------------------------------------
## jsp templet 만들기
> Preferences - jsp Files - Editor - Templates

```
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ page language="java" contentType="text/html; charset=${encoding}"
	    pageEncoding="${encoding}"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="${encoding}">
	<title>Insert title here</title>
	</head>
	<body>
	${cursor}
	</body>
	</html>
```
