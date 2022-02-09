# xml config -> java config
```xml
    <context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframwork.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>com.poscoict.mysite.config.AppConfig</param-value>
	</context-param>
```

### 1. auto proxy 설정
```java
@EnableAspectJAutoProxy
```
```xml
<!-- proxy객체를 만드는 것 -->
<!-- auto proxy -->
<aop:aspectj-autoproxy />
```

### 2. xml auto 설정 -> java 자동 설정으로
```xml
<!-- annotation을 찍어 놓을 께 클래스를 찾아라. 위치는 여기서 부터 시작해 (base-package) -->
    <context:annotation-config />
    <context:component-scan
        base-package="com.poscoict.mysite.service, com.poscoict.mysite.repository, com.poscoict.mysite.aspect, com.poscoict.mysite.service">
        <context:include-filter type="annotation"
            expression="org.springframework.stereotype.Repository" />
        <context:include-filter type="annotation"
            expression="org.springframework.stereotype.Service" />
        <context:include-filter type="annotation"
            expression="org.springframework.stereotype.Component" />
    </context:component-scan>

```
```java
@ComponentScan({"com.poscoict.mysite.service","com.poscoict.mysite.repository","com.poscoict.mysite.aspect"})
```

### 3. myBatis 
```xml
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
```
```xml
<context-param>
    <param-name>contextClass</param-name>
    <param-value>org.springframwork.web.context.support.AnnotationConfigWebApplicationContext</param-value>
</context-param>
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>com.poscoict.mysite.config.AppConfig</param-value>
</context-param>

```


### 4. java 설정
    ```xml
        <!-- Connection Pool DataSource -->
        <bean id="dataSource"
            class="org.apache.commons.dbcp.BasicDataSource">
            <property name="driverClassName"
                value="org.mariadb.jdbc.Driver" />
            <property name="url"
                value="jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&amp;serverTimezone=UTC" />
            <property name="username" value="webdb" />
            <property name="password" value="webdb" />
        </bean>
    ```
    ```java
    @Configuration
    public class DBConfig {
        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&;serverTimezone=UTC");
            dataSource.setUsername("webdb");
            dataSource.setPassword("webdb");
            dataSource.setInitialSize(50);
            dataSource.setMaxActive(100);	//	한번에 많이 들어오면 100개 뒤에는 나머지 기다려라
            return dataSource;
        }
    }

    ```

### 5. property 빼는 작업
+ 프로젝트마다 DB, server가 달라질 수 있을 때
    > com.poscoict.mysite.config.app
    ```properties
    jdbc.driverClassName=org.mariadb.jdbc.Driver
    jdbc.url=jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&;serverTimezone=UTC
    jdbc.username=webdb
    jdbc.password=webdb
    jdbc.initialSize=10
    jdbc.maxActive=20
    ```
+ 컨테이너에게 propert 알려줘야한ㄷ다.
    ```java
    @PropertySource("classpath:com/poscoict/mysite/config/app/jdbc.properties")
    ```
+ 읽은 다음 어떤 객체가 저장함 - Environment
    + Environment를 받아와야함
        ```java
        @Autowired
        private Environment env;
        ```
    + properties값 불러오기
        ```java
        public class DBConfig {
        @Autowired
        private Environment env;
        
        // 	Connection Pool DataSource
        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            dataSource.setUrl(env.getProperty("jdbc.url"));
            dataSource.setUsername(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
            
            dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));	//	string이 아니고 integer
            dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));	//	한번에 많이 들어오면 100개 뒤에는 나머지 기다려라
            return dataSource;
        }
        ```
        + dataSource.setInitialSize는 integer가 필요하기 때문에 ```env.getProperty("jdbc.initialSize", Integer.class)```를 사용한다. 

### 6. MyBatisConfig
```xml
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
```
1. MyBatis SqlSessionFactoryBean
    ```xml
    <!-- MyBatis SqlSessionFactoryBean -->
        <!-- setter를 사용하는 DAO -->
        <bean id="sqlSessionFactory"
            class="org.mybatis.spring.SqlSessionFactoryBean">
            <property name="dataSource" ref="dataSource" />	<!-- 주입할 bean의 id (ref) -->
            <property name="configLocation"
                value="classpath:mybatis/configuration.xml" />
        </bean>
    ```
    ```java
    @Bean
    public SqlSessionFactory sqlSessionFactory(
            DataSource dataSource
            , ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/poscoict/mysite/config/app/mybatis/configuration.xml"));
        
        
        return sqlSessionFactory.getObject();
    }
    ```
+ 직접 빈 생성 메서드를 호출할 수가 없음 
    ```java
    SqlSessionFactory sqlSessionFactory(DataSource dataSource) 
    ```
> configuration.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <typeAliases>
        <typeAlias type="com.poscoict.mysite.vo.UserVo" alias="uservo" />
        <typeAlias type="com.poscoict.mysite.vo.GuestbookVo" alias="guestbookvo" />
        <typeAlias type="com.poscoict.mysite.vo.BoardVo" alias="boardvo" />
        <typeAlias type="com.poscoict.mysite.vo.SiteVo" alias="sitevo" />
        </typeAliases>	
    <mappers>
        <mapper resource ="com/poscoict/mysite/config/app/mybatis/mappers/user.xml"/>
        <mapper resource ="com/poscoict/mysite/config/app/mybatis/mappers/guestbook.xml"/>
        <mapper resource ="com/poscoict/mysite/config/app/mybatis/mappers/board.xml"/>
        <mapper resource ="com/poscoict/mysite/config/app/mybatis/mappers/site.xml"/>
    </mappers>
</configuration>

