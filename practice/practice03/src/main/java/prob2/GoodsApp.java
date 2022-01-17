package prob2;


import java.util.Scanner;

public class GoodsApp {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Goods[] goods = new Goods[3];
		
		for(int i = 0; i<goods.length; i++) {
			String name = scan.next();
			int price = scan.nextInt();
			int count = scan.nextInt();
			
			goods[i] = new Goods(name, price, count );
		}
		
		for(int i = 0; i<goods.length;i++) {
			System.out.println(goods[i].getName()
					+ "(가격:"+goods[i].getPrice()+"원)이"
					+ goods[i].getCount()+"개 입고 되었습니다.");
		}
		scan.close();
	}

	
}
