### Mysite04, 05 Package Structure
```bash
mysite04
[src]
    |--- [main]
        |--- [java]
        |   |--- com.poscoict
        |       |--- config
                |   |--- app
                |   |   |--- DBConfig.java 
                |   |--- web
                |       |--- FileuploadConfig.java
                |       |--- MessageConfig.java
                |       |--- MvcConfig.java
                |       |--- SecurityConfig.java
                |--- mysite
                    |--- aspect
                    |   |--- MeasureExecutionTimeAspect.java
                    |--- config
                    |   |--- AppConfig.java
                    |   |--- MyBatisConfig.java
                    |   |--- WebConfig.java
                    |--- controller
                    |--- exception
                    |--- interceptor
                    |--- repository
                    |--- security
                    |--- service
                    |--- vo
        |--- [resource]
            |--- com
            |   |--- poscoict
            |       |--- mysite
            |           |--- config
            |               |--- app
            |               |--- web
            |                   |--- fileupload.properties
            |                   |--- messages.properties
            |--- logback.xml
```
