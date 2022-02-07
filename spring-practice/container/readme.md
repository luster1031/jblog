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

# [자동 와이러닝](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/spring-practice/container/md-files/testBeanFactory01.md)
# [명시적 와이러닝](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/spring-practice/container/md-files/testBeanFactory02.md)
# [빈 생성 01](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/spring-practice/container/md-files/testApplicationContext02.md)
# [빈 생성 02](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/spring-practice/container/md-files/AppConfigTest.md)
# [테스트 러너](https://github.com/luster1031/JAVA_Expert_courses_Practice/tree/master/spring-practice/container/md-files/test.md)


+ com.poscoict.container.user
	+ 구경하기
	+ 맛보기

> src/main/java
+ com.poscoict.container.soundsystem
	+ auto wiring (java, xml config)
	
	|--- CompactDisc.java
<br>
	|--- CDPlayer.java


> src/main/resources

+ com.poscoict.container.config.soundsystem<br>
	|--- CDPlayerConfig.xml

> src/test/main<br>
+ com.poscoict.container.soundsystem<br>
|--- CDPlayerXmlConfigTest.java

<br>

+ com.poscoict.container.videosystem 
	+ explicit wiring (java, xml config)

