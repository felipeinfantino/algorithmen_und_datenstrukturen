import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		KnapsackSolver ks = new KnapsackSolver();
		
		LinkedList<Item> list = new LinkedList<Item>();
		
		Item item1 = new Item(1, 23 ,92);
		Item item2 = new Item(2,31,57);
		Item item3 = new Item(3,29,49);
		Item item4 = new Item(4,44,68);
		Item item5 = new Item(5, 53,60);
		Item item6 = new Item(6,38,43);
		Item item7 = new Item(7,63,67);
		Item item8 = new Item(8,85,84);
		Item item9 = new Item(9,89,87);
		Item item10 = new Item(10,82,72);
		
		//Test Youtube
		
//		Item item1 = new Item(1, 3 ,100);
//		Item item2 = new Item(2,2,20);
//		Item item3 = new Item(3,4,60);
//		Item item4 = new Item(4,1,40);
		list.add(item1);
		list.add(item2);
		list.add(item3);
		list.add(item4);
		list.add(item5);
		list.add(item6);
		list.add(item7);
		list.add(item8);
		list.add(item9);
		list.add(item10);
		
		LinkedList<Item> list2 = new LinkedList<Item>();
		Item item01 = new Item(1, 1 ,1);
		Item item02 = new Item(2,3,4);
		Item item03 = new Item(3,4,5);
		Item item04 = new Item(4,5,7);
		Knapsack k4 = new Knapsack(10);
		list2.add(item01);
		list2.add(item02);
		list2.add(item03);
		list2.add(item04);
		
		
		Knapsack k1 = new Knapsack(165);
		Knapsack losungSmart = ks.solveKnapsackGreedySmart(k1,list);
		System.out.println("Gewicht : " +losungSmart.currentWeight+ "\n" + " Value :" + losungSmart.currentValue);
		
		Knapsack k2 = new Knapsack(165);
		Knapsack losungStupid = ks.solveKnapsackGreedyStupid(k2,list);
		System.out.println("Gewicht : "+losungStupid.currentWeight+ "\n" + " Value : " +losungStupid.currentValue);
		
		Knapsack k3 = new Knapsack(165);
		Knapsack losungOptimal = ks.solveKnapsackOptimal(k3, list);
		System.out.println("Gewicht : "+losungOptimal.currentWeight+ " Value :" + losungOptimal.currentValue);


	}

}