```
### 7. mvc
+ mvc 는 ben설정, 와이어링으로 안되는 얘
+ mvc 기능이 필요함 - 오버라이딩 필요
1. 기본 설정
    > WebConfig.java
    ```java
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan({"com.poscoict.mysite.controller","com.poscoict.mysite.exception"})
    @Import({MvcConfig.class})
    public class WebConfig {
        
    }
    ```
    > web.xml
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
            <param-value>com.poscoict.mysite.config.WebConfig</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    ```
    > MvcConfig.java
    ```java
    @Configuration
    @EnableWebMvc
    public class MvcConfig extends WebMvcConfigurerAdapter {
    }
    ```
2. MvcConfig
    1. ViewResolver
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
        ```java
        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver  = new  InternalResourceViewResolver();
            viewResolver.setViewClass(JstlView.class);
            viewResolver.setPrefix("/WEB-INF/views/");
            viewResolver.setSuffix(".jsp");
            return viewResolver;
        }   
        ```
    2. DefaultServlet Handler 
        + configureDefaultServletHandling를 오버라이딩 한다. 
        ```java
        @Override
        public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }
        ```
        ```xml
        <!-- 서블릿 컨테이너(tomcat)의 DefaultServlet 위임(delegate) Handler -->
        <mvc:default-servlet-handler />

        ```
3. Interceptors
    1. 보안이 아닌 부분
    + 원래 xml
        ```xml
        <mvc:interceptor>
            <mvc:mapping path="/**" />
            <bean class="com.poscoict.mysite.interceptor.SiteInterceptor" />
        </mvc:interceptor>
        ```
     + 자바로 바꾸기
        > WebConfig.java
        ```java
        @Bean
        public HandlerInterceptor siteInterceptor() {
            return new SiteInterceptor();
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry
                .addInterceptor(siteInterceptor())
                .addPathPatterns("/**");
        }
        ```
        
    2. security
        > SecurityConfig.java
        1. Argument Resolver
            ```xml
            <!-- Message Converter, Handler Mapping, Validator 생성 및 설정 -->
            <mvc:annotation-driven>
                <mvc:argument-resolvers>
                    <bean
                        class="com.poscoict.mysite.security.AuthUserHandlerMethodArgumentResolver" />
                </mvc:argument-resolvers>
            </mvc:annotation-driven>
            ```

            1. addArgumentResolvers 오버라이딩
                ```java
                public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
                    return new AuthUserHandlerMethodArgumentResolver();
                }

                @Override
                public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
                    argumentResolvers.add(handlerMethodArgumentResolver());
                }
                ```
            2. SecurityConfig import추가
            > WebConfig.java
            ```java
            @Import({MvcConfig.class, SecurityConfig.class})
            ```
        2. Interceptors
            ```xml
            <!-- Interceptors -->
            <mvc:interceptors>
                <mvc:interceptor>
                    <mvc:mapping path="/user/auth" />
                    <bean class="com.poscoict.mysite.security.LoginInterceptor" />
                </mvc:interceptor>

                <mvc:interceptor>
                    <mvc:mapping path="/user/logout" />
                    <bean class="com.poscoict.mysite.security.LogoutInterceptor" />
                </mvc:interceptor>
                <mvc:interceptor>
                    <mvc:mapping path="/admin" />
                    <bean class="com.poscoict.mysite.interceptor.SiteInterceptor" />
                </mvc:interceptor>
                <mvc:interceptor>
                    <mvc:mapping path="/**" />
                    <mvc:exclude-mapping path="/user/auth" />
                    <mvc:exclude-mapping path="/user/logout" />
                    <mvc:exclude-mapping path="/assets/**" />
                    <bean class="com.poscoict.mysite.security.AuthInterceptor" />
                </mvc:interceptor>
                
            </mvc:interceptors>
            ```
            > SecurityConfig.java
            ```java
            @Configuration
            public class SecurityConfig extends WebMvcConfigurerAdapter {
                // Argument Resolver
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
            ```
### 8. MessageSource
+ xml 코드
    ```xml
    <!-- 6. MessageSource -->
        <bean id="messageSource"
            class="org.springframework.context.support.ResourceBundleMessageSource">
            <property name="basenames">
                <list>
                    <value>messages/messages_ko</value>
                </list>
            </property>
        </bean>
    ```
+ 자바로
    ```java
    @Configuration
    public class MessageConfig {
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            messageSource.setBasename("com/poscoict/mysite/config/web/messages_ko");
            messageSource.setDefaultEncoding("utf-8");
            return messageSource;
        }

    }

    ```
### 9. 파일 업로드
1. 멀티파트 리졸버
    + 원래 xml코드
    ```java
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
    + 바꾼 java코드
    ```java
    @Configuration
    @PropertySource("classpath:com/poscoict/mysite/config/web/fileupload.properties")
    public class FileuploadConfig extends WebMvcConfigurerAdapter {
        @Autowired
        private Environment env;
        // 	MultipartResolver
        @Bean
        public MultipartResolver multipartResolver() {
            CommonsMultipartResolver multipartResolver  = new CommonsMultipartResolver();
            multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
            multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
            multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));
            
            return multipartResolver;
        }
    }
    ```
2. url-resource mapping
    ```xml
        <!-- mvc url-resource mapping -->
        <mvc:resources mapping="/images/**"	location="file:/upload-images/" />
    ```
    ```java
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry
                .addResourceHandler(env.getProperty("fileupload.resourceMapping"))
                .addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
        }
    ```
3. propertise
    ```properties
    fileupload.maxUploadSize=52428800
    fileupload.maxInMemorySize=52428800
    fileupload.defaultEncoding=utf-8
    fileupload.resourceMapping=/images/**
    fileupload.uploadLocation=/upload-images/
    ```