package prob4;

import java.util.Scanner;

public class prob4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		printCharArray(reverse(s));
		scanner.close();
	}

	public static char[] reverse(String str) {
		char[] output = new char[str.length()];
		for (int i = str.length() - 1, j = 0; i >= 0; i--, j++) {
			output[j] = str.charAt(i);
		}

		return output;
	}

	public static void printCharArray(char[] array) {
		for (char c : array) {
			System.out.print(c);
		}
	}
}
