package prob03;

public class Prob2 {
	public static void main(String[] args) {
		Bird duck = new Duck();
		duck.setName("꽥꽥이");
		duck.fly();
		duck.sing();
		System.out.println(duck.toString());
		
		Bird sparrow = new Sparrow();
		sparrow.setName("짹짹");
		sparrow.fly();
		sparrow.sing();
		System.out.println(sparrow);
	}
}
