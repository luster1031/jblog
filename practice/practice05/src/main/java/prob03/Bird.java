package prob03;

public abstract class Bird {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public abstract void fly();
	public abstract void sing();
}
