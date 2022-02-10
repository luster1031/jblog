package ex03;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ex03.component.MyComponent;


// 	spring boot

@SpringBootTest

public class MyApplicationTest02 {
	@Autowired
	private MyComponent myComponent; 
	
	@Test
	public void MyComponentNotNull() {
		assertNotNull(myComponent);
	}
}
	