# 블로그 글쓰기
### 1. path 설정
```jsp
<li><a href="${pageContext.request.contextPath}/jblog/${map.blog.user_id}/admin/write">글작성</a></li>
```
----
### 2. 글 추가 폼 
1. controller 추가
    ```java
    //	블로그 글 추가 폼
        @Auth
        @RequestMapping(value="admin/write", method = RequestMethod.GET)
        public String adminWriteForm(@PathVariable("id") String id
                , Model model) {
            categoryservice.getContentCategory(id, model);
            return "blog/blog-admin-write";
        }
    ```
2. 카테고리 정보 보여주는 service 추가
    ```java
    //	카테고리 정보 보여주기
	public void getContentCategory(String id, Model model) {
		BlogVo vo = blogrepository.getContent(id);
		Map<String, Object> map = new HashMap<>();
		List<CategoryVo> categoryList = categoryrepository.getCategory(id);
		List<Long> countPost = categoryrepository.getPostCount(id);
		map.put("blog", vo);
		map.put("category", categoryList);
		map.put("id", id);
		map.put("count", countPost);
		model.addAttribute("map", map);
	}
    ```
    + 카테고리 정보, blog정보가 필요한데 함수 재사용을 이용해서 객체 정보를 model로 보냈다. 
-----
### 3. 블로그 글 추가
1. 블로그 글을 추가하기 전에 사용자는 defalut로 "미분류"라는 카테고리를 가진다. 
2. 글 추가하는 controller 작성
    ```java
    //	블로그 글 추가
        @Auth
        @RequestMapping(value="admin/write", method = RequestMethod.POST)
        public String adminWrite(@PathVariable("id") String id
                , @ModelAttribute PostVo vo
                , Model model) {
            categoryservice.insertPost(id, vo);
            return "redirect:/jblog/"+id;
        }
    ```
    + 여기서 PostVo로 받은 이유는
    ```jsp
        <select name="category_no">
        <c:forEach items="${map.category }" var="list" varStatus="status">	
        <option value="${list.no }">${list.name }</option>
        </c:forEach>
        </select>
    ```
        + select의 value를 list.no(카테고리 번호)로 지정했기 때문이다. 
3. service 작성
    ```java
    	public void insertPost(String id, PostVo vo) {
		postrepository.insertByCategory(vo);
	}
    ```
4. 쿼리문 및, PostRepository 작성
    ```java
    public void insertByCategory(PostVo vo) {
            sqlSession.insert("post.insert", vo);
        }
    ```
    ```xml
    <insert id="insert" parameterType="categoryvo">
            <![CDATA[
                insert into category values(null, #{name}, #{description}, #{blog_id});
            ]]>
        </insert>
    ```