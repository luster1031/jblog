<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
				<!-- 임시로 0번째 -->
					<h4>${map.post[0].title }</h4>
					<p>${map.post[0].contents }<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${map.post }" var="list" varStatus="status">
					<li>
						<a href="${pageContext.request.contextPath}/jblog/${map.id}/${map.categoryNum}/${list.no}">${list.title }</a> <span>${list.reg_date }</span>
					</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}/assets${map.blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			
			<ul>
				<c:forEach items="${map.category }" var="category" varStatus="status">
					<li><a href="${pageContext.request.contextPath}/jblog/${map.id}/${category.no}">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>