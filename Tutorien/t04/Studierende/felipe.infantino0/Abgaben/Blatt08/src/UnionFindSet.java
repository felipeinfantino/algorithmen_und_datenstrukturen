import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.util.Elements;

/**
 * A class implementing a Union-Find-data structure with representatives.
 * 
 * @author AlgoDat-Team
 */
public class UnionFindSet<T>{

	//You can use this map to store the representant for each element of the Union find structure
	// <Node, repre>
	// Key , Value
	private HashMap<T,T> element2representative;

	public UnionFindSet() {
		element2representative = new HashMap<>();
	}

	/**
	 * Takes a collection of n elements and adds 
	 * n disjoint partitions each with one element in it.
	 * 
	 * @param set
	 *            The set to be partitioned.
	 */
	public void add(List<T> elements) {
		for(int i = 0; i < elements.size(); i++){
			if(!element2representative.containsKey(elements.get(i))){
				element2representative.put(elements.get(i),elements.get(i));
			}
		}
	}

	/**
	 * Creates one disjoint partition with the element in it 
	 * and adds it to the Union-Find data structure
	 * 
	 * @param element
	 *            The element to put in the partition.
	 */
	public void add(T element) {
		if(!element2representative.containsKey(element)){
			element2representative.put(element, element);
		}
		else{
			throw new RuntimeException("Element schon drin");
		}
	}

	/**
	 * Retrieves the partition which contains the wanted element.
	 * 
	 * A partition is identified by a single, representative element.
	 * This function returns the representative of the partition
	 * that contains x. 
	 * 
	 * @param x
	 *            The element whose partition we want to know
	 * @return 
	 *            The representative element of the partition
	 */
	public T getRepresentative(T x) {
		if(element2representative.containsKey(x)){
			return element2representative.get(x);
		}
		return null;
	}

	/**
	 * Joins two partitions. First it retrieves the partitions containing the
	 * given elements. Removes one of the joined partitions from
	 * <code>partitions</code>.
	 * 
	 * @param x
	 *            One element whose partition is to be joined.
	 * @param y
	 *            The other element whose partition is to be joined.
	 */
	public void union(T x, T y) {
		if(x == null || y == null) return;
		if(!element2representative.containsKey(x)||!element2representative.containsKey(y)) return;
		if(element2representative.get(x).equals(element2representative.get(y)))return;
		
		T representativX = getRepresentative(x);
		for(T c : element2representative.keySet()){
			if(getRepresentative(c).equals(representativX)){
				element2representative.put(c, getRepresentative(y));
			}
		}

	}
	
	public String toString(){
		return element2representative.toString();
	}
}

