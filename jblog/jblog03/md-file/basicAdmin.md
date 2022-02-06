## 블로그 기본 관리
### 1. http://localhost:8088/jblog/${사용자 아이디}/admin/basic 접속시 처리
+ Controller
    ```java
    @RequestMapping("/admin/basic")
    public String adminBasic(@PathVariable("id") String id
            , Model model) {
        blogservice.BasicAdmin(id,model);
        return "blog/blog-admin-basic";
    }
    ```
+ blog정보 불러오기
    + Service
        ```java
        public void BasicAdmin(String id, Model model) {
        Map<String, Object> map = new HashMap<>();
        
        BlogVo vo = blogrepository.getContent(id);
        map.put("blog", vo);
        model.addAttribute("map", map);
        }
        ```
        + header에 블로그 title을 map을 사용하여서, map으로 저장뒤 addAttribute했다.
----
### 2. 이미지 수정
1. 멀티파트 리졸버 설정
    >spring-servlet.xml
    ```xml
    
    <!-- 멀티파트 리졸버 -->
    <bean id="multipartResolver" 
        class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        
        <!-- 최대업로드 가능한 바이트크기 -->
        <property name="maxUploadSize" value="52428800" />
        
        <!-- 디스크에 임시 파일을 생성하기 전에 메모리에 보관할수있는 최대 바이트 크기 -->
        <!-- property name="maxInMemorySize" value="52428800" /-->

        <!-- defaultEncoding -->
        <property name="defaultEncoding" value="utf-8" />

    </bean>

    ```
2. common-fileupload, common-io 라이브러리 추가
    > pom.xml
    ```xml
    <!-- Common Fileupload -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>1.4</version>
        </dependency>
    ```
3. Controller설정
    ```java
    @RequestMapping("/admin/basic/update")
    public String adminBasicUpdate(
            BlogVo blogvo
            ,@PathVariable("id") String id
            , @RequestParam(value="logo-file") MultipartFile multipartFile
            , Model model) {
        String url = fileUploadService.BasicUpdate(multipartFile, id)
        return "redirect:/blog-admin-basic";
    }
    ```
    + fileupload하는 부분의 id를 logo-file로 했기 때문에 logo-file로 받는다.
        + ```<td><input type="file" name="logo-file"></td> ```
4. FileUploadService service추가
    ```java
    @Service
    public class FileUploadService {

        private static String SAVE_PATH ="/upload-images";
        private static String URL_BASE = "/images";
        
        public String BasicUpdate(MultipartFile multipartFile, String id) {
            return null;
        }

    }
    ```
    1. 파일이 DB에 있는지 없는지 확인 해야한다. 
        + 없으면 있는 걸로 return 
        ```java
        if(multipartFile.isEmpty()) {
        BlogVo blogVo = blogrepository.getContent(id);
        return blogVo.getLogo();
        }
        ```
    2. 파일 명 설정해주는 코드를 작성한다. 
        + exception 파일까지 만든다.
5. 새로 파일 명을 설정했거나 기존 것을 받아서 update해준다.
    ```java
    String url = fileUploadService.BasicUpdate(multipartFile,id);
    blogvo.setLogo(url);
    blogservice.updateBlog(blogvo);
    ```
    1. update service 메소드 만들기
        ```java
        public void updateBlog(BlogVo blogvo) {
        blogrepository.updateContent(blogvo);
        }
        ```
    2. update repository 메소드 만들기
        ```java
        public boolean updateContent(BlogVo blogvo) {
        return sqlSession.update("blog.update",blogvo)==1;
        }
        ```
    3. blog.xml에 update 쿼리 추가
        ```xml
        <update id="update">
        <![CDATA[
            update blog set title=#{title}, logo=#{logo};
        ]]>
        </update>
        
        ```
    4. spring-servlet.xml에 mapping추가
        ```xml
        <!-- the mvc resources tag does the magic -->
        <mvc:resources mapping="/images/**"
        location="file:/upload-images/" />

        ```
        + DB에 /images/*** 로 추가될 것이기 때문에, location을 설정해준다. 
----        
### 3. 접근 제어
+ 다른 아이디에서 블로그 볼 때, 블로그 관리 버튼 안 나오게
    ```jsp
    <c:choose>
        <c:when test="${authUser.id eq map.blog.user_id}">
            <li><a href="${pageContext.request.contextPath}/jblog/${map.id}/admin/basic">블로그 관리</a></li>
        </c:when>
    </c:choose>
    ```
+ 다른 아이디에서 url을 통해 들어올 때, 접근 제어
    1. intercepotr설정
        > spring-servlet.xml
        ```xml
            <mvc:interceptor>
                <mvc:mapping path="/jblog/**" />
                <bean class="com.poscoict.jblog.security.BlogInterceptor" />
            </mvc:interceptor>
        </mvc:interceptors>
        ```
    2. @Auth어노테이션 만들기
    3. 해당 메소드 앞에 @Auth 코드 넣기
        ```java
        @Auth
        @RequestMapping("/admin/basic")
        public String adminBasic(@PathVariable("id") String id
                , Model model) {
            blogservice.BasicAdmin(id,model);
            return "blog/blog-admin-basic";
        }
        ```
    3. 블로그 관리.jsp에 들어올 때 pathValue로 넘겼던 id받기
        ```java
        //1. pathValue에서 받았던 id받기
        @SuppressWarnings("unchecked")
        Map<String, Object> user_id = (Map<String, Object>) request.getAttribute( HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        System.out.println("[interceptor] id : "+ user_id);
        
        ```
        +  현재 로그인 id와 블로그 주인의 id를 비교하기 위해서다. 
    4. 메소드 앞에 @Auth가 있는지 확인
        ```java
        // 2. casting
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        // 3. Handler Method의 @Auth 받아오기
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
        
        //	인증이 필요 없는 것
        if (auth == null) { 
            return true;
        }
        
        //	인증이 필요한 것 (메소드 앞에 @Auth라고 되어있는 것)
        HttpSession session = request.getSession();
        System.out.println("인증이 필요한 것");
        if(session != null) {
            
            // ======= 처리 =========

        }
        ```
    5. session에 저장해둔 로그인 아이디 가지고 오기
        ```java
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
            return false;
        }
        //	수정하려는 id가 블로그 주인이라면
        if(authUser.getId().equals(user_id.get("id"))) {
            return true;
        }
        ```
    6. 접근한 id가 블로그의 주인이 아니라면
        ```java
        response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
        return false;
        ```