# 로그인
1. login
    >login.jsp
    + include설정
    ```jsp
    <c:import url="/WEB-INF/views/includes/header.jsp"/>
    ```
2. login 후 interceptor 등록
    >spring-servlet.xml
    ```xml
    <!-- Interceptors -->
        <mvc:interceptors>
            <mvc:interceptor>
                <mvc:mapping path="/user/auth"/>
                <bean class="com.poscoict.jblog.security.LoginTnterceptor"/>
            </mvc:interceptor>
        </mvc:interceptors>
    ```
3. 로그인 확인
    >LoginTnterceptor.java
    + HandlerInterceptorAdapter를 상속 받는다.
    + preHandle를 override한다. 
        ```java
            UserVo authUser = userService.CheckUser(id, password);
                if(authUser==null) {
                }
        ```
    + 회원이 있는지 확인한다. 
    + session처리 한다. 
        ```java
            HttpSession session = request.getSession(true);
            session.setAttribute("authUser", authUser);
            response.sendRedirect(request.getContextPath());
        ```
4. hearder처리
    >header.jsp
    + authUser를 session에 저장했기 때문에, 로그인 여부에 따른 header가 달라져야한다. 
        ```jsp
        <c:choose>
            <c:when test="${empty authUser }">
                <li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
                <li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
                <li><a href="">내블로그</a></li>
            </c:otherwise>
        </c:choose>
        ```
5. 