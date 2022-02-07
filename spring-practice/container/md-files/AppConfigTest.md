# 자바로 빈 
1. 설정파일 만들기 위한 beean, Bean을 등록하기 위한 애노테이션
    1. testAppConfig01
        > AppConfigTest.java
        ```java
        public class AppConfigTest {
            public static void main(String[] args) {
                testAppConfig01();
            }
            
            public static void testAppConfig01() {
                ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig01.class);
                User user = ac.getBean(User.class);
                System.out.println(user);
            }
        }
        ```
    + @Configuration 안 달아주면 와이러닝이 안 됨
    + 컨테이너가 스캐닝 타겟에 @annotation달아 둠

    2. 
        > AppConfig01.java
        ```java
        public class AppConfig01 {
            @Bean
            public User user() {
                return new User();
            }
        }
        ```

2. 예제 
    1. CDplayerConfig.xml
        ```xml
        <context:annotation-config />
            <context:component-scan
                base-package="com.poscoict.container.soundsystem">
                <context:include-filter type="annotation"
                    expression="org.springframework.stereotype.Component" />
            </context:component-scan>
        ```
    2. CDPlayer
        ```java
        @Component
        public class CDPlayer {
            private CompactDisc cd;
            public void play(){
                cd.play();
            }
        }
        ```
    3. CompactDisc.java (interface)
        ```java
        public interface CompactDisc {
            void play();
        }
        ```
    3. HighSchoolRapper2Final.java
        ```java
        @Component
        public class HighSchoolRapper2Final implements CompactDisc {

            private String artist = "김하온";
            private String title = "붕붕";
            
            @Override
            public void play() {
                System.out.println("Playing"+  title + "by" +  artist);
            }
        }
        ```

3. junit
    1. junit
        > pom.xml
        ```xml
        <!-- test -->
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>4.12</version>
                <scope>test</scope>
            </dependency>
        ```
    2. CDPlayerXmlConfigTest.java
        ```java
        public class CDPlayerXmlConfigTest {
            @Test
            public void testHelloWorld() {
                ApplicationContext ac = new ClassPathXmlApplicationContext("com/poscoict/container/config/soundsystem/CDPlayerConfig.xml");
                CDPlayer cdplayer = ac.getBean(CDPlayer.class);
                assertNotNull(cdplayer);
            }
        }
        ```
    + 컨테이너가 잘 생성됐는지 확인
    + 이렇게 하면 스프링에서 계속 컨테이너 만들어야한다. 
