# 웹 애플리케이션 컨텍스트 

```
/emaillist03
	|--- WEB-INF
		|--- web.xml
		|--- applicationContext.xml
		|--- spring-servlet.xml
		|--- web.xml
		|--- classes
			|--- com..controller
				|--- EmaillistController.class
			|--- com..repository
				|--- EmaillistREpository.class
			|--- com..vo
				|--- EmaillistVo.java
```

+ 생성과 주입을 할 수 있어야 한다. 

> applicationContext.xml

```
	<context:annotation-config />
	<context:component-scan base-package="com.poscoict.emaillist.repository">
		<context:include-filter type="annotation" expression="org.springframework.stereotype.Repository" />
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Service" />
		<context:include-filter type="annotation"
			expression="org.springframework.stereotype.Component" />
	</context:component-scan>
```

annotation을 찍어 놓을 테니 클래스를 알아서 찾아라
위치는 basse-package에 적어 놈

> EmaillistRepository.java

+ 꼭 클래스 위에 @Repository 적어줘야함

