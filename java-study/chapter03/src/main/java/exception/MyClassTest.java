package exception;

import java.io.IOException;

public class MyClassTest {
	public static void main(String[] args) throws MyException {
		try {
			MyClass mc = new MyClass();
			mc.danger();
		}catch(IOException e) {
			System.out.println("error : " + e);
		}
	}
}
 