# 코드리뷰
+   2022.02.07
### 1. PathVariable
1. category, post처리 시 
    ```java
    @RequestMapping({"/{category}/{post}","","/{category}"})
        public String main(@PathVariable("id") String id
                ,@PathVariable("category") Optional<Long> category //--> null처리
                ,@PathVariable("post") Optional<Long> post
        ){

            // 컨트롤러 처리

        }

    ```        
2. null값이 들어 올 수 있다.
```java
Long categoryNo = 0L;
Long postNO = 0L;
if(category.isPresent()) {
    categoryNo = category.get();
}else if(post.isPresent()){
    categoryNo = category.get();
    postNO = post.get();
}
```

### 2. mapping
2. id가 "assets"가 아닌 모든 것
    ```java
    @RequestMapping("/jblog/{id:(?!assets).*}")
    ```
### 3. body 내용 불러오기
```
@Responsebody  
```
+ http요청 body를 자바 객체로 전달받을 수 있다.
