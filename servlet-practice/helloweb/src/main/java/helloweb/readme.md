# Lifecycle

```
/helloweb
	|--- WEB-INT
		|--- classes
			|--- helloweb
				|--- LifecycleServlet.class
```

-----
# Cookie

> CookieServlet.java


+ 처음 요청하면 cookiecount = 0
	+ 쿠기 구움
	+ 응답이 간다.

+ 다음 요청 때, 브라우저가 cookiecount =1을 들고 감
	+ setcookie = 2로 응답함
