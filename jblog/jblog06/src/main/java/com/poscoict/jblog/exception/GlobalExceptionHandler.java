
package com.poscoict.jblog.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


/*
 * 모든 에러가 여기로 AOP기술
 * 
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		
		//	1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors)); 
		LOGGER.error(errors.toString());
		
		//  errors가 가지고 있는 버퍼 안에 에러 내용 적혀 있음
		System.out.println("-----");
		System.out.println(errors.toString());
		System.out.println("-----");
		if(errors.toString().equals("org.springframework.web.servlet.NoHandlerFoundException"))
			System.out.println("에러");
		model.addAttribute("exception", errors.toString());
		
		//	2. 사과 페이지 (HTML 응답, 정상 종료)
		return "error/exception";
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String Exception404() {
		return "error/404";
	}
}
