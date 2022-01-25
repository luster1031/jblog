package exception;

public class UncheckedExceptionTest {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		/* 	out bounds exception	*/
		for (int i = 0; i <= a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
