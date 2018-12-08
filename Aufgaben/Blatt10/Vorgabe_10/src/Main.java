import com.sun.javafx.geom.transform.GeneralTransform3D;

public class Main {

	public static void main(String[] args) {
		double[] buffer1 = {1.0, 5.0, 8.0, 2.0, 3.0, 2.0, 5.0}; 
		double[] buffer2 = {10.0, 9.0, 3.0, 1.0, 2.0, 3.0, 8.0, 9.0}; 
		ISignal s1 = new SignalFromBuffer(buffer1, 44100); 
		ISignal s2 = new SignalFromBuffer(buffer2, 44100); 

		Matcher matcher = new Matcher(s1, s2); 
		matcher.compute();

		
		prettyprintMatrix(matcher.accumulatedDistance);
		matcher.getMappingPath();
		for(int i = 0; i < matcher.getMappingPathAsArray().length ; i ++){
			System.out.println();
			for(int p = 0; p < 2 ; p++){
				System.out.print(matcher.getMappingPathAsArray()[i][p]+ " ");
			}
		}
		System.out.println(matcher.distance);
		
	}
	public static void prettyprintMatrix(double[][] m){
		for (double[] row: m) {
			for (double element : row) {
				if (element == Double.POSITIVE_INFINITY) {					
					System.out.print(" inf, ");
				} else {					
					System.out.print(String.format("%.2f, ", element));
				}
			}
			System.out.println();				
		}
	}

}
