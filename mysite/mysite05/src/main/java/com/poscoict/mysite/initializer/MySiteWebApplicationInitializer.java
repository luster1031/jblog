package com.poscoict.mysite.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.mysite.config.AppConfig;
import com.poscoict.mysite.config.WebConfig;

import ch.qos.logback.core.spi.FilterAttachableImpl;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { AppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	
	//	Dispatcher Servlet 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] {WebConfig.class};
	}
	//Encoding Filter 
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new CharacterEncodingFilter("utf-8",false)};
	}



//	// 해당 url에 핸들러가 없으면 exception 던지겠다. 
//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//		registration.setInitParameter("throwExceptionIfNoHandlerFound", "");
//	}
	
	
}
