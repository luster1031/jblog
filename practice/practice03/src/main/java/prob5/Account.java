package prob5;

public class Account {
	String accountNO;
	int balance;
	public Account() {
		this.balance = 0;
	}
	public void save(int won) {
		balance += won;
		System.out.println(this.accountNO + "계좌에 " + won+
				"만원이 입금되었습니다.");
	}

	public void deposit(int won) {
		balance -= won;
		System.out.println(this.accountNO + "계좌에 " + won+
				"만원이 출금되었습니다.");
	}

	public String getAccountNO() {
		return accountNO;
	}

	public void setAccountNO(String accountNO) {
		this.accountNO = accountNO;
		System.out.println(accountNO+ "계좌가 개설되었습니다.");
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
