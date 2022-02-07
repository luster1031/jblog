```xml
container
	|--- src/main/java
		|--- com.poscoict.container.config.user
			|--- AppConfig01.java
			|--- AppConfig02.java
		|--- com.poscoict.container.soundsystem
			|--- CDPlayer.java
			|--- CompactDisc.java
			|--- HighSchoolRapper2Final.java
		|--- com.poscoict.container.user
			|--- Friend.java
			|--- User.java
			|--- User1.java
		|--- com.poscoict.container.user.test
			|--- AppConfigTest.java
			|--- XmlConfigTest.java

	
	|--- src/main/resources
		|--- com.poscoict.container.config.soundsystem
			|--- CDPlayerConfig.xml
		|--- com.poscoict.container.config.user
			|--- applicationContext01.xml
			|--- applicationContext02.xml

	|--- src/test/java
		|--- com.poscoict.container.config.soundsystem
			|--- CDPlayerConfig.java
		|--- com.poscoict.container.soundsystem
			|--- CDPlayerJavaConfigTest.java
			|--- CDPlayerXmlConfigTest.java
```


# 1. 빈 생성
+ 컨테이너 : 빈 생성, 의존성 주입한다. 
+ 생성 방식과 주입 방식이 다르다 -> setter, getter
### 1. 기본 생성자로 빈 생성
+ 매개변수 있는 생성자가 있으면 container가 개입을 안 함
+ 생성자가 없으면 컨테이너가 만들어줌 
1. 빈 생성
    > applicationContext02.xml
    ```xml
    <!-- 기본 생성자로 빈 생성 -->
        <bean id="user" class="com.poscoict.container.user.User" />
    ```
2. id로 빈 가져오기
    ```java
    // XML Auto Config (Explicit Scanning)
    private static void testApplicationContext02() {
            BeanFactory ac = new ClassPathXmlApplicationContext("com/poscoict/container/config/user/applicationContext02.xml");
            
            User user = null;
            
            //	id로 빈 가져오기
            user = (User)ac.getBean("user");
            System.out.println(user);
        }
    ```
    + id가 ```user```

3. name으로 빈 가져오기
    1. 빈 설정
    > applicationContext02.xml
    ```xml
    <!-- 기본 생성자로 빈 생성 -->
        <bean name="usr" id="user" class="com.poscoict.container.user.User" />
    ```
    2. name으로 빈 가져오기
    > XmlConfigTest.xml
    ```java
    //	name으로 가져오기
		user = (User)ac.getBean("usr");
		System.out.println(user);
    ```
    + name이 "usr"

4. type으로 가져오기
    + 같은 타입의 빈이 두 개 이상 있으면 type으로 가져오기 실패
        1. id + type
        2. name + type으로 해야함
    1. id + type
        ```java
        user = ac.getBean("user2", User.class);
        ```

5. 파라미터 생성자로 빈 생성
    1. 파라미터 1개인 생성자로 빈 생성
        ```xml
        <!-- 파라미터 1개인 생성자로 빈 생성 -->
	    <bean id="user2" class="com.poscoict.container.user.User" />
        ```
    2. 파라미터 2개인 생성자로 빈 생성
        1. User.java
            ```java
            package com.poscoict.container.user;


            public class User {
                private Long no = 0L;
                private String name = "User_임한나";
                public User() {
                    
                }
                public User(String name) {
                    this.name = name;
                }
                public User(Long no, String name) {
                    this.no = no;
                    this.name = name;
                }
                
                @Override
                public String toString() {
                    return "User [no=" + no + ", name=" + name + "]";
                }
                
            }
            ```
        2. xml
            ```xml
            <!-- 파라미터 2개인 생성자로 빈 생성 -->
            <bean id="user3" class="com.poscoict.container.user.User" >
                <constructor-arg value="파라미터2개_이름"/>
                <constructor-arg value="3"/>
            </bean>
            ```
        3. XmlConfigTest.java
            ```java
            //	2개 파라미터 생성자로 생성된 빈 가져오기
		    user = (User)ac.getBean("usr3");
		    System.out.println(user);
            ```
        + 에러가 난다. 
        + new User("파라미터2개_이름", 3);
            + 설정된 순서대로 넣기 때문에 에러남
            + 순서대로 넣던가 따로 순서를 정해줘서 넣어줘야 함
            
            <br>

            1. 순서대로 넣기
                1. xml
                    ```xml
                    <!-- 파라미터 2개인 생성자로 빈 생성 - 순서대로-->
                    <bean id="user3" class="com.poscoict.container.user.User" >
                        <constructor-arg value="3"/>
                        <constructor-arg value="파라미터2개_이름"/>
                    </bean>
                    ```
                2. java
                    ```java
                    //	2개 파라미터 생성자로 생성된 빈 가져오기
                    user = (User)ac.getBean("user4");
                    System.out.println(user);
                    ```
            2. 순서 정해줘서 넣기
                1. xml
                    ```xml
                   <!-- 파라미터 2개인 생성자로 빈 생성 - index지정 -->
                    <bean id="user4" class="com.poscoict.container.user.User" >
                        <constructor-arg index="1" value="파라미터2개_이름"/>
                        <constructor-arg index="0" value="3"/>
                    </bean>
                    ```
                2. java
                    ```java
                    //	2개 파라미터 생성자로 생성된 빈 가져오기
                    user = (User)ac.getBean("user4");
                    System.out.println(user);
                    ```
        
    3. 파라미터 1개인 생성자
        + 생성자 직접 만든 상태
            > User.java
            ```java
            public class User {
                private String name = "User_임한나";
                public User(String name) {
                    this.name = name;
                }
                @Override
                public String toString() {
                    return "User [name=" + name + "]";
                }
            }
            ```
            > xml
            ```xml
            <!-- 파라미터 1개인 생성자로 빈 생성 -->
            <bean id="user2" class="com.poscoict.container.user.User" >
                <constructor-arg value="name바꾸기"/>
            </bean>
            ```
        + 생성자를 직접 만들면 기본 생성자가 없어서 에러남
        + container가 안 만듦
            + 기본 생성자 만들어줘야한다. 
                ```java
                package com.poscoict.container.user;


                public class User {
                    private 
                    private String name = "User_임한나";
                    public User() {
                        
                    }
                    public User(String name) {
                        this.name = name;
                    }
                    @Override
                    public String toString() {
                        return "User [name=" + name + "]";
                    }
                    
                }
                ```
