## 블로그 카테고리 관리
### 1. 카테고리 정보 보기
1. controller 설정

    ```java
    //	블로그 카테고리 관리
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id
			, Model model) {
		blogservice.getContentCategory(id, model);
		return "blog/blog-admin-category";
	}
    ```
2. service 설정
    ```java
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
    + contentBlog의 기능과 똑같아서 재사용하고 싶었지만 못 했다.
        + 재 사용 시 필요가 없는 post의 정보까지 불러오기  때문이다. 
3. 각 카테고리의 post 갯수 구하기
    > CategoryRepository.java
    ```java
    public List<Long> getPostCount(String id) {
		return sqlSession.selectList("category.postCount",id);
	}
    ```
    
    <br>

    >category.xml
    ```xml
    <select id="postCount" parameterType="string" resultType="long">
	<![CDATA[
		select (select count(*) from post p
			where category_no=c.no)num 
		from category c 
		where blog_id=#{id};
	]]>
	</select>
    ```
-----    
### 2. 카테고리 추가
1. form설정
    > blog-admin-category.jsp
    ```jsp
    <form method="Post" action="${pageContext.request.contextPath}/jblog/${map.id}/admin/category/update">
    </form>
    ```
2. Controller 추가
    ```java
    //	블로그 카테고리 추가
	@Auth
	@RequestMapping(value="/admin/category/update", method = RequestMethod.POST)
	public String adminCategoryUpdate(@PathVariable("id") String id
			, @ModelAttribute CategoryVo vo
			, Model model) {
		categoryservice.updateCategory(vo,id);
		return "redirect:/jblog/"+id+"/admin/category";
	}
    ```
3. Service추가
    > CategoryService.java
    ```java
    public void updateCategory(CategoryVo vo, String id) {
		categoryrepository.insert(vo, id);
	}
    ```
4. CategoryRepository추가
    > CategoryRepository.java
    ```java
    public void insert(CategoryVo vo, String id) {
		vo.setBlog_id(id);
		sqlSession.insert("category.insert",vo);
	}
    ```
5. category.xml 에 쿼리문 추가 
    ```xml
    <insert id="insert" parameterType="categoryvo">
    <![CDATA[
        insert into category values(null, #{name}, #{description}, #{blog_id});
    ]]>
    ```
-----
### 3. 카테고리 삭제
1. url설정
    ```jsp
    <a href="${pageContext.request.contextPath}/jblog/${map.id}/admin/category/${list.no }"><img
    src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>
    ```
2. Controller설정
    > JblogController.java
    ```java
    //	블로그 카테고리 삭제
	@Auth
	@RequestMapping("/admin/category/{index}")
	public String adminCategoryDelete(@PathVariable("id") String id
			,@PathVariable Long index
			, Model model) {
		categoryservice.deleteCategory(index);
		return "redirect:/jblog/"+id+"/admin/category";
	}
    ```
    + path로 받은 category_no를 삭제한다. 
3. Service 설정
    ```java
    //	카테고리 삭제
	public boolean deleteCategory(Long category_no) {
		return categoryrepository.deleteCategory(category_no)== 1;
	}
    ```
4. CategoryRepository 설정
    ```java
    public int deleteCategory(Long category_no) {
		return sqlSession.delete("category.delete", category_no);
	}
    ```
5. delete 쿼릴문 작성
    > category.xml
    ```xml
    <delete id="delete" parameterType="Long">
		<![CDATA[
		delete from category 
		where (
			select count(*) from post p 
			where p.category_no=#{category_no})=0 
		and no=#{category_no} and name!='미분류';
		]]>
	</delete>
    ```
    + post의 개수가 0인 category만 삭제해야한다. 
	+ 추가) 카테고리가 '미분류'는 기본 카테고리기 때문에, 삭제하면 안된다. 
