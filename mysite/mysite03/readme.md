> applicationContext.xml

```
<context:component-scan
		base-package="com.poscoict.mysite.service, com.poscoict.mysite.repository">
```
+ com.poscoict.mysite.service 추가


###	 DefaultServelt 위임


> spring=servlet.xml

```
	<!-- 서블릿 컨테이너 (tomcat)의 DefaultServlet 위임(delegate) Handler-->
	<mvc:default-servlet-handler/>
```
 

### ViewResolver

```
@RequestMapping({"", "/main"})
	public String index() {
		// 	return "/WEB-INF/views/main/index.jsp"
		return "main/index";
	}
```
+ return "main/index"로만 했는데 <br>
	"/WEB-INF/views/main/index.jsp" 되게 만들기

```
<!-- ViewResolver -->
	<bean id="viewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="viewClass" value="org.springframework.web.servlet.view.JstlView" />
		<property name="prefix" value="/WEB-INF/views/" />
		<property name="suffix" value=".jsp" />
	</bean>
```


### exception
