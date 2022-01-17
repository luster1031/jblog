package prob6;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;

		while (true) {
			System.out.print(">> ");
			String[] input = scanner.nextLine().split(" ");
			if (!input[0].equals("quit")) {
				num1 = Integer.parseInt(input[0]);
				num2 = Integer.parseInt(input[2]);
				switch (input[1]) {
				case "+":
					Add add = new Add();
					add.setValue(num1, num2);
					System.out.println(">> " + add.calculate());
					break;
				case "-":
					Sub sub = new Sub();
					sub.setValue(num1, num2);
					System.out.println(">> " + sub.calculate());
					break;
				case "*":
					Mul mul = new Mul();
					mul.setValue(num1, num2);
					System.out.println(">> " + mul.calculate());
					break;
				case "/":
					Div div = new Div();
					div.setValue(num1, num2);
					System.out.println(">> " + div.calculate());
					break;

				}
			} else {
				break;
			}
		}
		scanner.close();
	}
}
