package ex05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import ex05.component.MyComponent;

/**
 * 
 * Application Context 구성 후, 실행 할 코드( Application Context환경에 의존하고 있으면 - bean을
 * 호출하는 )가 있는 경우 ApplicationRunner 인터페이스 구현 클래스 빈 생성하기. -> 주입 받기
 * 
 * 
 */
@SpringBootApplication
public class MyApplication {

	@Bean
	public ApplicationRunner applicationRunner() {

		// 1. 작성된 구현 클래스를 사용하는 방법
		// return new HelloworldRunner();

		// 2. Annoymouse Class 사용하는 방법

		return new ApplicationRunner() {

			@Autowired
			private MyComponent myComponent;

			@Override
			public void run(ApplicationArguments args) throws Exception {
				myComponent.printHello();
			}
		};

	}

	// 3. 함수형(람다 표현식)
	// 인터페이스에 함수가 하나야한다.
	// 함수 안에 변수가 있으면 못 씀
	// 함수가 객체 -> 함수를 파라미터를 전달 가능

//		return (args) -> {
//			System.out.println("Hello World!");
//		};
//	}

	public static void main(String[] args) {
		try (ConfigurableApplicationContext c = SpringApplication.run(MyApplication.class, args)) {
		}
	}

}
