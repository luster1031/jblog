package prob5;

public class AccountTest {
	public static void printBalance(Account acc) {
		System.out.println(acc.getAccountNO()+
				"계좌의 잔고는 "+ acc.getBalance()+ "만원입니다.");
		
	}
	public static void main(String[] args) {
		Account acc = new Account();
		acc.setAccountNO("078-3762-293");
		printBalance(acc);
		acc.save(100);
		printBalance(acc);
		acc.deposit(30);
		printBalance(acc);
	}
	
}
