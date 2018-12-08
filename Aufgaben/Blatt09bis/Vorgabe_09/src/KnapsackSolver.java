import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;


/**
 * 
 * @author AlgoDat Team
 *
 */
public class KnapsackSolver {

	/**
	 * Tries all possible item combination to solve the knapsack problem. Returns the optimal solution.
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */


	public Knapsack solveKnapsackOptimal(Knapsack k, LinkedList<Item> items){
		if(items == null || k ==  null || items.isEmpty()) return k;
		
		int[][] values = new int[items.size()+1][k.getMaximumWeight()+1];
		
		for(int i = 0; i < items.size()+1; i++){
			values[i][0] = 0;
		}
		for(int i = 0; i < k.getMaximumWeight()+1; i++){
			values[0][i] = 0;
		}
		
		for(int i = 1; i< items.size()+1; i++){
			for(int w = 1; w< k.getMaximumWeight()+1; w++){
				if(items.get(i-1).getWeight() > w ){
					values[i][w] = values[i-1][w];
				}else{
					values[i][w] = Math.max(values[i-1][w], items.get(i-1).getValue() + values[i-1][w-items.get(i-1).getWeight()]);
				}
			}
		}
		
		for(int i = items.size(), w = k.getMaximumWeight(); i > 0 && w > 0; ){
			if(values[i][w] != values[i-1][w]){
				k.addItem(items.get(i-1));
				w = w - items.get(i-1).getWeight();
				i = i-1;
			}else{
				i = i-1;
			}
		}
		
		return k;
		

	}


	/**
	 * Uses the trivial greedy algorithm to solve the Knapsack problem. 
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */
	public Knapsack solveKnapsackGreedyStupid(Knapsack k, LinkedList<Item> items){
		if(items == null || k ==  null || items.isEmpty()) return k;
		
		//Sortiere erstmal die Items mit Bubblesort
		for(int i = 0; i < items.size()-1; i++){
			for(int p = 0; p < items.size()-1; p++){
				if(items.get(p).getValue() > items.get(p+1).getValue()){
					Item temp = items.get(p);
					items.set(p, items.get(p+1));
					items.set(p+1, temp);
					
				}
			}
		}
		//Erstelle eine Liste mit dem sortierte Items
		LinkedList<Item> itemsSortiert = new LinkedList<Item>(items);
		
		//Solange die current weight kleiner als die Maximum weight und die Liste mit Sortierte Items nicht leer ist
		while(k.getMaximumWeight() > k.getCurrentWeight() && !itemsSortiert.isEmpty()){
			//Es wird nur ein Element hinzugefügt wenn der current weight + den weight das untersucht wird (letzte Item der liste) kleiner als die Rucksack kapazität
			if(k.getCurrentWeight() + itemsSortiert.getLast().getWeight() <= k.getMaximumWeight()){
				k.addItem(itemsSortiert.getLast());
			}
			
			//Es wird in jede Iteration die letzte gelösch 
			itemsSortiert.removeLast();
		}
		
		return k;
	}


	/**
	 * Uses a smarter greedy algorithm to solve the Knapsack problem. 
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */
	public Knapsack solveKnapsackGreedySmart(Knapsack k, LinkedList<Item> items){

		//Knapsack k1 = new Knapsack(k.getMaximumWeight());
		//Erzeuge eine TreeMap, wo die Keys sortiert sind
		TreeMap<Double, Item> t = new TreeMap<Double,Item>();
		if(items == null || k ==  null || items.isEmpty()) return k;
		//Ich füge jede element in die Treemap hinzu, wobei das Key ist der Quotient Value/Weight
		for(int i = 0; i < items.size(); i++){
			t.put((double)(items.get(i).getValue())/(items.get(i).getWeight()), items.get(i));
		}
		//Somit haben wir alle Items in die TreeMap wobei der letzte item, das Item mit dem höchsten Value ist
		//Solange die Maximum gewicht grösser als das aktuelle gewicht ist und die TreeMap nicht empty ist
		while(k.getMaximumWeight() > k.getCurrentWeight() && !t.isEmpty()){
			//Die last key hat ja den Höchsten value, denn Bei der TreeMap die keys sortiert sind
			double last = t.lastKey();
			//untersuche ob das Element reinpasst, wenn passt wird das Element in die Rucksack hinzugefügt
			if(k.getCurrentWeight() + t.get(last).getWeight() <= k.getMaximumWeight()){
				k.addItem(t.get(last));
			}
			//lösche das letztes element, und mach weiter
			t.remove(last);
		}
	
		return k;
	}

	//END
}



