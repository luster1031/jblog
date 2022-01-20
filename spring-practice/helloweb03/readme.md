1. springex 웹 애플리케이션 
	1. init()
         ```xml
               <init-param>
                  <param-name>contextConfigLocation</param-name>
                  <param-value>/WEB-INF/spring-context.xml</param-value>
               </init-param>>
         ```		
         ```xml
               <context:annotation-config />
                  <context:component-scan
                     base-package="com.poscoict.helloweb.controller" />
         ```
	
         - auto configration : 자동으로 스캐닝 함 
         - 스캐닝 준비함
            - annotation에 관심을 가짐

                  ```
                  webapps
                     |--- helloweb03
                        |--- WEB-INF
                           |--- web.xml
                           |--- spring-servlet.xml
                           |--- class
                              |--- com.poscoict
                           |--- libs
                  ```

            - lib, libs, class contextpath로 잡혀있어서 여기를 뒤진다.
            - 어노테이션이 클래스에 붙어있는지 확인함. 

------
# Spring mvc
> helloController.java


```java
@RequestMapping("/hello2")
	public String hello2(String name, int no) {
		System.out.println("naem : " + name);
		System.out.println("int : " + no);
		return "/WEB-INF/views/hello2.jsp";
	}
```
-> http://localhost:8080/helloweb03/hello2?name=hi&no=1
하면 자동으로 매개변수 넣어짐

------

+ jsp로 데이터 전달할 때

   ```java
   @RequestMapping("/hello3")
      public ModelAndView hello3(String id) {
         ModelAndView mav = new ModelAndView();
         mav.addObject("id", id);	// 모델에 넣기
         mav.setViewName("/WEB-INF/views/hello2.jsp");	//	modelandView에 넣어야함
         return mav;
      }
   ```

   1. param = reqeust.getParameter("id"
   2. c.hello3(param)
   3. 나온 modelAndView를 가지고
   4. view = viewResdver.rsolve( 여기 )에 넣는다.
   5. JSTLView.render( 모델 ) 은 모델을 가지고 포워드 해버린다.


------
+ 추천하는 방법

   ```java
   @RequestMapping("/hello4")
      public String hello4(String id, Model model) {
         model.addAttribute("id",id);
         return "/WEB-INF/views/hello4.jsp";
      }
   ```
   1. 메소드를 만들 때, 파라미터에 model넣고, id넣는 것
      + 받은 모델에 id 넣으면 된다. 


-----
+ response bady 
	+ 리턴 타입이 다름
	
	```java
	//	api만들 때, 많이 안 씀
	@ResponseBody
	@RequestMapping("/hello6")
	public String hello6() {
		//브라우저로 바로 가는 string 
		return "<h1>Hello World</h1>";
	}
	``` 
	
+ redirect

   ```java
   @RequestMapping("/hello5")
      public String hello5() { 
         return "redirect:/hello";
      }
   ```
	+ 5번으로 감


+ 절대 비추

   ```java
   /*	절대 비추	*/
      // 할 수는 있지만, 보안에도 안 좋음 
      @RequestMapping("/hello7")
      public void hello(HttpServletResponse resp
            , HttpServletRequest req
            , HttpSession session
            , Writer out) throws IOException{
         String id = req.getParameter("id");
         out.write("<h1>Hello </h1>");
      }
   ```