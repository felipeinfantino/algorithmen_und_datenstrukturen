import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class FastaParser {
	/**
	 * Parse Fasta File and return list of sequences
	 * @param file to read
	 * @return ArrayList of String Objects containing the sequences
	 */
	public ArrayList<String> readSequences(File file) throws FastaException, IOException{
		ArrayList<String> sequence = new ArrayList<String>();
		ArrayList<String> identifiers = new ArrayList<String>();
		ArrayList<String> fileAsString = new ArrayList<String>();
		RandomAccessFile input = null;
		String line;
		try{
			input = new RandomAccessFile(file, "r");
			
			while((line = input.readLine()) != null){
				if(line.isEmpty()) continue;
				fileAsString.add(line);
				
			}
			if(fileAsString.isEmpty()){
				input.close();
				throw new FastaException("Expected identifier");
			}
			
		}
		catch(IOException e){
			e.printStackTrace();
			
		}
		
		String tempSequence  = "";
		for(int i = 0; i < fileAsString.size() ; i++){
			if(isIdentifier(fileAsString.get(i))){
				if(tempSequence != "") {
					sequence.add(tempSequence);
					tempSequence = "";
				}
				if(i == fileAsString.size()-1)throw new FastaException("Expected sequence on line " + (i+2));
				if(isIdentifier(fileAsString.get(i+1))) throw new FastaException("Expected sequence on line " + (i+2));
				if(identifiers.contains(fileAsString.get(i)))throw new FastaException("Repeated Identifier: "+ fileAsString.get(i) +" , this Identifier appears on line : " + fileAsString.indexOf(fileAsString.get(i))+ " and on line "+ (i+1) + " ,please change one of these identifiers");
				identifiers.add(fileAsString.get(i));
			}else{
				if(identifiers.isEmpty()){
					//if(fileAsString.get(i) == "") continue;
					throw new FastaException("Expected identifier");
				}
				fileAsString.set(i, fileAsString.get(i).toUpperCase());
				fileAsString.set(i, fileAsString.get(i).replaceAll("\\s", ""));
				tempSequence = tempSequence+ fileAsString.get(i);
				for(int p = 0; p < fileAsString.get(i).length(); p++){
					if(!Character.isLetter(fileAsString.get(i).charAt(p)))	throw new FastaException("Expected sequence on line "+ (i+1) );
				}
				if(i == fileAsString.size()-1)sequence.add(tempSequence);
			}

		}

		return sequence;
	}

	/**
	 * Runs readSequence on the input and returns either a string presentation of the resulting ArrayList or, 
	 * in case of an Exception, the corresponding message (hint: Exceptions are normal objects with fields/ methods) 
	 * @param file to read
	 * @return String representation of result list or exception message
	 */
	public String handleException(File f) {
		try{
		return readSequences(f).toString();
		}catch(FastaException e){
			return e.getMessage();
		}catch(IOException e){
			return e.getMessage();
		}
	}

	public boolean isIdentifier(String line){
		if(line.charAt(0)== '>') return true;
		if(line == "") return false;
		if(line.isEmpty())return false;
		return false;
		//problema con las leerzeichnen, preguntar que se debe hacer
	}

}

