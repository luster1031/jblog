# WebConfig
### 1.auto proxy
> spring-servlet.xml
```xml
<aop:aspectj-autoproxy />
```
> WebConfig.java
```java
@EnableAspectJAutoProxy
```
------
### 2. componetScan
> spring-servlet.xml
```xml
<context:annotation-config />
	<context:component-scan
		base-package="com.poscoict.jblog.controller, com.poscoict.jblog.exception, com.poscoict.jblog.aspect" />
```
> WebConfig.java
```java
@ComponentScan({"com.poscoict.jblog.controller", "com.poscoict.jblog.exception", "com.poscoict.jblog.aspect"})
```
-----
### 3. Mvc
1. Dispatcher servlet설정
    1. com.poscoict.jblog.config 밑에 WebConfig.java만들기
    2. web.xml 설정 바꾸기
    > web.xml
    ```xml
    <!-- Dispatcher Servlet -->
    <servlet>
        <servlet-name>spring</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/spring-servlet.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    ```
    ↓
    ```xml
    <!-- Dispatcher Servlet -->
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
                <param-value>com.poscoict.jblog.config.WebConfig</param-value>
            </init-param>
        </servlet>
        <servlet-mapping>
            <servlet-name>spring</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
    ```
2. view resolver 설정
    > spring-servlet.xml
    ```xml
    <!-- ViewResolver -->
    <bean id="viewResolver"
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass"
            value="org.springframework.web.servlet.view.JstlView" />
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jsp" />
    </bean>
    ```
    > MvcConfig.java
    ```java
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    ```
3. default servlet설정
    > spring-servlet.xml
    ```xml
    <mvc:default-servlet-handler />
    ```

4. import
    ```java
    @Import({MvcConfig.class})
    ```
### 4. Mvc-Interceptor

> spring-servlet.xml
```xml
<!-- Interceptors -->
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/user/auth" />
        <bean class="com.poscoict.jblog.security.LoginInterceptor" />
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/user/logout" />
        <bean class="com.poscoict.jblog.security.LogoutInterceptor" />
    </mvc:interceptor>
    <mvc:interceptor>
        <mvc:mapping path="/jblog/**" />
        <bean class="com.poscoict.jblog.security.BlogInterceptor" />
    </mvc:interceptor>
</mvc:interceptors>
```
> SecurityConfig.java
```java
@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LoginInterceptor();
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
        registry.addInterceptor(blogInterceptor()).addPathPatterns("/jblog/**");
	}
}

```
> WebConfig.java
```java
@Import({MvcConfig.class, SecurityConfig.class})
```
+ import 추가
------
### 5. MessageConfig
> spring-servlet.xml
```xml
<!-- 6. MessageSource -->
<bean id="messageSource"
    class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basenames">
        <list>
            <value>messages/messages</value>
        </list>
    </property>
</bean>
```
1. com/poscoict/jblog/config/web에 messages.properties추가
2. MessageConfig 추가
    ```java
    public class MessageConfig {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("com/poscoict/jblog/config/web/messages_ko");
            messageSource.setDefaultEncoding("utf-8");
            return messageSource;
        }
    }
    ```
3. import 추가
    ```java
    @Import({MvcConfig.class, SecurityConfig.class, MessageConfig.class})
    ```
-----
### 6. file upload
```xml

<!-- the mvc resources tag does the magic -->
	<mvc:resources mapping="/images/**"
		location="file:/upload-images/" />

<!-- 멀티파트 리졸버 -->
<bean id="multipartResolver"
    class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 최대업로드 가능한 바이트크기 -->
    <property name="maxUploadSize" value="52428800" />
    <!-- 디스크에 임시 파일을 생성하기 전에 메모리에 보관할수있는 최대 바이트 크기 -->
    <!-- property name="maxInMemorySize" value="52428800" / -->
    <!-- defaultEncoding -->
    <property name="defaultEncoding" value="utf-8" />
</bean>
```
> FileuploadConfig.java
```java

public class FileuploadConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private Environment env;

	// MultipartResolver
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
		multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
		multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));

		return multipartResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
				.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
	}
}

```