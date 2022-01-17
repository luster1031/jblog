package prob3;

public class prob3 {
	public static void main(String[] args) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'c', 'i', 'l', '.' };
		printCharArray(c);
		replaceSpace(c);
		printCharArray(c);
	}

	public static void replaceSpace(char a[]) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == ' ')
				a[i] = ',';
		}
	}

	public static void printCharArray(char a[]) {
		for (char c : a) {
			System.out.print(c);
		}
		System.out.println("");
	}
}
