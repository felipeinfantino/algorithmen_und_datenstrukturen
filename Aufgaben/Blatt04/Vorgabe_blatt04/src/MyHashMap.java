/**
 * 
 * @author Algorithm and Datastructures Team SS2016
 * @version 1.0  
 * 
 */
import java.lang.RuntimeException;
public class MyHashMap {

	/**
	 * Use this array to store the values
	 * DO NOT MODIFY OR REMOVE THIS Attribute. Our tests rely on it.
	 */
	Student[] array;
	Student[] temp; 


	/**
	 * Tries inserting a Student into the hash map.
	 * Throws RunTimeException, if the student is already in the table or the table is full.
	 */
	public void add(Student s){
		
		if(isVoll()) throw new RuntimeException("Hastable voll");
		if(this.contains(s)) throw new RuntimeException("der Student ist schon drin");
		int hashNummer = hashFunction(s);
		if(array[hashNummer] == null ) array[hashNummer] = s;
		else{
			while(array[hashNummer] != null){
				if(hashNummer == array.length-1) hashNummer = -1;
				hashNummer++;
			}
			array[hashNummer] = s;
		}
	}
	
	public boolean isVoll(){
		for(int i = 0; i< array.length ; i++){
			if(array[i] == null){
				return false;
			}
		}
		return true;
	}

	/**
	 * Try removing a Student from the hash table.
	 * You should use the same implementation for remove discussed in the tutorial.
	 * You should NOT use the lazy deletion strategy (adding a special flag key indicating a deleted key)
	 * See https://en.wikipedia.org/wiki/Linear_probing#Deletion for more details about the algorithm!
	 * Throw RunTimeException if the hash table contained the Student
	 */
	public void remove(Student s){
		if(!this.contains(s))throw new RuntimeException("Der Student ist nicht mal drin");
		int hashNummer = hashFunction(s);

		while(array[hashNummer] != null){
			if(array[hashNummer].equals(s)){
				this.array[hashNummer] = null;
			}
			if(hashNummer == array.length-1) hashNummer = -1;
			hashNummer++;
		}
		if(hashNummer == array.length-1) hashNummer = -1;
		hashNummer++;
		while(array[hashNummer]!= null){
			Student temp = array[hashNummer];
			array[hashNummer] = null;
			add(temp);
			if(hashNummer == array.length-1) hashNummer = -1;
			hashNummer++;
			
			
		}
//		temp=  new Student[array.length];
//		if(hashNummer == array.length-1) hashNummer = -1;
//		for(int j=0, k= (hashNummer+1); (k != hashNummer && j< array.length); j++, k++){
//			temp[j] = array[k];
//			if(k == array.length-1) k = -1;
//			
//		}
//		for(int i = 0; i <array.length; i++){
//			array[i] = null;
//		}
//		for(int i = 0; i< array.length; i++){
//			if(temp[i] == null)continue;
//			add(temp[i]);
//		}
//	
	}

	/**
	 * Returns true, if the hash table contains the given Student
	 */
	public boolean contains(Student s){
		if(s == null) return false;
		int hashNummer = this.hashFunction(s);
		while(array[hashNummer] != null){
			if(array[hashNummer].equals(s))return true;
			if(hashNummer == array.length-1) hashNummer = -1;
			hashNummer++;
		}
		return false;
	}

	/**
	 * @param h Hashvalue to search for
	 * @return Number of Student in the hash table that have the hashvalue h
	 */
	public int getNumberStudentsWithHashvalue(int h){
		int n = 0;
		int counter = h;
		if(array[h] == null) return 0;
		while(array[counter] != null){
			if(hashFunction(array[counter]) == h) n++;
			if(counter == array.length-1) counter = -1;
			counter++;
		}
		return n;
	}

	/**
	 * Doubles the size of the hash table. Recomputes the position of all elements using the
	 * new function.
	 */
	public void resize(){
		Student[] neueArray = new Student[2* array.length];
		
		for(int i = 0; i < array.length; i++){
			if(array[i] != null){
				int hashNummer = Math.abs(array[i].hashCode()) % neueArray.length;	
				while(neueArray[hashNummer] != null){
					if(hashNummer == neueArray.length-1) hashNummer = -1;
					hashNummer++;
				}
				neueArray[hashNummer] = array[i];
			}
		}
		this.array = neueArray;
	}

	/**
	 * This method return the array in which the strings are stored.
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public Student[] getArray(){
		return array;
	}

	/**
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public void setArray(Student[] array){
		this.array = array;
	}

	/**
	 * Runs the hash function for Student s (dependent on the size of the hash table)
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 * @param s Student
	 * @return the hash value for a student. (The position where it would be saved given no collisions)
	 */
	public int hashFunction(Student s){
		int hashvalue = Math.abs(s.hashCode()) % array.length;
		return hashvalue;
	}

	/**
	 * Constructor to initialize the hash with a given capacity
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public MyHashMap(int capacity){
		array = new Student[capacity];
	}

}

