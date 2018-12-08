
/**
 * Any 2d Entity that fills some area.
 * @author AlgoDat team
 *
 */
public abstract class Shape implements Comparable<Shape>  {
    
	/**
	 * if difference below tolerance parameter, shapes are considered equal
	 */
	private static double tol = .1;

    /**
     * Calculates the area of a shape.
     * @return the area the shape fills
     */
    abstract double calculateArea();
    
    /**
     * Scales the shape by a factor.
     * @param factor the scaling factor
     */
    abstract void scale(double factor);
    
    
    /**
     * Compares two shapes using the area to compare.
     *
     * @param s the other Shape
     */
    @Override
    public int compareTo(Shape s) {    
        double myArea = this.calculateArea();
        double otherArea = s.calculateArea();
        double differenz = myArea - otherArea;
        if(differenz< tol){
        	if(differenz < (-1)* tol)return -1;
        	else return 0;
        	}
        return 1;
        }
    	
        
        //pillar , si la diferencia es menor que -0,1 o si es mayor que menos 1;
    }    


