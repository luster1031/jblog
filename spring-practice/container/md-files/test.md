# test 러너
```xml
container
	|--- src/main/java
		|--- com.poscoict.container.soundsystem
			|--- CDPlayer.java
			|--- CompactDisc.java
			|--- HighSchoolRapper2Final.java
	|--- src/main/resources
		|--- com.poscoict.container.config.soundsystem
			|--- CDPlayerConfig.xml
	|--- src/test/java
		|--- com.poscoict.container.config.soundsystem
			|--- CDPlayerConfig.java
		|--- com.poscoict.container.soundsystem
			|--- CDPlayerJavaConfigTest.java
			|--- CDPlayerXmlConfigTest.java
```

1. test 러너
    + junit 에 있는 것을 스프링 컨테이너 안에서 돌아가도록 만들기
        + 컨테이너 안 만들어도 됨
    1. pom.xml
        ```xml
        <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-test</artifactId>
                <version>${org.springframwork-version}</version>
                <scope>test</scope>
            </dependency>
        ```
    2. @RunWith(SpringJUnit4ClassRunner.class) 설정
        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations = {"classpath:com/poscoict/container/config/soundsystem/CDPlayerConfig.xml"})
        public class CDPlayerXmlConfigTest {
            
            //	주입받기
            @Autowired
            private CDPlayer cdPlayer;
            
            //	test case
            @Test
            public void testCDPlayerNotNull() {
                assertNotNull(cdPlayer);
            }
        }
        ```
        + 전체 컨테이너 만든 다음에 컨테이너 안에 빈들이 만들어짐
    3. java
        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations = {"classpath:com/poscoict/container/config/soundsystem/CDPlayerConfig.xml"})
        public class CDPlayerXmlConfigTest {
            
            //	주입받기
            @Autowired
            private CDPlayer cdPlayer;
            
            //	test case
            @Test
            public void testCDPlayerNotNull() {
                assertNotNull(cdPlayer);
            }
            
            @Test
            //	주입이 잘 되어있는 지 확인
            public void testPlay() {
                cdPlayer.play();
                assertTrue(1 == 2-1);
            }
        }
        ```
2. rule사용 - console에 프린트한 부분 test
    1. rule depondency
        ```xml
        <dependency>
                    <groupId>com.github.stefanbirkner</groupId>
                    <artifactId>system-rules</artifactId>
                    <version>1.19.0</version>
                </dependency>
        ```
    2. java
        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations = {"classpath:com/poscoict/container/config/soundsystem/CDPlayerConfig.xml"})
        public class CDPlayerXmlConfigTest {
            @Rule
            public final SystemOutRule SystemOutRule = new SystemOutRule().enableLog();
            
            //	주입받기
            @Autowired
            private CDPlayer cdPlayer;
            
            //	test case
            @Test
            public void testCDPlayerNotNull() {
                assertNotNull(cdPlayer);
            }
            
            @Test
            //	주입이 잘 되어있는 지 확인
            public void testPlay() {
                cdPlayer.play();
                assertTrue(1 == 2-1);
                assertEquals("Playing 붕붕 by 김하온",SystemOutRule.getLog().replace("\r\n", "").replace("\n", ""));
            }
        }
        ```

3. TTD
    + CDPlayerJavaConfigTest
        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(classes= {CDPlayerConfig.class})
        public class CDPlayerJavaConfigTest {
            @Rule
            public final SystemOutRule SystemOutRule = new SystemOutRule().enableLog();
            
            @Autowired
            private CDPlayer cdPlayer;
            
            @Test
            public void testCDPlayerNot() {
                assertNotNull(cdPlayer);
            }
            
            @Test
            //	주입이 잘 되어있는 지 확인
            public void testPlay() {
                cdPlayer.play();
                assertTrue(1 == 2-1);
                assertEquals("Playing 붕붕 by 김하온",SystemOutRule.getLog().replace("\r\n", "").replace("\n", ""));
            }
        }
        ```
    + 자동으로 하라고 알려줘야함
    1.  @ComponentScan
        ```java
        @Configuration
        @ComponentScan(basePackages= {"com.poscoict.container.soundsystem"})
        public class CDPlayerConfig {
            
        }
        ```
        + 내가 만든 클래스만 달 수 있다. 
        + 자동으로 컨테이너가 찾아준다.
        + 나머지는 명시적으로 componet 달아 줘야함
