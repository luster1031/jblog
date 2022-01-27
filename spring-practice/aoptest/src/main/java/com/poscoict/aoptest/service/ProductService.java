package com.poscoict.aoptest.service;

import org.springframework.stereotype.Service;

import com.poscoict.aoptest.vo.ProductVo;

@Service
public class ProductService {

	public ProductVo find(String name) {
		System.out.println("[productService] finding...");
		// aop를 이 메소드에 한다. 
		
		/*
		 * //exception일으키기 위해 속이는 것 if(1-1 == 0) { throw new
		 * RuntimeException("Find Exception"); }
		 */
		return new ProductVo(name);
	}
	
}
