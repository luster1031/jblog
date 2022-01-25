# Exception
+ 에러가 올라감
	repository에서 안 받으면
	-> service
	-> Controller
	-> servlet
	-> WAS


+ checked Exception VS Unchecked Excpetion
	+ checked Exception
		+ 예외만나면 현재까지 스택, 정보를 exception 객체에 넣어서 던짐
		+ 3줄만 있으면 되는데 try catch 문 때문에 길어짐
		

+ Controller에서 처리 	
	+ 컨트롤러마다 다르게 처리하고 싶을 때, 
	
> UserController.java
	
	```
	@ExceptionHandler(Exception.class)
		public String UserControllerExceptionHandler() {
			return "error/exception";
		}
	```
	
