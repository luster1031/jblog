# 1. Main servlet 설정
>MainController.java

```java
@Controller
public class MainController {
    @RequestMapping({"","/main"})
    public String main() {
        return "main/index";
    }
}
```

# 2. User servlet 설정
> UserController.java
+ login, logout, join 함수를 일단 만들어 놓는다. 
```java
@RequestMapping("/join")
public String join() {
    return "user/join";
}
@RequestMapping("/login")
public String login() {
    return "user/login";
}
@RequestMapping("/logout")
public String logout() {
    return "user/logout";
}
```


# 3. 회원 가입
1. UserController.java mapping 설정

    > spring-servlet.xml
    
    ```xml
    <context:annotation-config />
        <context:component-scan
        base-package="com.poscoict.jblog.controller, com.poscoict.jblog.exception, com.poscoict.jblog.aspect" />
    ```
    <br>
2. 
    > UserController.java
    
    ```java
    @Controller
    @RequestMapping("/user")
    ```
    + mapping설정해줌 
    <br>
    ```java
    @RequestMapping("/join")
        public String join(@ModelAttribute UserVo vo) {
            return "user/join";
        }
    ```
    + 원하는 path로 리턴함 이걸 설정해주는게 spring-servlet.xml의 viewResolver
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
    + 추가해주면, /WEB-INF/views/가 앞에 붙고, 뒤에 .jsp가 붙는다. 

    + @ModelAttribute
        + 전달하는 값을 Object형태로 바꿔 줌 
        ```java
        public String join(@ModelAttribute UserVo vo) {
        return "user/join";
        }
        ```
    + BindingResult
        + 예외처리
        + 검증 오류가 발생하는 경우 오류 내용을 보관
        + @ModelAttribute에서 데이터 바인딩 시 오류가 발생 -> 오류 정보를 BindingResult가 담은 뒤 컨트롤러가 호출 됨
        + 반드시 @ModelAttribute 어노테이션이 붙은 객체 다음에 위치해야함 
        ```java
        public String join(@ModelAttribute @Valid UserVo vo, BindingResult result) {
            if(result.hasErrors()) {
                // 오류가 있으면 처리해야하는 것
            }
        }
        ```

3. > UserVo.java    
    + id, name, password getter, setter설정


4. join.jsp 설정

    + 회원 가입 시, 빈칸이 안 들어가게 하기 위함
        1. UserVo의 null이 되면 안 되는 것들 설정
            > UserVo.java
            ```java
            @NotEmpty
            private String id;

            @NotEmpty
            @Length(min=2, max=8)
            private String name;
            
            @NotEmpty
            @Length(min=4, max=16)
            private String password;
            
            ```
        2. pom.xml validation설정
            > pom.xml
            ```xml
            <!-- validation -->
            <dependency>
                <groupId>javax.validation</groupId>
                <artifactId>validation-api</artifactId>
                <version>2.0.1.Final</version>
            </dependency>
            <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.1.0.Final</version>
            </dependency>
            ```
        
        3. join.jsp 수정
            ```jsp
                <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
                <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
                ```
            ```jsp
            <form:input path="name" />
                        <p style="text-align:left; padding-left:0; color:#f00">
                            <spring:hasBindErrors name="userVo">
                                <c:if test="${errors.hasFieldErrors('name') }">
                                    <spring:message code="${errors.getFieldError('name').codes[0] }" />
                                </c:if>
                            </spring:hasBindErrors>
                        </p>
            ```
        4. messagesourc 설정
            + .properties 확장자가 붙은 프로퍼티 파일에 입력된 국가로 국가 언어로 message가 뜬다. 
            
            > spring-servlet.xml 
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
        5. 특정 메시지 입력 
            + 이걸 해줘야, 비번을 입력 안 했을 때 입력해라고 message가 뜬다. 
            >messages_ko.properties
            ```xml
            NotEmpty.userVo.name=\uC774\uB984\uC740 \uBE44\uC5B4 \uC788\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.
            Length.userVo.name=\uC774\uB984\uC740 2~8\uC790 \uC774\uC5B4\uC57C \uD569\uB2C8\uB2E4.
            NotEmpty.userVo.id=id\uB97C \uC801\uC5B4\uC8FC\uC138\uC694
            NotEmpty.userVo.password=\uBE44\uBC00\uBC88\uD638\uB294 \uBE44\uC5B4 \uC788\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4.
            Length.userVo.password=\uBE44\uBC00\uBC88\uD638\uB294 4~16\uC790 \uC774\uC5B4\uC57C \uD569\uB2C8\uB2E4.

            user.join.label.password=\uBE44\uBC00\uBC88\uD638
            ```
        6. Controller 설정
            + annotation을 통한 검증 작업은 꼭 ```@Valid```를 적어줘야한다. 
            ```java
            @RequestMapping(value = "/join", method = RequestMethod.POST)
            public String join(@ModelAttribute @Valid UserVo vo
                    , BindingResult result
                    , Model model) {
                System.out.println(result.toString());
                if(result.hasErrors()) {
                    model.addAllAttributes(result.getModel());
                    return "user/join";
                }
                System.out.println(vo.toString());
                return "/user/joinsuccess";
            }
            ```
