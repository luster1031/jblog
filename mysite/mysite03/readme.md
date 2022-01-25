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

+ AOP기술
	+ 모든 에러가 여기로 가는 것 
	
> spring-servlet.xml 추가해줘야함

```
<context:component-scan
		base-package="com.poscoict.mysite.controller, com.poscoict.mysite.exception" />
```

> GlobalException.java
```
@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		
		//	1. 로깅
		//  errors가 가지고 있는 버퍼 안에 에러 내용 적혀 있음
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); 
		System.out.println(errors.toString());
		
		model.addAttribute("exception", errors.toString());
		
		//	2. 사과 페이지 (HTML 응답, 정상 종료)
		return "error/exception";
		
		
	}
}
```
    