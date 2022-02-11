# jblog06
### 1. pom.xml에 추가
```xml
<properties>
    <project.build.sourceEncoding>utf-8</project.build.sourceEncoding>
    <project.reporting.outputEncodnig>utf-8</project.reporting.outputEncodnig>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>

<!-- 모둘에서의 부모가 지정된 상태에서는 이렇게 설정 -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.5.6</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>


    <!-- spring boot devtools live reload -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>


    <!-- spring boot web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- spring boot web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-aop</artifactId>
    </dependency>



    <!-- spring boot validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- mybatis starter -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.3.2</version>
    </dependency>

    <!-- Common DBCP -->
    <dependency>
        <groupId>commons-dbcp</groupId>
        <artifactId>commons-dbcp</artifactId>
        <version>1.4</version>
    </dependency>

    <!-- mariadb -->
    <dependency>
        <groupId>org.mariadb.jdbc</groupId>
        <artifactId>mariadb-java-client</artifactId>
    </dependency>

    <!-- Common Fileupload -->
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.2.1</version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>1.4</version>
    </dependency>

    <!-- jstl -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
    </dependency>

    <!-- jsp을 serlvet으로 바꿔주는 tomcat jasper -->
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-jasper</artifactId>
    </dependency>
    
    <!-- spring boot test (spring boot환경에서 테스트 할 수 있음) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>

</dependencies>

<!-- 실행이 가능 한 war : -->
<build>
    <finalName>jblog06</finalName>
    <plugins>
        <!-- 스프링 부터 메이븐 플러그인 -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <!-- 톰캣 war 배포 플러그인 -->
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>tomcat-maven-plugin</artifactId>
            <version>1.1</version>
            <configuration>
                <url>http://127.0.0.1:8080/manager/text</url>
                <path>/jblog06</path>
                <username>admin</username>
                <password>manager</password>
            </configuration>
        </plugin>
    </plugins>
</build>
``` 
### 2. webapp, assets, config, mybatis 폴더 추가 (jblog05)
### spring boot configuration 설정
### 3. bootstrap 만들기
    1. com.poscoict.jblog 패키지 만들기
    2. MyApplication 메서드 만들기
    ```java
    @SpringBootApplication
    public class MysiteApplication {
        public static void main(String[] args) {
            SpringApplication.run(MysiteApplication.class, args);
        }
    }
    ```

### 4. java files 복사해서 붙이기
+ com.poscoict.config, com.poscoict.config.web, com.poscoict.jblog.config, com.poscoict.jblog.initializer 빼고 붙이기

### 5. application.properties
1. server
    > pom.xml
    ```xml
    <!-- 톰캣 war 배포 플러그인 -->
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>tomcat-maven-plugin</artifactId>
            <version>1.1</version>
            <configuration>
                <url>http://127.0.0.1:8080/manager/text</url>
                <path>/mysite06</path>
                <username>admin</username>
                <password>manager</password>
            </configuration>
        </plugin>
    ```
    > application.properties
    ```properties
    # server
    server.port=8080
    server.servlet.context-path=/jblog06
    ```
2. devtools
    > pom.xml
    ```xml
    <!-- spring boot devtools live reload -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    ```
    > application.properties
    ```properties
    # devtools
    spring.devtools.livereload.enabled=true
    ```

3. spring mvc
    > MvcConfig.java (jblog05)
    ```java
    @Configuration
    @EnableWebMvc
    public class MvcConfig extends WebMvcConfigurerAdapter {
        
        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setPrefix("/WEB-INF/views/");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
        }
    }
    ```
    > application.properties
    ```properties
    # spring mvc
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
    ```
4. http
    > application.properties
    ```properties
    # http
    spring.http.encoding.charset=utf-8
    spring.http.encoding.enabled=true
    spring.http.encoding.force=false
    spring.http.encoding.force-request=false
    spring.http.encoding.force-response=false
    ```

5. datasource
    > jdbc.properties (jblog05)
    ```properties
    jdbc.driverClassName=org.mariadb.jdbc.Driver
    jdbc.url=jdbc:mysql://192.168.0.69:3307/jblog?characterEncoding=UTF-8&;serverTimezone=UTC
    jdbc.username=jblog
    jdbc.password=jblog
    jdbc.initialSize=10
    jdbc.maxActive=20
    ```
    > application.properties
    ```properties
    # datasource
    spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
    spring.datasource.url=jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC
    spring.datasource.username=webdb
    spring.datasource.password=webdb
    spring.datasource.initialSize=10
    spring.datasource.maxActive=20

    ```
6. mybatis
    > MyBatisConfig.java (jblog05)
    ```java
    sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/poscoict/jblog/config/app/mybatis/configuration.xml"));
    ```
    > application.properties
    ```properties
    # mybatis
    mybatis.config-location=classpath:mybatis/configuration.xml
    ```
7. message resource(Internationalization)
    > MessageConfig.java
    ```java
    public class MessageConfig {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("com/poscoict/jblog/config/web/messages");
            messageSource.setDefaultEncoding("utf-8");
            return messageSource;
        }
    }

    ```
    > application.properties
    ```properties
    # message resource(Internationalization)
    spring.messages.always-message-format=true
    spring.messages.basename=messages/messages
    spring.messages.encoding=UTF-8

    ```
8. multipart
    > fileupload.properties
    ```properties
    fileupload.maxUploadSize=52428800
    fileupload.maxInMemorySize=52428800
    fileupload.defaultEncoding=utf-8
    ```
    > application.properties
    ```properties
    # multipart
    spring.servlet.multipart.enabled=true
    spring.servlet.multipart.max-file-size=50MB
    spring.servlet.multipart.max-request-size=50M
    ```

9. aop(@EnableAspectJAutoProxy)
    > application.properties
    ```properties
    # aop(@EnableAspectJAutoProxy)
    spring.aop.auto=true
    spring.aop.proxy-target-class=true
    ```

10. logging
    > application.properties
    ```properties
    logging.config=classpath:logback.xml
    ```
    + src/main/resource 밑에 logback.xml 붙이기

11. configuration.xml
+ 경로 바꾸기
+ mybatis가 src/main/resource 밑에 있기 때문
```
```
12. WebConfig 작성 - interceptor
WebConfig
```java
@SpringBootApplication
@PropertySource("classpath:com/poscoict/jblog/config/WebConfig.properties")
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private Environment env;
	
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
	public HandlerInterceptor blogInterceptor() {
		return new BlogInterceptor();
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
			.addInterceptor(blogInterceptor())
			.addPathPatterns("/jblog/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/images/**");
		
	

	}
	
	// Resource Mapping
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:/upload-images/");

		registry
		.addResourceHandler(env.getProperty("assets.assetsMapping"))
		.addResourceLocations("classpath:" + env.getProperty("assets.assetsLocation"));

	}
}

```