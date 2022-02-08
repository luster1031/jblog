# 자바로 config
### spring container
+ bean(object) 관리 = bean 생성 + Dependency 관리 (Depedency Injection - 와이어링)
    + 빌드의 의존관계 때문에 (비지니스 만들면서 생김) 
        + 어플리케이션 실행 시키면 함수가 실행되어야하고, 의존관계들을 setter해야한다. 
1. bean설정 (
    1. auto : Annotation, Auto Scan(@Component, @Service, @Controller, @Autowored) 
        + xml
        + java
    2. explicity
        + xml
        + java
)

<br>

+ 컨테이너가 알아서 함
+ 컨테이너에게 알려줄 것 - annotation
+ annotation을 설정하고 container에게 알려주는 것
    ```java
    @ComponentScan(basePackages= {"com.poscoict.container.soundsystem"})
    ```
    ```xml
    <context:annotation-config />
    ```
    + 컨테이너가 이미 annotaion 알고 있음 
    + 없애도 컨테이너가 스캔을 한다. 
        ```xml
        <context:include-filter type="annotation"
			expression="org.springframework.stereotype.Component" />
        ```
        + 생략해도 됨
+ @Autowored - 의존성을 해결해달라고 container에게 말함, 의존성이 있는 부분에 달아야함


```xml
container
	|--- src/main/java
        |--- com.poscoict.container.viedosystem
            |--- DigitalVideoDisc.java
            |--- DvdPlayer.java
            |--- ...
        |--- com.poscoict.container.config.videosystem
            |--- DvdPlayerConfig.java
		
	
	|--- src/main/resources
		|--- com.poscoict.container.config.soundsystem
			|--- CDPlayerConfig.xml
		|--- com.poscoict.container.config.user
			|--- applicationContext01.xml
			|--- applicationContext02.xml

	|--- src/test/java
        |--- com.poscoict.container.viedosystem
            |--- DvdPlayerJavaConfigTest.java
            |--- DvdPlayerXmlConfigTest.java
```


# Explicit XML Bean 설정
> Avengers.java
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    public class Avengers implements DigitalVideoDisc {
        private String studio = "MARVEL";
        private String title = "Avengers";

        @Override
        public void play() {
            System.out.println("Playing Movie "+ studio+ "MARVEL's " + title);
        }
    }
    ```
1. test case 만들기
    1. testcase할 때, 같은 위치에다가 해야한다. 소스폴더만 다르게
        ```java
        public class DvdPlayerJavaConfigTest {
            
            @Autowired
            private DigitalVideoDisc dvd;
            
            @Test
            public void testDVDNotNull() {
                
                assertNotNull(dvd);
            }
        }
        ```

        ```java
        public interface DigitalVideoDisc {
        }
        ```

2. 러너를 사용해야한다. 
    ```java
    @RunWith(SpringJUnit4ClassRunner.class)
    ```
    + 컨테이너를 만든다. 
    + 컨테이너 설정
    ```java
    @ContextConfiguration(classes= {DvdPlayerConfig.class})
    ```

3.  DvdPlayerConfig만들기
    ```java
    public interface DigitalVideoDisc {

    }
    ```

4. 주입하는 얘 만들어야함
    ```java
    @Configuration
    public class DvdPlayerConfig {
        
        @Bean
        public DigitalVideoDisc c() {
            return null;
        }
    }
    ```
# DVDPlayer 예제
### 1. ben 생성하기 - 기본
> DVDPlayerConfig.xml
+ src/main/resource
+ com.poscoict.container.config.videosystem    
```xml
<bean class="com.poscoict.container.videosystem.Avengers" />
```
> DVDPlayerXmlConfigTest.java 
+ src/test/java
+ com.poscoict.container.videosystem
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/poscoict/container/config/videosystem/DVDPlayerConfig.xml" })
public class DVDPlayerXmlConfigTest {
    @Rule
    public final SystemOutRule SystemOutRule = new SystemOutRule().enableLog();
    private DigitalVideoDisc dvd1;
```

