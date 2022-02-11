# myiste06
1. pom.xml추가
2. webapp추가

+ (thymeleaf spring boot 쓰면 jsp 없앰)
3. java파일 옮기기
4. spring boot configuration 설정
    1. auto configuration (by spring boot)
    2. 미세 설정 조정 (by 개발자)
    3. Java Config (by 개발자)

    <br>

    1. boot strap 클래스 만들기
        + try catch는 web application에 쓰면 안 됨
            + 스레드로 톰캣이 실행 됨
            + 자동으로 컨테이너가 닫히면 안되기 때문
        + 콘솔용에만 써야함
            + Context를 없애버림
    > com.poscoict.mysite
    ```java
    @SpringBootApplication
    public class MysiteApplication {
        public static void main(String[] args) {
            SpringApplication.run(MysiteApplication.class, args);
        }
    }
    ```  
5. application.properties 
```properties
#server
server.port=8080
server.servlet.context-path=/myiste06

# devtools
spring.devtools.livereload.enabled=true

#spring mvc
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```
6. filter setting
    + spring.http.encoding.forced=false
        + 무조건 인코딩하는거 막음
    ```properties
    #http
    spring.http.encoding.character=utf-8
    spring.http.encoding.enabled=true
    spring.http.encoding.forced=false
    spring.http.encoding.forced-request=false
    spring.http.encoding.forced-response=false
    ```
7. datasource 설정
```properties
# datasource
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&;serverTimezone=UTC
spring.datasource.username=webdb
spring.datasource.password=webdb
spring.datasource.initialSize=10
spring.datasource.maxActive=20
```

8. mybatis 설정
    1. src/main/resource밑에 mybatis 옮기기
    2. configuration.xml path 설정 바꾸기
    3. application.properties
        ```properties
        # mybatis
        mybatis.config-location=classpath:mybatis/configuration.xml
        ```
9. message resource 설정
```properties
spring.messages.always-message-format=true
spring.messages.basename=messages/message_ko
spring.messages.encoding=UTF-8

```

9. multipart
```properties
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.request-size=50MB
```

10. aop
```properties
spring.aop.auto=true
spring.aop.proxy-target-class=true
```

11. logging
```properties
```

### auto configuration (by spring boot)
1. com.poscoict.mysite.config (java, resource만들기)
2. public class WebConfig implements WebMvcConfigurer 만들기
3. ArgumentResolver 등록
```java
@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
```
4. 나머지 추가
```java
@SpringBootApplication
public class WebConfig implements WebMvcConfigurer {
	// ArgumentResolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(handlerMethodArgumentResolver());
	}
	
//	Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	@Bean
	public HandlerInterceptor siteInterceptor() {
		return new SiteInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(loginInterceptor())
			.addPathPatterns("/user/auth");
		registry
			.addInterceptor(logoutInterceptor())
			.addPathPatterns("/user/logout");
		
		registry
			.addInterceptor(authInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/assets/**");

	}
}
```
5. resource mapping
    1.  WebConfig 설정
    ```java
    @SpringBootApplication
    @PropertySource("classpath:com/poscoict/mysite/config/WebConfig.properties")
    public class WebConfig implements WebMvcConfigurer {
        @Autowired
        private Environment env;
        // Resource Mapping
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(env.getProperty("assets.assetsMapping"))
                .addResourceLocations("classpath:" + env.getProperty("assets.assetsLocation"));
            registry
                .addResourceHandler(env.getProperty("fileupload.resourceMapping"))
                .addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
        }
    }
    ```
    2. WebConfig.properties
    ```properties
    fileupload.resourceMapping=/images/**
    fileupload.uploadLocation=/upload-images/
    assets.assetsMapping=/assets/**
    assets.assetsLocation=/com/poscoict/mysite/assets/
    ```
