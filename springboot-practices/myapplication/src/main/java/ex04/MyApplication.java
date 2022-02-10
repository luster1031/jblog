package ex04;
/**
 * 2022.02.10
 * @EnableAutoConfiguration 예제
 * */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


/**
 * @SpringBootApplication 메타 어노테이션
 * @SpringBootConfiguration == @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration (웹 MVC 설정, apo, security, jpa,...등을 자동 디폴트 설정 (Dependency기반으로) -> 라이브러리 잘 만들어야함)
 * 		- yaml, properties파일 등에 넣음(미세 설정)
 * 		
 * */

@SpringBootApplication
public class MyApplication {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext c = SpringApplication.run(MyApplication.class, args)) {
		}
	}

}
