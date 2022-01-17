package prob1;

import java.util.Scanner;

public class prob1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("금액 : ");
		int money = scanner.nextInt();
		int coin[] = { 50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1 };
		for (int i = 0; i < coin.length; i++) {
			System.out.print(coin[i] + "원 : ");
			if (money > coin[i]) {
				System.out.print(money / coin[i] + "개");
				money %= coin[i];
			} else
				System.out.print("0개");
			System.out.println("");
		}
		scanner.close();
	}
}
