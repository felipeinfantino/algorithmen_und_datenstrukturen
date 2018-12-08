/**
 * Represents a Cube
 * @author AlgoDat
 */
public class Cube extends Cuboid {
	private double length;
	private double height;
	private double width;
	/*
	 * Constructor without parameter
	 */
	public Cube() {
		
	}
	/*
	 * Constructor with one parameter
	 * 
	 * @param length the side length of the cube
	 */
	public Cube(double length) {
		if(length > 0)	{
			this.length = length;
			this.height = length;
			this.width = length;
		}
		else {
			this.length =1;
			this.height = 1;
			this.width = 1;
		}
	}

	public double getLength(){
		return this.length;
	}
	
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setLength(double length){
		this.length = length;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	@Override
	double calculateSurface() {
		return 6*Math.pow(this.length, 2);
	}
	@Override
	double calculateVolume() {
		return Math.pow(this.length, 3);
	}
}

