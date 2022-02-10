package ex03;
/**
 * 2022.02.10
 * @ComponentScan 예제
 * */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
/**
 * @SpringBootConfiguration == @Configuration
 * @ComponentScan
 * @EnableAutoConfiguration
 * */

@SpringBootConfiguration
@ComponentScan
public class MyApplication {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext c = SpringApplication.run(MyApplication.class, args)) {
		}
	}

}