---- 
### 2. ben 생성하기 - id 부여
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <!-- 02. 빈 생성 : id부여하기 -->
	<bean id="ironMan"
		class="com.poscoict.container.videosystem.IronMan" />
    ```
    + ```<bean id = "">```로 부여함
> DVDPlayerXmlConfigTest.java 
+ src/test/java
    + com.poscoict.container.videosystem
        ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations = { "classpath:com/poscoict/container/config/videosystem/DVDPlayerConfig.xml" })
        public class DVDPlayerXmlConfigTest {
            @Rule
            public final SystemOutRule SystemOutRule = new SystemOutRule().enableLog();

            // @Autowired
            // 예외발생
            // Explicit XML Bean 설정(Avengers)에서는 id를 자동 부여 하지 않는다.
            // 따라서 @Qualifier를 사용할 수 없다.
            private DigitalVideoDisc dvd1;

            @Qualifier("ironMan")
            @Autowired
            private DigitalVideoDisc dvd2;

            @Test
            public void testDvd2() {
                dvd2.play();
                assertNotNull(dvd2);
            }

        ```
        + dvd1이랑 같이 돌리면 예외가 발생한다. 
            + 명시적 bean 설정에서는 id를 자동으로 부여하지 않는다. 
            + 이게 무슨 말이냐면, dvd2는 id를 사용하고 있고, dvd1은 사용하지 않는다. 
                + 둘 다 DigitalVideoDiscd을 사용하는데, 아이디가 있는 거랑 없는 게 두 개 다 있어서 에러가 난다. 
            + @Qulifier를 사용할 수가 없음
        + @Autowired를 주석 처리 해줘야한다. 
-----
### 3. ben 생성 - 생성자 리터럴 사용 1
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <bean id="avangersInfinityWar"
		class="com.poscoict.container.videosystem.BlankDisc">
		<constructor-arg name="title"
			value="Avengers Infinity War" />
		<constructor-arg name="studio" value="MARVEL" />

	</bean>
    ```
> DVDPlayerXmlConfigTest.java 
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    @Qualifier("avangersInfinityWar")
	@Autowired
	private DigitalVideoDisc dvd3;

    @Test
	public void testDvd3() {
		dvd3.play();
		assertNotNull(dvd3);
	}
    ```
> BlankDisc.java
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    public BlankDisc(String title, String studio) {
            this.title = title;
            this.studio = studio;
        }
    ```
+ 생성자 추가

-----
### 4. ben 생성 - 리터럴 사용
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <beans xmlns:c="http://www.springframework.org/schema/c">
    ```
    + c xmlns 추가
    
    <br>

    ```xml
    <bean id="avengersEndgame" class="com.poscoict.container.videosystem.BlankDisc"
		c:title="Avengers Endgame" c:studio="MARVEL" />
    ```
> DVDPlayerXmlConfigTest.java 
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    @Qualifier("avengersEndgame")
	@Autowired
	private DigitalVideoDisc dvd4;

    @Test
    public void testDvd4() {
        dvd4.play();
        assertNotNull(dvd4);
    }

    ```

-----
### 5. ben 생성 - 생성자 인덱스 사용 
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <bean id="avengersAgeOfUltron" class="com.poscoict.container.videosystem.BlankDisc"
		c:_0="Avengers Age of Ultron" 
		c:_1="MARVEL" />
    ```
+ 생성자 순서대로 index 지정해주면 된다. 
-----
### 6. ben 생성 - 생성자 null argument
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <bean id="avengersCaptainAmerica" class="com.poscoict.container.videosystem.BlankDisc">
		<constructor-arg name="title" value="Avengers Captain America" />
		<constructor-arg name="studio" value="MARVEL" />
		<constructor-arg name="actors" ><null/></constructor-arg>
	</bean>
    ```
    + actors를 null로 
> BlankDisc.java
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    private List<String> actors;

    public BlankDisc(String title, String studio, List<String> actors) {
		this.title = title;
		this.studio = studio;
		this.actors = actors;
	}
    ```
+ actors, 생성자 추가


-----
### 7. ben 생성 - 생성자 Collection Argument 
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <bean id="avengersDirectorEdition" class="com.poscoict.container.videosystem.BlankDisc">
		<constructor-arg name="title" value="avengers Director Edition" />
		<constructor-arg name="studio" value="MARVEL" />
		<constructor-arg name="actors" >
			<list>
				<value>Robert Downey Jr.</value>
				<value>Scalette</value>
				<value>Chris Evans</value>
			</list>
		</constructor-arg>
	</bean>
    ```
    + artors의 값 넣기
> DVDPlayerXmlConfigTest.java 
+ src/test/java
    + com.poscoict.container.videosystem
    ```java
    ```

-----
### 8. ben 생성 - 생성자 Wiring Collection
> DVDPlayerConfig.xml
+ src/main/resource
    + com.poscoict.container.config.videosystem    
    ```xml
    <bean id="avengersTriplePack" class="com.poscoict.container.videosystem.DVDPack">
		<constructor-arg name="title" value="Avengers Triple Pack" />
		<constructor-arg name="dvds" >
			<list>
				<ref bean="avangersInfinityWar" />
				<ref bean="avengersCaptainAmerica" />
				<ref bean="avengersDirectorEdition" />
			</list>
		</constructor-arg>
	</bean>
    ```
    + artors의 값 넣기 - ```<ref bean="bean 아이디"```