5. 회원 가입 정보 DB에 넣기
    1. 초기 설정, UserService, UserRepository만들기
        > UserController.java
        ```java
            @Autowired
            private UserService userService;
        ```
        <br>
        
        > UserService.java
        
        ```java
        @Service
        public class UserService {
            @Autowired
            private UserRepository userRepository;

        }
        ```
        <br>
        
        > UserRepository.java
        
        ```java
            @Repository
            public class UserRepository {
                @Autowired
                private SqlSession sqlSession;
            }

        ```
        + DB를 쓰기 위해 sqlSession을 사용한다. 
    2. DB설정
        + 라이브러리 추기
            >pom.xml
            ```xml
            <!-- Spring JDBC -->
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${org.springframework-version}</version>
            </dependency>
            ```
        + Common DBCP의 Datasouce를 bean으로 등록
            > pom.xml
            ```xml
            <!-- Common DBCP -->
            <dependency>
                <groupId>commons-dbcp</groupId>
                <artifactId>commons-dbcp</artifactId>
                <version>1.4</version>
            </dependency>
            ```
            <br>
            > applicationContext.xml

            ```xml
            <!-- Connection Pool DataSource -->
                <bean id="dataSource"
                    class="org.apache.commons.dbcp.BasicDataSource">
                    <property name="driverClassName"
                        value="org.mariadb.jdbc.Driver" />
                    <property name="url"
                        value="jdbc:mysql://192.168.0.69:3307/jblog?characterEncoding=UTF-8&amp;serverTimezone=UTC" />
                    <property name="username" value="jblog" />
                    <property name="password" value="jblog" />
                </bean>
            ```

    + mybatis설정
        + SQL을 자바 코드에서 분리해서 별도의 xml 파일 안에 작성하고 관리할 수 있는 것

        + 라이브러리 추가
            >pom.xml
            ```xml
            <!-- MyBatis -->
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis</artifactId>
                <version>3.2.2</version>
            </dependency>
            <dependency>
                <groupId>org.mybatis</groupId>
                <artifactId>mybatis-spring</artifactId>
                <version>1.2.0</version>
            </dependency>
            ```
        + SqlSessionFactoryBean 설정 
            > applicationContext.xml
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
        + SqlSessionTemplete 설정 
            + SQLSession 인터페이스를 구현한 것
                > applicationContext.xml
                ```xml
                <!-- MyBatis SqlSessionTemplate -->
                <!-- 생성자 사용하는 DAO -->
                <bean id="sqlSession"
                    class="org.mybatis.spring.SqlSessionTemplate">
                    <constructor-arg index="0" ref="sqlSessionFactory" />
                </bean>

                ```
        + UserRepository에서 SqlSessionTemplate을 DI한다. 
            > UserRepository.java
            ```java
                @Autowired
                private SqlSession sqlSession;

            ```
        + MyBatis 설정 파일 
            + 현재는 회원가입이니, user.xml만 넣겠다. 
            >configuration.xml
            ```xml
            <mappers>
                <mapper resource="mybatis/mappers/user.xml" />
            </mappers>
            ```
        + sql 매핑 파일
            + user.xml파일을 만듦
            >configuration.xml
            ```xml
            <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

            <mapper namespace="user">
            </mapper>
            ```
            + 매핑 명은 user로 
        + 타입 별칭 지정
            + UserVo를 uservo라고 설정했다. 
            >configuration.xml
            ```xml
            <typeAliases>
                <typeAlias alias="uservo" type="com.poscoict.jblog.vo.UserVo" />
            </typeAliases>
            ```
        + 다시 돌아가서 insert 쿼리문을 전달해준다. 
            >user.xml
            ```xml
            <mapper namespace="user">
                <insert id="insert" parameterType="uservo">
                <![CDATA[
                    insert into user values(#{id}, #{name}, #{password});
                ]]>
                </insert>
            </mapper>
            ```
        