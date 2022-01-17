package prob02;

public class Book {
	int bookNo; // 번호
	String title; // 제목
	String author; // 작가
	int stateCode; // 상태 코드


	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Book(int bookNo, String title, String author) {
		this.author = author;
		this.bookNo = bookNo;
		this.stateCode = 1;
		this.title = title;
	}

	public void rent() {
		this.stateCode = 0;
		System.out.println(this.title + "이(가) 대여 됐습니다.");
	}

	public void print() {
		System.out.print("책 제목:"+this.title+", 작가:" + this.author + 
				", 대여 유무:");
		if (stateCode == 1) {
			System.out.println("재고있음");
		} else {
			System.out.println("대여중");
		}
	}
}
