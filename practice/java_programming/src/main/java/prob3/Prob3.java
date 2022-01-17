package prob3;
import java.util.Scanner;

public class Prob3 {
	public static void main(String[] args) {
		System.out.print("숫자를 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		
		int input_num = scanner.nextInt();
		int even =0; 
		int odd = 0;
		for(int i =1; i<=input_num; i++) {
			if(i %2 == 0) {
				even += i;
			}else
				odd += i;
		}
		System.out.print("결과 값 : " );
		if(input_num %2 == 0)
			System.out.println(even);
		else
			System.out.println(odd);
		scanner.close();
	}
}
