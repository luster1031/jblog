### index.jsp
+ request.getAttribute("list")는 object로 반환하기 때문에 다운 캐스팅 해줘야 한다.

```
List<EmaillistVO> list = (List<EmaillistVO>)request.getAttribute("list");
```
+ <%=request.getContextPath() %> 동적으로 바뀌게

### EmaillistController.java
+ WEB-INF 는 아무리 client가 접근하려고 해도 안 된다. 
+ 서블릿이 받은 request, response를 받아준다. 
+ jsp가 request, response 내장되어 있는데 서블릿꺼랑 같음
+ 문자열 비교할 때
	+ actionName.equals("list")보단
	
	list.equesls(actionName)해라 -> numpointexception걸릴수도 있다. 
	
+ 소스 길이가 길어지면 객체 지향 방법이 있다. 

#### 요청 분기
```
RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
```
#### 요청 확장
```
rd.forward(request, response);
```