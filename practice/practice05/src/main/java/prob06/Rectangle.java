package prob06;

public class Rectangle extends Shape implements Resizable{
	double width;
	double height;
	public Rectangle(double w, double h) {
		this.width = w;
		this.height = h;
	}
	
	@Override
	public void resize(double s) {
		this.width *= s;
		this.height *= s;
	}

	@Override
	public double getArea() {
		return this.width*this.height;
	}

	@Override
	public double getPerimeter() {
		return this.width*2 + this.height*2;
	}
}
