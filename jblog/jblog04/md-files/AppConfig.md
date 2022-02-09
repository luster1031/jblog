# AppConfig
### 1. com.poscoict.jblog.config 추가
> web.xml
```xml
<context-param>
		<param-name>contextClass</param-name>
		<param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
	</context-param>
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>com.poscoict.jblog.config.AppConfig</param-value>
	</context-param>
```
> applicatoinContext.xml
```java
@ComponentScan({"com.poscoict.jblog.service","com.poscoict.jblog.repository"})
```
-----
### 2. auto proxy
> spring-servlet.xml
```xml
<aop:aspectj-autoproxy />
```
> AppConfig
```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

}
```
+ ```@EnableAspectJAutoProxy```추가
-----
### 3. dataSource 
> applicationContext.xml
```xml
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
1. com.poscoict.config 패키지 만들기
2. DBConfig 메서드 만들기
3. /jblog04/src/main/resources 밑에 /com/poscoict/jblog/config/app 패키치 만들기
4. jdbc.properties 만들기
    + 이 곳에 DB정보를 넣기 위해서다. 
    ```properties
    jdbc.driverClassName=org.mariadb.jdbc.Driver
    jdbc.url=jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&;serverTimezone=UTC
    jdbc.username=webdb
    jdbc.password=webdb
    jdbc.initialSize=10
    jdbc.maxActive=20
    ```
5. DBConfig
    ```java
    @Configuration
    @PropertySource("classpath:com/poscoict/jblog/config/app/jdbc.properties")
    public class DBConfig {
        @Autowired
        private Environment env;

        @Bean
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            dataSource.setUrl(env.getProperty("jdbc.url"));
            dataSource.setUsername(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
            
            dataSource.setInitialSize(env.getProperty("jdbc.initialSize", Integer.class));
            dataSource.setMaxActive(env.getProperty("jdbc.maxActive", Integer.class));
            return dataSource;
        }
    }
    ```
6. import
    > AppConfig.java
    ```java
    @Import({DBConfig.class})
    ```
------
### 4. Mybatis
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
1. /jblog04/src/main/resources/com/poscoict/jblog/config/app/mybatis 패키지 만들기
2. 밑에 configuration.xml 옮기기
3. MyBatisConfig.java
    ```java
    @Configuration
    public class MyBatisConfig {
        @Bean
        public SqlSessionFactory sqlSessionFactory(
                DataSource dataSource
                , ApplicationContext applicationContext) throws Exception{
            SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
            sqlSessionFactory.setDataSource(dataSource);
            sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:com/poscoict/jblog/config/app/mybatis/configuration.xml"));
            return sqlSessionFactory.getObject();
        }
    }
    ```
4. import추가
    ```java
    @Import({DBConfig.class, MyBatisConfig.class})
    ```
    ### 5. MyBatis SqlSessionTemplate
    ```xml
    <!-- MyBatis SqlSessionTemplate -->
    <!-- 생성자 사용하는 DAO -->
    <bean id="sqlSession"
        class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory" />
    </bean>
    ```
    > MyBatisConfig.java
    ```java
    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
        
    }
    ```
