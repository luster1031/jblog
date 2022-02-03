package com.poscoict.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.repository.UserRepository;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;
import com.poscoict.mysite.vo.UserVo;

public class SiteInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("================prehandle -- site===========");
		
		if(handler instanceof HandlerMethod  == false) {
			return true;
		}
		
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) { 
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		System.out.println(authUser.getNo());
		String role = userRepository.findrole(authUser.getNo());
		
		System.out.println("[role] : "+ role);
		if("ADMIN".equals(role)) {
			System.out.println("이름" +authUser.getName());
			if( "관리자".equals(authUser.getName())){
				System.out.println("이름이 관리자");
				response.sendRedirect(request.getContextPath() + "/admin/main");
				return false;
			}
			
		}
		
		SiteVo sitevo = (SiteVo)request.getServletContext().getAttribute("site");
		if(sitevo==null) {
			System.out.println("servletcontext null");
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		else {
			response.sendRedirect(request.getContextPath() + "/");
			System.out.println("[sitevo prehandle]" + sitevo.toString());
		}
		
		return false;	
	}
	
}
