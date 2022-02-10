package com.poscoict.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unused")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	//  java.lang.ClassCastException: org.springframework.web.servlet.resource.
			if (handler instanceof HandlerMethod == false) {
				return true;
			}
		//1. pathValue에서 받았던 id받기
		@SuppressWarnings("unchecked")
		Map<String, Object> user_id = (Map<String, Object>) request.getAttribute( HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println("[interceptor] id : "+ user_id);
		
		//	post와 category에 숫자를 안 넣으면 main문으로
		if(user_id.get("post")!=null) {
			try {
				Long category = Long.parseLong((String) user_id.get("category"));
				Long post =  Long.parseLong((String)user_id.get("post"));
			}catch(Exception e) {
				System.out.println("post 숫자 아님");
				response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
				return false;
			}
		}
		if(user_id.get("category")!=null) {
			try {
				Long category = Long.parseLong((String)user_id.get("category"));
			}catch(Exception e) {
				System.out.println("category 숫자 아님");
				response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
				return false;
			}
		}
		
		
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		
		// 3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//	인증이 필요 없는 것
		if (auth == null) { 
			System.out.println("인증 필요 없음");
			return true;
		}
		
		//	인증이 필요한 것 (메소드 앞에 @Auth라고 되어있는 것)
		HttpSession session = request.getSession();
		System.out.println("인증이 필요한 것");
		if(session != null) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			if (authUser == null) {
				System.out.println("userid.get: " + user_id.get("id"));
				response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
				return false;
			}
			//	수정하려는 id가 블로그 주인이라면
			if(authUser.getId().equals(user_id.get("id"))) {
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/jblog/"+user_id.get("id"));
		return false;
	}

}