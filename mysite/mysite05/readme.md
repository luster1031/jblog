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
```java
@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
```
```xml
<servlet-mapping>
		<servlet-name>spring</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```
### 4. contextConfigLocation
```java
@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
```
```xml
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
```java
@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("utf-8",false)};
	}

```
```xml
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
1. exception 던지기
	```java
	// 해당 url에 핸들러가 없으면 exception 던지겠다. 
		@Override
		protected void customizeRegistration(Dynamic registration) {
			registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		}
	```

2. DefaultServlet Handler 주석달기
	```java
	//	//	DefaultServlet Handler 
	//	@Override
	//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	//		configurer.enable();
	//	}
	```
3. assets를 /com/poscoict/mysite밑에 넣기
4. propertise 추가
	```propertise
	assets.assetsMapping=/assets/**
	assets.assetsLocation=/com/poscoict/mysite/assets/
	```

5. fileupload에 추가
	```java
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler(env.getProperty("assets.assetsMapping"))
				.addResourceLocations("classpath:" + env.getProperty("assets.assetsLocation"));
			registry
				.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
				.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
		}
	```
	+ 여기서 assets는 classpath를 사용해야한다. 

### 7. 404에러 처리
> GlobalExceptionHandler.java

```java
@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String Exception404() {
		return "error/404";
	}
```
+ 모든 에러가 여기로 가기 때문에, ```NoHandlerFoundException```를 통해서 404에러를 처리해준다. 