package prob3;

public class CurrencyConverter {
	private static double rate;

	public static double toDollar(double won) {
		// 한국 원화를 달러로 변환
		won/=rate;
		return won;
	}

	public static double toKRW(double dollar) {
		// 달러를 한국 원화로 변환
		dollar*=rate;
		return dollar;
	}

	public static void setRate(double r) {
		// 환율 설정(KRW/$1)
		rate = r;
	}


}
