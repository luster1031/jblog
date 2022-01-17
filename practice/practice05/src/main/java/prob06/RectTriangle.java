package prob06;

public class RectTriangle extends Shape {
	double width;
	double height;
	public RectTriangle(double w, double h) {
		this.width = w;
		this.height =h;
	}
	@Override
	public double getArea() {
		return this.width*this.height*0.5;
	}
	@Override
	public double getPerimeter() {
		double otherLength = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
		return this.width + this.height+ otherLength;
	}
}
