class Sphere {
	
	Point center;
	double radius;
	
	Sphere(int x, int y, int z, double r){
		this.center = new Point(x,y,z);
		this.radius = r;
	}
	
	Sphere(Point c, double r){
		this.center = c;
		this.radius = r;
	}
	
	int getX(){
		//javadoc
		return this.center.getX(); // TODO
	}

	int getY(){
		//javadoc
		return this.center.getY(); // TODO
	}

	int getZ(){
		//javadoc
		return this.center.getZ(); // TODO
	}


	double getRadius(){
		//javadoc
		return this.radius; // TODO
	}

	double calculateDiameter(){
		// el diametro es dos veces el radio javadoc
		return 2* this.radius;
		// TODO
	}	
	
	double calculateSurfaceArea(){
		// javadoc O = 4 * pi * r^2
		return 4.0 * Math.PI * Math.pow(this.radius, 2);
	}
	
	double calculateVolume(){
		// javadoc V = 4/3 * pi * r^3
		return (4.0/3.0) * Math.PI * Math.pow(this.radius, 3);
	}

}

