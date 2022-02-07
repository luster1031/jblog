## XML Auto Config (Annotation Scanning)
```xml
container
	|--- src/main/java
		|--- com.poscoict.container.user
			|--- User1.java
		|--- com.poscoict.container.user.test
			|--- XmlConfigTest.java

	
	|--- src/main/resources
		|--- com.poscoict.container.config.user
			|--- applicationContext02.xml
```

+ XML Bean Config (Explicit Scanning) - 명시적
+ 실무에서는 자동과 명시적을 같이 섞어 쓴다.
<br>
1. bean 설정
    > applicationContext02.xml
    ```xml
    <bean class="com.poscoict.container.user.User1" />
    ``` 
+ 명시적은 남이 만든 것을 받아오기 위해서 쓴다.
+ 컨테이너는 bean 을 만드는 일 밖에 안 함
    + 컨테이너에게 와이러닝 정보 줘야 함

2. User1.java
    ```java
    @Component
    public class User1 {
        public String getName() {
            return "임한나";
        }
    }
    ```

3. XmlConfigTest.java
```java
public static void main(String[] args) {
		// testBeanFacory01();
		testBeanFacory02();
	}
// XML Bean Config (Explicit Scanning)
private static void testBeanFacory02() {
    BeanFactory bf = new XmlBeanFactory(new ClassPathResource("com/poscoict/container/config/user/applicationContext02.xml"));

    User1 user1 = bf.getBean(User1.class);
    System.out.println(user1.getName());
}
```

+ Bean id 설정
    + bean id가 자동으로 생성되지 않음
    + id를 명시적으로 설정해야 한다. 
    > applicationContext.xml

    ```xml
    <bean id="user1" class="com.poscoict.container.user.User1" />
    ```
+ XmlConfigTest.java
    ```java
    // XML Auto Config (Annotation Scanning)
    private static void testBeanFacory01() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("com/poscoict/container/config/user/applicationContext02.xml"));
        // bean id 가 자동으로 설정됨
        user1 = (User1) bf.getBean("user1");
        System.out.println(user1.getName());
        
    }
    ```
