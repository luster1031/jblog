package prob5;

public class Prob5 {
	public static void main(String[] args) {
		for (int i = 3; i < 100; i++) {

			if ((i % 10) % 3 == 0 || ((i / 10) % 3 == 0 && i > 10)) {
				System.out.print(i + " ");
				if ((i % 10) % 3 == 0)
					System.out.print("짝");
				if (((i / 10) % 3 == 0))
					System.out.print("짝");
				System.out.println("");
			}
		}
	}
}
