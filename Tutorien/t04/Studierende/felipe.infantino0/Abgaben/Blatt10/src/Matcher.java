
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Represents the match of two wave files
 * 
 * Implements the comparison and matching methods according
 * to the optimal dynamic programming presented in the tutorial
 */
public class Matcher implements IMatcher {

	/**
	 * Signal X
	 */
	protected ISignal signalX;
	/**
	 * Signal Y
	 */
	protected ISignal signalY;

	
	/**
	 * The distance/dissimilarity score between
	 * the two wav files. The score is calculated in the
	 * compute method
	 * 
	 *  @see Matcher.compute
	 *  @see Matcher.computeDistance
	 */
	protected double distance = -1.0;

	/**
	 * The path of minimal distance between the two
	 * wave files.
	 * 
	 * The first integer of the pair denotes the
	 * frame number of WavFile w, the second integer
	 * the frame number of WavFile v
	 * 
	 * It will be stored here by the compute method
	 * 
	 * @see Matcher.compute
	 */
	protected List<Pair<Integer, Integer>> matchingPath = null;

	/**
	 * The accumulated distance/dissimilarity matrix. Multiple methods change or
	 * read this field.
	 *
	 * @see #initializeAccDistanceMatrix
	 * @see #computeAccDistanceMatrix
	 * @see #computeDistance
	 * @see #computeMatchingPath
	 * @see #compute
	 */
	protected double accumulatedDistance[][] = null;
	
	
		/**
	 * Construct a WavDistance object
	 *
	 * With this constructor, you can use signals defined by a buffer
	 * 
	 * For both passed ISignal buffers we have to check that
	 * they only have one channel and that they do not
	 * exceed the maximum number of frames MAX_FRAMES
	 * 
	 * @param dataX Array with signal values
	 * @param dataY Array with signal values
	 * @param sampleRate in samples per second
	 */
	public Matcher(double[] dataX, double[] dataY, int sampleRate) {
		this.signalX = new SignalFromBuffer(dataX, sampleRate);
		this.signalY = new SignalFromBuffer(dataY, sampleRate);
	}


	/**
	 * Construct a Matcher object
	 *
	 * With this constructor, you can use signals defined by a buffer
	 * 
	 * For both passed ISignal buffers we have to check that
	 * they only have one channel and that they do not
	 * exceed the maximum number of frames MAX_FRAMES
	 * 
	 * @param x 1st Object with ISignal interface
	 * @param y 2nd Object with ISignal interface
	 */
	public Matcher(ISignal x,ISignal y) {
		this.signalX = x;
		this.signalY = y;
	}
	
	/**
	 * Warps each signal to mimic the other one
	 *
	 * @return Pair of warped signals
	 * @throws IOException, WavFileException
	 */
	public Pair<ISignal, ISignal> warpSignals()  {
		
		 ISignal resultX = new SignalFromBuffer(matchingPath.size(), signalX.getSampleRate()); 
		 ISignal resultY = new SignalFromBuffer(matchingPath.size(), signalY.getSampleRate()); 
		
		//Construct signals:
		Iterator<Pair<Integer, Integer>> it = matchingPath.iterator();
		int indexX=0;
		int indexY=0;
		for (int matchcounter = 0; matchcounter < matchingPath.size(); matchcounter++) {
			Pair<Integer, Integer> pair = it.next();
			indexX = pair.getLeft();
			indexY = pair.getRight();
			resultX.setFrame(matchcounter, this.signalX.getFrame(indexX));
			resultY.setFrame(matchcounter, this.signalY.getFrame(indexY));
		}
		//trim the signal to the actual length of the signal:
		resultX.trimTo(indexX);
		resultY.trimTo(indexY);
		
		Pair <ISignal, ISignal> result = new Pair<ISignal, ISignal>(resultX, resultY);
		return result;
	}

	/**
	 * Returns the computed distance
	 * 
	 * @return Calculated distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Returns the computed mapping 
	 * 
	 * @return Calculated mapping
	 */
	public List<Pair<Integer, Integer>> getMappingPath() {
		return matchingPath;
	}

