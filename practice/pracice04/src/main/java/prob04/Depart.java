package prob04;

public class Depart extends Employee{
	String department = "기획부";
	
	public Depart(String name, int salary, String dept) {
		super(name, salary);
	}
	public void getInformation() {
		System.out.printf("이름: %s   연봉: %d   부서 : %s\n"
				, getName(),  getSalary(),this.department);
	}
}
