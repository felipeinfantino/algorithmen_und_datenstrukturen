import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements two methods. These check an array on a few
 * characteristics.
 * 
 * @author AlgoDat-Tutoren
 * 
 */
public class ArrayCheck {

	/**
	 * Tests all elements of the given array, if they are divisible by the given
	 * divisor.
	 * 
	 * @param arr
	 *            array to be tested
	 * @param divisor
	 *            number by which all elements of the given array should be
	 *            divisible
	 * @return true if all elements are divisible by divisor
	 */
	public boolean allDivisibleBy(ArrayList<Integer> arr, int divisor) {
        if(arr == null ||arr.isEmpty() || divisor == 0 ) return false;
        for(int i = 0; i< arr.size() ; i++){
        	if((arr.get(i) % divisor) != 0) return false;
        }
        return true;
    }

	/**
	 * Tests if the given two arrays are anagrams of each other
	 * 
	 * @param arr1
	 *            first array to be compared
	 * @param arr2
	 *            second array to be compared
	 * @return true if the two arrays are an anagram
	 */
    public boolean isAnagram(ArrayList<Character> arr1, ArrayList<Character> arr2) {
    	//erstmal beide ArrayList in nur Buchstaben absortieren , dann alles in kleiner buchstaben , dann sortieren und dann vergleichen
    	if( arr1 == null || arr2 == null ||arr1.isEmpty() || arr2.isEmpty()) return false;
    	
    	for(int i = arr1.size()-1; i >=0; i--){
    		if(Character.isLetter(arr1.get(i)))	arr1.set(i,Character.toLowerCase(arr1.get(i)));
    		else arr1.remove(i);
    	}
    	for(int i = arr2.size()-1; i >= 0; i--){
    		if(Character.isLetter(arr2.get(i))) arr2.set(i,Character.toLowerCase(arr2.get(i)));
    		else arr2.remove(i);
    	}
    	
    	Collections.sort(arr1);
    	Collections.sort(arr2);
    	
    	if(arr1.equals(arr2)) return true;
    	return false;
    }

}

