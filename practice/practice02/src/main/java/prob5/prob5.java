package prob5;

import java.util.Random;
import java.util.Scanner;

public class prob5 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();
		int num = 0;
		int guess = 0;
		String check = "";
		do {
			num = random.nextInt(100) + 1;
			System.out.println("수를 결정하였습니다. 맞추어 보세요\n1-100");
			for (int i = 1; guess != num; i++) {
				System.out.print(i + ">>");
				guess = scanner.nextInt();
				if (guess > num)
					System.out.println("더 낮게");
				else if (guess < num)
					System.out.println("더 높게");
				else
					System.out.println("맞았습니다.");
			}
			System.out.println("다시하시겠습니까(y/n)");
			check = scanner.next();
		} while (check.equals("y"));
		
		scanner.close();
	}
}
