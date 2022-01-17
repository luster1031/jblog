package prob3;

public class prob3 {
	public static void main(String[] args) {
		CurrencyConverter.setRate(1190.26);
		
		double todoller = CurrencyConverter.toDollar(1000000.0);
		double tokrw= CurrencyConverter.toKRW(100.0);
		System.out.println("백만원은 "+ todoller + " 달러입니다.");
		System.out.println("백달러는 "+ tokrw+" 달러입니다.");
		
	}
	
}
