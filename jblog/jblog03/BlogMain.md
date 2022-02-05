# 블로그 메인
## 1. blog-header생성 - 로그인 여부에 따른 header가 달라야하기 때문.
## 2. JblogController, BlogService, BlogRepository 생성
## 3. JblogController 설정
+ ```${pageContext.request.contextPath}/${authUser.id}```로 부르기 때문에
+ ```@PathVariable```로 user_id 값을 받아야 한다.
>JblogController.java 
```java
@RequestMapping(value="/",method = RequestMethod.GET)
public String main(String id) {
}
```
## 4. BlogVo 생성
## 5. DB에서 id에 따른 블로그 정보 가져오기
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
3. 불로온 Category, post, blog정보를 map에 저장한다.
    + 현재 카테고리번호, id를 저장해준다.
        ```java
        map.put("blog", vo);
        map.put("category", categoryList);
        map.put("post", postvo);
        map.put("categoryNum", category);
        map.put("id", id);
        model.addAttribute("map", map);
        ```
    + authUser.id를 쓰면 안되는 이유
        + 로그인을 안 한 상태에서 blog를 방문 할 수 있기 때문이다.

## 6. 카테고리 클릭
1. 카테고리 클릭시 no를 url에 전달
    ```jsp
    <li><a href="${pageContext.request.contextPath}/${authUser.id}/${category.no}">${category.name }</a></li>
                
    ```
2. Controller에서 처리
    
    ```java
        @RequestMapping({"/{category}",""})
        public String main(@PathVariable("id") String id
            ,@PathVariable(required=false) String category){

        }
    ```
    + category가 안 들어와도(null) 기본값 설정이 가능함. 
    ```java
    //	post, category 초기값 설정
    category = category!=null?category:1;
    ```
## 7. 포스터 클릭 시 페이지 목록 나타내기
+ 포스터 클릭시 포스터no를 url에 전달 후 controller에서 처리

    ```java
    @RequestMapping({"/{category}/{post}","","/{category}"})
        public String main(@PathVariable("id") String id
                ,@PathVariable(required=false) Long category
                ,@PathVariable(required=false) Long post
                , Model model) {
            //	post, category 초기값 설정
            category = category!=null?category:1;
            post = post!=null?post:0;
        }

    ```
+ 첫 메인 페이지는 카테고리가 1번이고, post는 카테고리별 최댓값을 하기위해 0을 지정하였다. -> 밑에 1번 수행

<br>

+ 포스터 no로 내림차순한 순서에서, 클릭한 부분이 젤 위로가고
+ 클릭한 부분의 순번에서 5개만 출력하도록 했다. 

    1. 제일 먼저, 현재 카테고리의 포스터의 최상단 포스터의 번호를 알아야 한다. 
        ```sql
        <select id="MaxPostNum" parameterType="long" resultType="long">
        <![CDATA[
            select max(no) from post where category_no=#{category};
        ]]>
        </select>

        ```
        + 각 카테고리별로 최 상단의 포스터의 번호가 다르기 때문이다. 
            + 예를 들어) 
                + 1번 카테고리는 첫 화면을 젤 최신 (6번)부터 보여줘야하고, 
                + 2번 카테고리는 첫 화면을 젤 최신 (3번)부터 보여줘야하기 때문에, 각 카테고리의 포스트 최댓값을 구한다.
    
    <br>

    2. 그 다음, 포스터 no로 내림차순한 순서에서의 페이지 순서를 알아야한다. 
        ```sql
        <select id="findPostNum" parameterType="long" resultType="long">
        <![CDATA[
            select  p.rownum 
            from (select @Rownum :=@ROWNUM+1 as rownum, p.*
                    from post p, (select @rownum:=0) tmp
                    where category_no=#{category_no}
                    ORDER BY no DESC) p
                , post post
            where post.no=#{post_no} and p.no=#{post_no};    
        ]]>
        </select>
        ```
        + @Rownum을 이용하여, 정렬한 후의 순번을 rownum으로 정했다. 
        ![image](https://user-images.githubusercontent.com/61460836/152643795-d012044f-1515-4af2-aea5-0a2e672c3342.png)
        + 만약, 4-contents를 클릭했다면 post_no를 내림차순으로 정렬한 후의 순서는 3번이다.
            + 3번을 가지고, 내림차순한 것의 3번부터 5개 받아오기 위해서 이 과정을 수행했다. - limit이용하기 위해    
            <br>
    2. 순번을 받은 뒤 ```limit```를 이용하여 순번 밑으로 5개만 출력하였다. 
        ```sql
        <select id="findByid" parameterType="map" resultType="postvo">
        <![CDATA[
            select * 
            from post
            where category_no=#{category_no}
            ORDER BY no DESC
            limit #{start_num},5;
        ]]>
        </select>
        ```
