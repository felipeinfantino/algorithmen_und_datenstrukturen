public class FastaException extends Exception {
	
	/**
	 * @param Nachricht von der Exception
	 * wenn ich super aufrufe, beziehe ich mich auf die Oberklasse "Exception"
	 */
	private static final long serialVersionUID = 1L;
	public FastaException(String message){
		super(message);
	}
}