6. setter 사용하기
    1. xml
        ```xml
        <bean id="user5" class="com.poscoict.container.user.User" >
            <property name = "no" value="5"/>
            <property name = "name" value="도우너"/>
        </bean>
        ```
        + 변수 값을 넣으려면 setter설정이 필요하다. 
            ```java
            package com.poscoict.container.user;


            public class User {
                private Long no = 0L;
                private String name = "User_임한나";
                public User() {
                    
                }
                public User(String name) {
                    this.name = name;
                }
                public User(Long no, String name) {
                    this.no = no;
                    this.name = name;
                }
                public Long getNo() {
                    return no;
                }
                public void setNo(Long no) {
                    this.no = no;
                }
                public String getName() {
                    return name;
                }
                public void setName(String name) {
                    this.name = name;
                }
                @Override
                public String toString() {
                    return "User [no=" + no + ", name=" + name + "]";
                }
                
            }
            ```
    2. setter를 사용해서 빈 가져오기
        ```java
        //	setter를 사용한 빈 가져오기
            user = (User)ac.getBean("user5");
            System.out.println(user);
        ```
7. setter를 사용하여 DI 가져오기
    1. User.java
        ```java
        public class User {
            private Long no = 0L;
            private String name = "User_임한나";
            private Friend friend = null;	//	의존성
        
            public User() {
            }
            public User(String name) {
                this.name = name;
            }
            public User(Long no, String name) {
                this.no = no;
                this.name = name;
            }
            public void setFriend(Friend friend) {
                this.friend = friend;
            }
            public Long getNo() {
                return no;
            }
            public void setNo(Long no) {
                this.no = no;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }

            public List<String> getFriends() {
                return friends;
            }
            public void setFriends(List<String> friends) {
                this.friends = friends;
            }
            @Override
            public String toString() {
                return "User [no=" + no + ", name=" + name + ", friend=" + friend + "]";
            }
	    }   
        ```
    2. Friend.java
        ```java
        public class Friend {
            private String name;
            
            public Friend(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Friend [name=" + name + "]";
            }
        }
        ```
    3. applicationContext02.xml
        ```xml
        <!-- DI 하기 (Wiring) -->
        <bean id="friend" class="com.poscoict.container.user.Friend" >
            <constructor-arg value="Di"/>
        </bean>
        
        <bean id="user6" class="com.poscoict.container.user.User" >
            <property name = "no" value="6"/>
            <property name = "name" value="영희"/>
            <property name = "friend" ref="friend"/>
        </bean>
        ```
        + frined의 id를 이용해서 ref한다. 
    4. XmlConfigTest.java
        ```java
        //setter를 사용한 빈 가져오기2 (DI)
		user = (User)ac.getBean("user6");
		System.out.println(user);
        ```
8. DI 하기 - wiring
    1. Friend.java
        ```java
        public class Friend {
            private String name;
            
            public Friend(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Friend [name=" + name + "]";
            }
            
        }
        ```
    2. applicationContent.xml
        ```xml
        <!-- DI 하기 (Wiring)  -->
        <bean id="friend" class="com.poscoict.container.user.Friend" >
		<constructor-arg value="Di"/>
        </bean>
        
        <bean id="user6-1" class="com.poscoict.container.user.User" >
            <constructor-arg value="61"/>
            <constructor-arg value="생성자 주입_name"/>
            <constructor-arg ref="friend"/>
        </bean>
        ```
    3. 
        ```java
        //	DI 하기 (Wiring)
		user = (User)ac.getBean("user6-1");
		System.out.println(user);
        ```
    + id로 생성자 연결
    4. 생성자 주입
        ```java
        public class User {
            private Long no = 0L;
            private String name = "User_임한나";
            private Friend friend = null;	//	의존성
            public User() {
            }
            public User(String name) {
                this.name = name;
            }
            public User(Long no, String name) {
                this.no = no;
                this.name = name;
            }
            public User(Long no, String name,Friend friend) {
                this.no = no;
                this.name = name;
                this.friend = friend;
            }

            public Friend getFriend() {
                return friend;
            }
            public void setFriend(Friend friend) {
                this.friend = friend;
            }
            public Long getNo() {
                return no;
            }
            public void setNo(Long no) {
                this.no = no;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "User [no=" + no + ", name=" + name + ", friend=" + friend + "]";
            }
        }
        ```