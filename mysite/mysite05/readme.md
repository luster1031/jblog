# web.xml
### 1. MySiteWebApplicationInitializer
### 2. contextClass등록
```java
@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class};
	}
```
```xml
<context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>com.poscoict.mysite.config.AppConfig</param-value>
	</context-param>
```

### 3. serlvet mapping
```
@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
```
```
<servlet-mapping>
		<servlet-name>spring</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```
### 4. contextConfigLocation
```
@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
```
```
<servlet>
		<servlet-name>spring</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet
		</servlet-class>
		<init-param>
			<param-name>contextClass</param-name>
			<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
		</init-param>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>com.poscoict.mysite.config.WebConfig</param-value>
		</init-param>
	</servlet>
```

### 5. Encoding Filter
```
@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("utf-8",false)};
	}

```
```
	<!-- Encoding Filter -->
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
    <filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

```
### 6. 공통 에러 메시지
```xml
// 해당 url에 핸들러가 없으면 exception 던지겠다. 
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "");
	}
```
+ defaultservlet 주석
+ 리소스 맵핑
+ 가상 url을 클래스 패스로
+ fileupload에 addrespircehamd;er가 file이 아니고 classpath로 
