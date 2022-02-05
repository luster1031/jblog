# 블로그 메인
1. blog-header생성 - 로그인 여부에 따른 header가 달라야하기 때문.
2. JblogController, BlogService, BlogRepository 생성
3. JblogController 설정
    + ```${pageContext.request.contextPath}/${authUser.id}```로 부르기 때문에
    + ```@PathVariable```로 user_id 값을 받아야 한다.
    >JblogController.java 
    ```java
    @RequestMapping(value="/",method = RequestMethod.GET)
	public String main(String id) {
    }
    ```
4. BlogVo 생성
5. DB에서 id에 따른 블로그 정보 가져오기
    1. 처음 가입할 때, 디폴트 값으로 title, id, 이미지 정보를 저장해 둔다. 
        > UserController.java
        ```java
        blogservice.DefaultContent(vo.getId());
        ```
        1. configuration.xml설정 - blogvo, blog.xml
            ```xml
            <configuration>
            <typeAliases>
                <typeAlias alias="uservo" type="com.poscoict.jblog.vo.UserVo" />
                <typeAlias alias="blogvo" type="com.poscoict.jblog.vo.BlogVo" />
            </typeAliases>
            <mappers>
                <mapper resource="mybatis/mappers/user.xml" />
                <mapper resource="mybatis/mappers/blog.xml" />
            </mappers>
            </configuration>
            ```
        2. blog.xml만들기 - 쿼리문 입력
    2. 첫 화면 불러오기
        1. Category정보 불러오기
            1. CategoryVo생성
                + 첫 화면에 category정보가 나와야 하기 때문
            2. CategoryRepository 생성
            3. 쿼리문 작성
        2. blog정보 들고오기
        3. post정보 불러오기
            1. postrepository 생성
            2. 쿼리문 작성
        4. jsp파일에 jstl로 작성
    
    3. 사용자 인증
