package exception;

import java.io.IOException;

/*	예외 던지기		*/
public class MyClass {
	public void danger() throws IOException, MyException {
		System.out.println("some coed 1 ... ");
		System.out.println("some coed 2 ... ");

		if(10-10 == 0) {
			throw new MyException();
		}
		if (5 - 5 == 0) {
			throw new IOException();
		}
		
		System.out.println("some coed 3 ... ");
		System.out.println("some coed 4 ... ");
	}
}