	/**
	 * 
	 * @return the computed mapping between the two signals as an array
	 * 		row: matches
	 * 		column: signals
	 */
	public int[][] getMappingPathAsArray() {
		int[][] array = new int[matchingPath.size()][2];
		int count = 0;
		for (Pair<Integer, Integer> pair : matchingPath) {
			array[count][0] = pair.getLeft();
			array[count][1] = pair.getRight();					
			count++;
		}
		return array;
	}

	
	/**
	 * Computes the distance score and mapping between the two WavFiles. The
	 * results of this computation can be retrieved with {@link #getDistance()}
	 * and {@link #getMappingPath()} or {@link #getMappingPathAsArray()}.<br>
	 * <br>
	 * The steps taken by this method can also be called separately:<br>
	 * 0. Be wary of the size of the signals.<br>
	 * 1. {@link #initializeAccDistanceMatrix()}<br>
	 * 2. {@link #computeAccDistanceMatrix()} <br>
	 * 3. {@link #computeDistance()}<br>
	 * 4. {@link #computeMatchingPath()}<br>
	 * 
	 * @throws WavFileException
	 */

	public void compute() throws RuntimeException {
		System.out.println("------");
		
		//Check size of the distance matrix to avoid bombing the memory heap
		Long heapsize = ((long)signalX.getNumFrames()) * ((long)signalX.getNumFrames()) * Double.SIZE / 1048576;
		System.out.println("Allocating " + heapsize.toString() + "MB");
		if (heapsize >  4000) {  // 4000 MB
			throw new RuntimeException("Signals are too long - I would gobble up too much memory when matching them!");
		} 
		
		// initialize the accumulated distance matrix properly
		initializeAccDistanceMatrix();
	
		// use dynamic programming to compute accumulated distance matrix 
		computeAccDistanceMatrix();
		// compute and store the distance score for the two files 
		computeDistance();
		// compute the mapping between the two files
		computeMatchingPath();
		
		System.out.println("Distance: " + distance);
		System.out.println("------");
		
	}
	
	/**
	 * Initializes the accumulated distance matrix {@link #accumulatedDistance}
	 * for the signals {@link #signalX} and {@link #signalY}.
	 */
	protected void initializeAccDistanceMatrix() {
		accumulatedDistance = new double[signalX.getNumFrames()+1][signalY.getNumFrames()+1];
		
		for(int i = 1; i < signalY.getNumFrames()+1 ; i++){
			accumulatedDistance[0][i] = Double.POSITIVE_INFINITY;
		}
		for(int i = 1; i < signalX.getNumFrames()+1 ; i++){
			accumulatedDistance[i][0] = Double.POSITIVE_INFINITY;
		}
		this.accumulatedDistance[0][0] = 0;
		if (this.accumulatedDistance[0][0] != 0) throw new RuntimeException("Ini");
	}
	
	/**
	 * Read the frames of the two signals and uses them
	 * to compute the accumulated distance matrix
	 * {@link Matcher#getAccumulatedDistanceMatrix()}.
	 *
	 * This method assumes that
	 * {@link Matcher#initializeAccDistanceMatrix()} has already
	 * been called.
	 *
	 * @see initializeAccDistanceMatrix
	 * @see computeDistance
	 * @see computeMatchingPath
	 */
	protected void computeAccDistanceMatrix() {
		if (this.accumulatedDistance == null) throw new RuntimeException("Called before initializing the distance matrix!");
		System.out.print("Computing...");
		
		for(int i = 1; i < signalX.getNumFrames()+1; i++){
			for(int p = 1 ; p < signalY.getNumFrames()+1; p++){
				double a = Math.min(accumulatedDistance[i-1][p-1], accumulatedDistance[i-1][p]);
				double b = Math.min(accumulatedDistance[i-1][p-1], accumulatedDistance[i][p-1]);
				accumulatedDistance[i][p] = Math.min(a, b) + Math.abs(signalX.getFrame(i-1) - signalY.getFrame(p-1)); // revisar i-p
			}
		}		  
		
		System.out.println("done");		
		
	}
	
