# yuml 설정
1. 프로파이링- 컴파일 환경 다르게 (실행할때마다)
    1. dev
    ```yml
    ---
    # dev profile: spring boot configuration
    spring:
    config:
        activate:
            on-profile: dev
    ```
    + 실행할 때
        1.  spring-boot:dev---
        2. 
        ```yml
        spring:
        profiles:
            active: dev
        ```

    2. production
    ```yml
    ---
    # production profile: spring boot configuration
    spring:
    config:
        activate:
            on-profile: production

    ```

```yml
spring:
   profiles:
      active: dev



---

# common: spring boot configuration
spring:
   #devtools-live reload
   livereload:
      enabled: true 
   
   # mvc
   mvc:
      view:
         prefix: /WEB-INF/views/
         suffix: .jsp
   
   # message resource(Internationalization)
   messages:
      always-message-format: true
      basename: messages/messages
      encoding: UTF-8
   
   # multipart
   multpart:
      enabled: true
      max-file-size: 50MB
      max-request-size: 50MB
      
   # aop(@EnableAspectJAutoProxy)
   aop:
      auto: true
      proxy-target-class: true
      
      
---

# dev profile: spring boot configuration
spring:
   config:
      activate:
         on-profile: dev
   
   
   # datesource
   datasource:
      driver-class-name: org.mariadb.jdbc.Driver
      url: jdbc:mysql://192.168.0.69:3307/webdb?characterEncoding=UTF-8&serverTimezone=UTC
      username: webdb
      password: webdb
      hikari:
         minimum-idle: 10
         minimum-pool-size: 20
         

---

# production profile: spring boot configuration
spring:
   config:
      activate:
         on-profile: production


---

# server
server:
   port: 8080
   servlet:
      context-path: /mysite06
      encoding:
         charset: utf-8
         enabled: true
         force: false
         force-request: false
         force-response: false
         
         
         
---

# mybatis
mybatis:
   config-location: classpath:mybatis/configuration.xml



---

# logging
logging:
   config: classpath:logback.xml
```
2. yml 암호화.. 찾아보기
