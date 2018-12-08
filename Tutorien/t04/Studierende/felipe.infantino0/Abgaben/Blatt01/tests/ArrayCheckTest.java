import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class ArrayCheckTest {
	// NOTTODO DIESE ZEILE BITTE NICHT VERAENDERN!!
	// TODO Fuehrt eure Tests auf diesem ArrayCheck-Objekt aus!
	// Ansonsten werden unsere Tests fehlschlagen
	public ArrayCheck arrayCheckTestObject = new ArrayCheck();

	@Test(timeout = 1000)
	public void testAllDivisibleBy() {
		
		// TODO Schreibt verschiedene Testfaelle, die unterschiedliche Arrays
		// anlegen und an arrayCheck.testAllDivisibleBy uebergeben.
		// Benutzt nur diese Methode.
		// Testet auch randfaelle wie z.B. leere arrays.
		// Bsp: assertTrue("mein test 1", arrayCheckTestObject.allDivisibleBy(testArray, 7));
		ArrayList<Integer> testArray1 = new ArrayList<Integer>(Arrays.asList(1,2,3));
		assertFalse("Test 1" , arrayCheckTestObject.allDivisibleBy(testArray1, 0)); //lleno - con 0 //probar el 0 como dvisor
		
		ArrayList<Integer> testArray2 = new ArrayList<Integer>();
		assertFalse("Test 2" , arrayCheckTestObject.allDivisibleBy(testArray2, 5)); // vacio - con numero // probar el array vacio
		
		ArrayList<Integer> testArray3 = new ArrayList<Integer>(Arrays.asList(2,4,8,10));
		assertFalse("Test 3" , arrayCheckTestObject.allDivisibleBy(testArray3, 3)); // lleno - con no divisible // probar que no es divisible
		
		ArrayList<Integer> testArray4 = new ArrayList<Integer>(Arrays.asList(0,2,3,6,10)); // lleno con cero - con no divisible // probar que un array tenga cero y que no sea divisble
		assertFalse("Test 4" , arrayCheckTestObject.allDivisibleBy(testArray4, 3));
		
		ArrayList<Integer> testArray5 = null;
		assertFalse("Test 5" , arrayCheckTestObject.allDivisibleBy(testArray5, 2)); // null
		
		ArrayList<Integer> testArray6 = new ArrayList<Integer>(Arrays.asList(0,2,4,6,10));
		assertTrue("Test 6" , arrayCheckTestObject.allDivisibleBy(testArray6, 2)); // array con cero y valor divisble
		
		ArrayList<Integer> testArray7 = new ArrayList<Integer>(Arrays.asList(3,6,12));
		assertTrue("Test 7" , arrayCheckTestObject.allDivisibleBy(testArray7, 3)); // array sin cero con valor divisible
		
		ArrayList<Integer> testArray8 = new ArrayList<Integer>(Arrays.asList(-3,9,-12,6,12));
		assertTrue("Test 8" , arrayCheckTestObject.allDivisibleBy(testArray8, 3)); // array con negativos con valor divisible
		
		ArrayList<Integer> testArray9 = new ArrayList<Integer>(Arrays.asList(-3,9,-12,6,12));
		assertTrue("Test 9" , arrayCheckTestObject.allDivisibleBy(testArray9, 3)); // array con negativos con valor divisible
		
		ArrayList<Integer> testArray10 = new ArrayList<Integer>(Arrays.asList(-3,-3,-3,-3,-3));
		assertTrue("Test 10" , arrayCheckTestObject.allDivisibleBy(testArray10, 3)); // array con negativos con valor divisible
		
		//Mas Test
		
		
		
		
		
		
	}

	@Test(timeout = 1000)
	public void testIsAnagram() {
		// TODO Schreibt verschiedene Testfaelle fuer isAnagram in diese Methode
		// Bsp: assertTrue("mein test 2", arrayCheckTestObject.isAnagram(testArray1, testArray2));

		// TODO Schreibt verschiedene Testfaelle fuer isAnagram
		ArrayList<Character> test1arr1 = new ArrayList<Character>(Arrays.asList('a','s','d'));
		ArrayList<Character> test1arr2 = new ArrayList<Character>(Arrays.asList('d','s','a','%', '§'));
		
		ArrayList<Character> test2arr1 = new ArrayList<Character>();
		ArrayList<Character> test2arr2 = new ArrayList<Character>(Arrays.asList('d','s','a','%', '§'));
		
		ArrayList<Character> test3arr1 = new ArrayList<Character>(Arrays.asList('d','s','a','%', '§'));
		ArrayList<Character> test3arr2 = new ArrayList<Character>();
		
		ArrayList<Character> test4arr1 = new ArrayList<Character>(Arrays.asList('a','b','c'));
		ArrayList<Character> test4arr2 = new ArrayList<Character>(Arrays.asList('b','c','a'));
		
		ArrayList<Character> test5arr1 = new ArrayList<Character>(Arrays.asList('A','l','g','o','D','a','t'));
		ArrayList<Character> test5arr2 = new ArrayList<Character>(Arrays.asList('G','a','l','a',' ','T','o','d','!'));
		
		ArrayList<Character> test6arr1 = new ArrayList<Character>(Arrays.asList('E','c','l','i', 'p','s','e'));
		ArrayList<Character> test6arr2 = new ArrayList<Character>(Arrays.asList('P','C',',',' ','L','e','i','s','e'));
		
		ArrayList<Character> test7arr1 = new ArrayList<Character>(Arrays.asList('a','a','a'));
		ArrayList<Character> test7arr2 = new ArrayList<Character>(Arrays.asList('a','a'));
		
		ArrayList<Character> test8arr1 = new ArrayList<Character>(Arrays.asList('a','a','b'));
		ArrayList<Character> test8arr2 = new ArrayList<Character>(Arrays.asList('b','a'));
		
		ArrayList<Character> test9arr1 = null;
		ArrayList<Character> test9arr2 = new ArrayList<Character>(Arrays.asList('b','a'));
		
		ArrayList<Character> test10arr1 = new ArrayList<Character>(Arrays.asList('a','a','b'));
		ArrayList<Character> test10arr2 = null;

		
		assertTrue("Test 1" , arrayCheckTestObject.isAnagram(test1arr1, test1arr2));
		assertFalse("Test 2" , arrayCheckTestObject.isAnagram(test2arr1, test2arr2));
		assertFalse("Test 3" , arrayCheckTestObject.isAnagram(test3arr1, test3arr2));
		assertTrue("Test 4" , arrayCheckTestObject.isAnagram(test4arr1, test4arr2));
		assertTrue("Test 5" , arrayCheckTestObject.isAnagram(test5arr1, test5arr2));
		assertTrue("Test 6" , arrayCheckTestObject.isAnagram(test6arr1, test6arr2));
		assertFalse("Test 7" , arrayCheckTestObject.isAnagram(test7arr1, test7arr2));
		assertFalse("Test 8" , arrayCheckTestObject.isAnagram(test8arr1, test8arr2));
		assertFalse("Test 9" , arrayCheckTestObject.isAnagram(test9arr1, test9arr2));
		assertFalse("Test 10" , arrayCheckTestObject.isAnagram(test10arr1, test10arr2));
		
	}


}