	/**
	 * Computes the minimum distance of two signals saved within the computed
	 * accumulated distance matrix. The distance can be retrieved with
	 * {@link Matcher#getDistance()}.
	 *
	 * This method assumes that {@link Matcher#computeAccDistanceMatrix()} has
	 * already been called.
	 *
	 * @see computeAccDistanceMatrix
	 */
	protected void computeDistance() throws RuntimeException {
		if(this.accumulatedDistance == null) {throw new RuntimeException("Called before computing the distance matrix!");}
		distance = accumulatedDistance[signalX.getNumFrames()][signalY.getNumFrames()];
		
	}
	
	/**
	 * Computes the matching path of two signals with the following criteria:
	 *
	 * 1. The path is a LinkedList of Pairs of frame indexes: [ (x_i1, y_i1),
	 * ..., (x_in, y_in) ]
	 *
	 * 2. The path is as short as possible: If we have the opportunity to go
	 * diagonally, we should do this (i.e. take the lexicographically smallest
	 * pair).
	 *
	 * 3. The indexes contained in the Pairs correspond to the indexes of the
	 * frames, i.e. they range from 0 to (fileSize-1) of the respective files.
	 *
	 * 4. The path is in ascending order.
	 *
	 * 5. The path can be retrieved with {@link Matcher#getMappingPath()}.
	 *
	 * This method assumes that {@link Matcher#computeAccDistanceMatrix()} has
	 * already been called.
	 *
	 * @see computeAccDistanceMatrix
	 */
	protected void computeMatchingPath() throws RuntimeException {
		if(this.accumulatedDistance == null) {throw new RuntimeException("Called before computing the distance matrix!");}		
		System.out.print("Computing mapping path...");		
		Queue<Pair<Integer,Integer>> queue = new LinkedList<Pair<Integer,Integer>>();
		Pair<Integer,Integer> start = new Pair(signalX.getNumFrames()-1, signalY.getNumFrames()-1);//signalIX. get -1 , signalIY.get-1
		Pair<Integer,Integer> ziel = new Pair(0,0);
		while(!start.equals(ziel)){
			queue.add(start);
			Pair<Integer,Integer> p1 = new Pair(start.getLeft()-1, start.getRight());
			Pair<Integer,Integer> p2 = new Pair(start.getLeft()-1, start.getRight()-1);
			Pair<Integer,Integer> p3 = new Pair(start.getLeft(), start.getRight()-1);
			start = minPair(p1, p2, p3);
		}
		queue.add(ziel);
		matchingPath = new LinkedList<Pair<Integer,Integer>>();
		Stack<Pair<Integer,Integer>> stack = new Stack<Pair<Integer,Integer>>();
		while(!queue.isEmpty()){
			stack.push(queue.poll());
		}
		while(!stack.isEmpty()){
			matchingPath.add(stack.pop());
		}
		
		
		System.out.println("done");
		
	}
	
	public Pair<Integer,Integer> minPair(Pair<Integer,Integer> p1 , Pair<Integer,Integer> p2 , Pair<Integer,Integer> p3){
		Pair<Integer, Integer> minP1P2 = null;
		Pair<Integer, Integer> min = null;
		if(accumulatedDistance[p1.getLeft()+1][p1.getRight()+1] == accumulatedDistance[p2.getLeft()+1][p2.getRight()+1]){
			p1 = p2;
		}
		if(accumulatedDistance[p3.getLeft()+1][p3.getRight()+1] == accumulatedDistance[p2.getLeft()+1][p2.getRight()+1]){
			p3 = p2;
		}
		if(Math.min(accumulatedDistance[p1.getLeft()+1][p1.getRight()+1], accumulatedDistance[p2.getLeft()+1][p2.getRight()+1])  == accumulatedDistance[p2.getLeft()+1][p2.getRight()+1]){
			minP1P2 = p2;
		}else{
			minP1P2 = p1;
		}
		
		if(Math.min(accumulatedDistance[minP1P2.getLeft()+1][minP1P2.getRight()+1], accumulatedDistance[p3.getLeft()+1][p3.getRight()+1]) == accumulatedDistance[p3.getLeft()+1][p3.getRight()+1] ){
			min = p3;
		}else{
			min = minP1P2;
		}
//    	System.out.println("Min entre :" + p1.toString() + " , " + p2.toString() + " , " + p3.toString() + " = " + min);
		return min;
	}
	
	
}

