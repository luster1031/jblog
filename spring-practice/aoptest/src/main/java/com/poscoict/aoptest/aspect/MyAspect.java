package com.poscoict.aoptest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component	//	설정완료 -> 생성 됨
public class MyAspect {
	//	advice
	
	//	메소드에 대한 것 다 적어줘야 함. 리턴 타입 적어줘야함
	//  이 메소드 실행 되면 그 전에 이 밑에 beforAdvice가 실행 됨
	@Before("execution(public com.poscoict.aoptest.vo.ProductVo com.poscoict.aoptest.service.ProductService.find(String))")
	public void beforeAdvice() {
		// finding 보다 먼저 나옴
		System.out.println("---Before Advice");
	}
	
	//	리턴 타입 생략 가능 execution(* *..*.ProductService.*(..))
	@After("execution(* *..*.ProductService.find(..))")
	public void afterAdvice() {
		System.out.println("----After Advice");
	}
	
	//	after advice 뒤에
	@AfterReturning("execution(* *..*.ProductService.*(..))")
	public void afterReturningAdvice() {
		System.out.println("---After Returning Advice---");
	}
	
	//	mysite에 해놓은 global exception과 똑같
	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("---After Throwing Advice "+ ex + " ---");
	}
	
	
	// around
	//	point cut에 대한 정보 - ProceedingJoinPoint
	//	around 사용하면 before, after다 된다. 
	@Around("execution(* *..*.ProductService.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		/* before */
		System.out.println("---Around(Before) Advice");
		
		
		//	실행 할 때, 파라미터 변경
		Object[] params = {"Cmera"};
		Object result = pjp.proceed(params);	// point cut 메소드 실행
		
		/* after */
		System.out.println("---Around(After) Advice");
		
		return result;
	}
}
