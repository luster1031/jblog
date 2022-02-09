package com.poscoict.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

public class MessageConfig {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/poscoict/jblog/config/web/messages");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
}
