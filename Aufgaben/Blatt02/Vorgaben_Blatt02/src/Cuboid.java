/**
 * Represents a Cuboid
 * http://en.wikipedia.org/wiki/Cuboid
 * 
 * @author AlgoDat
 *
 */
public class Cuboid extends Body {

	double height, length, width;
	
	/*
	 * Constructor for a Cuboid object
	 */
	public Cuboid() {
		
	}
	/*
	 * Constructor for a Cuboid object
	 * 
	 * @param h height
	 * @param l length
	 * @param w width
	 */
	public Cuboid(double h, double l, double w) {
		if(h > 0) this.height = h;
		else this.height = 1;
		
		if(l > 0) this.length = l;
		else this.length =1;
		
		if(w > 0)this.width = w;
		else this.width = 1;
		
	}

	public double getHeight(){
		return this.height;
	}
	public double getLength(){
		return this.length;
	}
	public double getWidth(){
		return this.width;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	@Override
	double calculateVolume() {
		return this.length * this.height  * this.width;
	}
	@Override
	double calculateSurface() {
		return 2*(this.length * this.width) + 2*(this.length * this.height) + 2*(this.width * this.height);
	}


}

