# 로그아웃
1. 로그아웃 interceptor 등록
    >spring-servlet.xml
    ```xml
    <mvc:interceptor>
                <mvc:mapping path="/user/logout"/>
                <bean class="com.poscoict.jblog.security.LogoutInterceptor"/>
            </mvc:interceptor>
    ```
2. session에 authUser삭제
    >LogoutInterceptor.java
    ```java
    HttpSession session = request.getSession();
            if(session != null) {
                session.removeAttribute("authUser");
                session.invalidate();
            }
    ```
3. sendredircet
    >LogoutInterceptor.java
    ```java
    response.sendRedirect(request.getContextPath());
    ```