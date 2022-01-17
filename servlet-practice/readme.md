# servlet-practices

## 1. Maven War project 만들기
1. servlet-pratices : packaging => gom
2.	maven project(helloweb) 생성:  packaging => war
3.	project 선택(오른쪽메뉴)-> java EE Tools -> generate Deployment Descriptor Stub(web.xml)
	+ maven update
	+ JavaSE-11 Library여야 함 -> 업데이트 필수
4.	web.xml 에서 xmlns="http://JAVA.sun.com/xml/ns/javaee" 수정	
	+ 대문자로 바꿔야함
5.	pom.xml 설정(helloweb 참고)
	```
		<?xml version="1.0" encoding="UTF-8"?>
		<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns="http://java.sun.com/xml/ns/javaee"
			xsi:schemaLocation="http://JAVA.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
			version="2.5">
			<display-name>helloweb</display-name>
			<welcome-file-list>
				<welcome-file>index.html</welcome-file>
				<welcome-file>index.htm</welcome-file>
				<welcome-file>index.jsp</welcome-file>
				<welcome-file>default.html</welcome-file>
				<welcome-file>default.htm</welcome-file>
				<welcome-file>default.jsp</welcome-file>
			</welcome-file-list>
		
		
			<servlet>
				<servlet-name>HelloServlet</servlet-name>
				<servlet-class>Helloweb.HelloServlet</servlet-class>
			</servlet>
		
			<!-- 멀티 맵핑 할 수 있음 하나의 서블릿에 여러개 가능 -->
			<servlet-mapping>
				<servlet-name>HelloServlet</servlet-name>
				<url-pattern>/Hello</url-pattern>
			</servlet-mapping>
			<servlet-mapping>
				<servlet-name>HelloServlet</servlet-name>
				<url-pattern>/Hello2</url-pattern>
			</servlet-mapping>
		
		</web-app>
	```
6. http://localhost:8080/helloweb/hello 접근


## 2. application
1. model1 : model + jsp(controller, view)
	- emaillist01
1. servlet-pratices : packaging => gom
2.	maven project(helloweb) 생성:  packaging => war
3.	project 선택(오른쪽메뉴)-> java EE Tools -> generate Deployment Descriptor Stub(web.xml)
	+ maven update
	+ JavaSE-11 Library여야 함 -> 업데이트 필수
4.	web.xml 에서 xmlns="http://JAVA.sun.com/xml/ns/javaee" 수정	
	+ 대문자로 바꿔야함
5.	pom.xml 설정(helloweb 참고)

```
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xmlns="http://java.sun.com/xml/ns/javaee"
		xsi:schemaLocation="http://JAVA.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
		version="2.5">
		<display-name>helloweb</display-name>
		<welcome-file-list>
			<welcome-file>index.html</welcome-file>
			<welcome-file>index.htm</welcome-file>
			<welcome-file>index.jsp</welcome-file>
			<welcome-file>default.html</welcome-file>
			<welcome-file>default.htm</welcome-file>
			<welcome-file>default.jsp</welcome-file>
		</welcome-file-list>


		<servlet>
			<servlet-name>HelloServlet</servlet-name>
			<servlet-class>Helloweb.HelloServlet</servlet-class>
		</servlet>

		<!-- 멀티 맵핑 할 수 있음 하나의 서블릿에 여러개 가능 -->
		<servlet-mapping>
			<servlet-name>HelloServlet</servlet-name>
			<url-pattern>/Hello</url-pattern>
		</servlet-mapping>
		<servlet-mapping>
			<servlet-name>HelloServlet</servlet-name>
			<url-pattern>/Hello2</url-pattern>
		</servlet-mapping>

	</web-app>
```
6. http://localhost:8080/helloweb/hello 접근
	refs/remotes/origin/master
