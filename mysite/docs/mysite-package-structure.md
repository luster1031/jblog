### Mysite04, 05 Package Structure
```bash
[src]
   |--- [main]
                 |--- [java]
                 |	         |--- com 
                 |	         |		|--- poscoict
                 |	         |		|	   |--- config
                 |	         |		|	   |		|--- app
                 |	         |		|	   |		|		|--- DBConfig.java
                 |	         |		|	   |		|		|--- MyBatisConfig.java
                 |	         |		|	   |		|--- web
                 |	         |		|	   |		|		|--- MVCConfig.java
                 |	         |		|	   |		|		|--- SecurityConfig.java
                 |	         |		|	   |		|		|--- MessageConfig.java
                 |	         |		|	   |		|		|--- FileuploadConfig.java
                 |	         |		|	   |--- mysite
                 |	         |		|      |		|--- config
                 |	         |		|	   |		|		|--- AppConfig.java
                 |	         |		|	   |		|		|--- WebConfig.java
                 |	         |		|	   |		|--- controller
                 |	         |		|	   |		|--- service
                 |	         |		|	   |		|--- repository
                 |	         |		|  	   |		|--- vo
                 |	         |		|  	   |		|--- exception
                 |	         |		|  	   |		|--- aop
                 |
                 |--- [resources]
                 |	         |--- logback.xml	
                 |	         |--- com 
                 |	         |		|--- poscoict
                 |	         |		|		|--- mysite
                 |	         |		|		|	|--- config
                 |	         |		|		|	|		|--- app
                 |	         |		|		|	|		|		|-- jdbc.properties
                 |	         |		|		|	|		|		|-- mybatis
                 |	         |		|		|	|		|		|     |---configuration.xml
                 |	         |		|		|	|		|		|     |---mappers
                  
                 |	         |		|		|	|		|--- web
                 
```               