package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		//	1. handler 정보 - handler
		// 	handler 종류 확인 - 이미지 파일,, 등등
		if(handler instanceof HandlerMethod  == false) {
			System.out.println("1번");
			return true;
		}
		
		//	2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//	3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//	4. Handler Method @Auth가 없다면 Type에 있는 지 확인(과제)
		if(auth == null) {
			//	클래스로 올라가서 찾아봐야 한다. 
		}
		
		// 5. type과 method에 @Auth가 적용이 안 되어 있는 경우
		if(auth==null) {	//	인증이 필요없는 것이라면
			System.out.println("인증이 필요 없");
			return true;
		}
		
		//	5 @Auth가 적용이 되어 있기 때문에, 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//	세션이 있는 것
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		System.out.println("[세션이 있는 것 : ]");
		if(authUser == null) { 
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		// 	6. 인증 확인 --> controller의 handler(methode) 실행
		return true;
	}

}
