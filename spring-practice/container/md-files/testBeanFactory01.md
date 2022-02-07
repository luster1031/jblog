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
			|--- applicationContext01.xml
```

1. 컨테이너 만들기
클래스 path에서 받아오고 싶을 때
    ```java
    BeanFactory bf = new XmlBeanFactory(new ClassPathResource("com/poscoict/container/config/user/applicationContext01.xml"));
    ```
2. annotation 설정
    > applicationContext01.xml
    ```xml
    <!-- annotation을 찍어 놓을 께 클래스를 찾아라. 위치는 여기서 부터 시작해 (base-package) -->
	<context:annotation-config />
	<context:component-scan
		base-package="com.poscoict.container.user">
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Component" />
	</context:component-scan>

    ```
3. User1.java
    ```java
    @Component
    public class User1 {
        public String getName() {
            return "임한나";
        }
    }
    ```
    + Component 추가
4. XmlConfigTest.java
    ```java
    // XML Auto Config (Annotation Scanning)
    private static void testBeanFacory01() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("com/poscoict/container/config/user/applicationContext01.xml"));
        User1 user1 = bf.getBean(User1.class);
        System.out.println(user1);

        // bean id 가 자동으로 설정됨
        user1 = (User1) bf.getBean("user1");
        System.out.println(user1.getName());
        
    }
    ```
+ 클래스 이름으로 자동으로 bean id가 설정된다. 
+ 타입이 안 정해져있어서 Object로 리턴 됨
    + 캐스팅 해줘야한다. 
    ```(User1) bf.getBean("user1");```

