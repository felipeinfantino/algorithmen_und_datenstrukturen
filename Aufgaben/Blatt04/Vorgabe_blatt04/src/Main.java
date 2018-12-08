import java.util.Hashtable;

public class Main {
	private static Student s1, s2, s3, s4, s5, s6, s7, s8, s9;
	
	
	public static void main(String[] args) {
		MyHashMap hashmap = new MyHashMap(7);
		s1 = new Student(1, "Max", "Mustermann", "Informatik");
		s2 = new Student(2, "Annika", "Mustermann", "Mathematik");
		s3 = new Student(3, "Alex", "Mustermann", "Biologie");
		s4 = new Student(4, "Julie", "Mustermann", "TI");
		s5 = new Student(5, "Leila", "Mustermann", "Informatik");
		s6 = new Student(6, "Arne", "Mustermann", "Jura");
		s7 = new Student(7, "Bella", "Mustermann", "Physik");
		s8 = new Student(8, "Linus", "Mustermann", "Psychologie");
		s9 = new Student(9, "Albert", "Mustermann", "Informatik");
	
		
		hashmap.add(s7);
		hashmap.add(s9);
		hashmap.add(s5);
		hashmap.add(s1);
		hashmap.add(s6);
		hashmap.add(s2);
		hashmap.add(s4);
		
		
		//hashmap.add(s8);
		
		for(int i = 0 ; i < hashmap.array.length; i++){
			if(hashmap.array[i] == null) {
				System.out.println("null");
				continue;
			}
			System.out.println(hashmap.array[i].name + "  hashnummer : " + hashmap.hashFunction(hashmap.array[i]) + " arrayindex : "+ i);
		}
		hashmap.remove(s7);
		System.out.println();
		System.out.println();
		
		for(int i = 0 ; i < hashmap.array.length; i++){
			if(hashmap.array[i] == null) {
				System.out.println("null");
				continue;
			}
			System.out.println(hashmap.array[i].name + " original hashnumemmer : " + hashmap.hashFunction(hashmap.array[i]) + " arrayindex : "+ i);
		}
//		
//		hashmap.remove(s4);
//		System.out.println();
//		System.out.println();
//		for(int i = 0 ; i < hashmap.array.length; i++){
//			if(hashmap.array[i] == null) continue;
//			System.out.println(hashmap.array[i].name + " original hashnumemmer : " + hashmap.hashFunction(hashmap.array[i]) + " arrayindex : "+ i);
//		}
//		hashmap.resize();
//		System.out.println();
//		System.out.println();
//		for(int i = 0 ; i < hashmap.array.length; i++){
//			if(hashmap.array[i] == null) continue;
//			System.out.println(hashmap.array[i].name + " original hashnumemmer : " + hashmap.hashFunction(hashmap.array[i]) + " arrayindex : "+ i);
//		}
		
	}

}
