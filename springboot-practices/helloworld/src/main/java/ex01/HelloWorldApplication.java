package ex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 
 * Bootstrapping class(모든 준비를 마친)
 * 
 * 1. 스프링 부터 애플리케이션의 부트스트래핑(Bootstrapping) 2. 설정 클래스로 역활(Configuation class)
 * 
 * 
 * 
 */
@SpringBootApplication
public class HelloWorldApplication {
	public static void main(String[] args) {
		/**
		 * 	spring boot 준비 과정 - SpringApplication.run(run)안에서 밑에 과정 ㅇ일어남
		 * 	1. 애플리케이션 컨텍스트 (Application Context, Spring Container)생성
		 * 		- was로 대체할 수 있다. 
		 * 
		 * 	2. 웹 애플리케이션 타입 확인 ()
		 * 		- Web Application 타입이면 MVC or Reactive 타입 설정(MVC인지, Reactive(함수형)인지) -> was때문에 
		 * 
		 * 	3. 어노케이션 스캐닝(auto) or Configration Class(Explicit) 통합 빈 생성/등록 및 와이어링
		 * 		- @compoentScan() -> auto
		 * 		- @import() -> 명시적
		 * 
		 * 	4. 웹 애플리케이션(MVC)
		 * 		- 데이터 변화에 반응 할 수 있게 
		 * 		- 내장(embeded) 서버(TomcatEmbeddedServletContainer)인스턴스 생성
		 * 		- 서버 인스턴스 웹애플리케이션을 배포
		 * 		- 서버 인스턴스 실행
		 *
		 * 	5. ApplicationRunner 인터페이스 구현한 빈을 찾아서 실행(run 호출)
		 * 		- run에 코드 생성
		 * 
		 * */
		
		/**
		 * 	run이 Container를 return해줌
		 *  ApplicationContext c = SpringApplication.run(HelloWorldApplication.class, args);
		 *  	- close메소드가 없음
		 *  	- close하려면
		 *  		ConfigurableApplicationContext c=null;
					try {
						c = SpringApplication.run(HelloWorldApplication.class, args);
					}catch(Throwable ex) {
						ex.printStackTrace();
					}finally {
						c.close();	
					}
					
		 * */
//		ConfigurableApplicationContext c=null;
//		try {
//			c = SpringApplication.run(HelloWorldApplication.class, args);
//		}catch(Throwable ex) {
//			ex.printStackTrace();
//		}finally {
//			c.close();	
//		}
		
		
		//try ~ with ~ resource
		try(ConfigurableApplicationContext c = SpringApplication.run(HelloWorldApplication.class, args)){
		}
		
		//	밑에 더 코드 넣지 말아라 -> 있으면 5번처럼 
	}
}
