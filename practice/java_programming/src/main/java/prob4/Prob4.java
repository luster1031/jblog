package prob4;

import java.util.Scanner;

public class Prob4 {
	public static void main(String[] args) {
		System.out.print("문자열을 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		for (int j = 1; j <= input.length(); j++) {
			for (int i = 0; i < j; i++) {
				System.out.print(input.charAt(i));
			}
			System.out.print("\n");
		}
		scanner.close();
	}
}
