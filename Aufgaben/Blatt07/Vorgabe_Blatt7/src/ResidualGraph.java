import java.io.IOException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Arne
 * 
 */
public class ResidualGraph extends DiGraph {

	// -- constructor --
	public ResidualGraph() {
	}
	
	/**
	 * Finds an augmenting path from start to end in the graph A path is
	 * augmenting if all it's edges have residual capacity > 0 (You can choose
	 * from several algorithms to find a path)
	 * 
	 * @param startNodeId
	 *            the id of the start node from where we should start the search
	 * @param endNodeId
	 *            the id of the end node which we want to reach
	 * 
	 * @return the path from start to end or an empty list if there is none
	 */
	public LinkedList<Node> findAugmentingPath(int startNodeId, int endNodeId) {
		LinkedList<Node> augmentingPath = new LinkedList<Node>();
		
		Node endNode = nodes.get(endNodeId);
		Node startNode = nodes.get(startNodeId);
		if(startNode == null || endNode == null) return null;
		if(startNode == endNode) return null;
		
		
		//BFS
		LinkedList<Node> nodeList = new LinkedList<Node>();
		LinkedList<Node> visitedNodes = new LinkedList<Node>();
		 Queue<Node> queue = new LinkedList<Node>();
	        if(startNode == null) return null;
	        nodeList.add(startNode);
	        
	        queue.add(startNode);
	        visitedNodes.add(startNode);
	        while(!queue.isEmpty())
	        {
	       
	            startNode = queue.remove(); 
	          
	            for(Node n: startNode.getAdjacentNodes() )
	            {
	                if(!visitedNodes.contains(n) && startNode.getWeight(n) > 0)
	                {
	                    queue.add(n);
	                    visitedNodes.add(n);
	                    nodeList.add(n);
	                    n.predecessor = startNode;
	                }
	            }
	        }
	       //END BFS
	        //no hay un camino
		if(!nodeList.contains(endNode)) return new LinkedList<Node>();
		
		startNode = nodes.get(startNodeId);
		endNode = nodes.get(endNodeId);
		
		while(startNode != endNode){
			augmentingPath.push(endNode);
			endNode = endNode.predecessor;
		}
		augmentingPath.push(endNode);
		
		if(this.findMinCapacity(augmentingPath) < 0) {
			return null;
		}
	//	System.out.println(augmentingPath);
		return augmentingPath;
	}
	

	/**
	 * Finds the minimal residual capacity over the given path
	 * 
	 * @return the minimal capacity
	 */
	public double findMinCapacity(LinkedList<Node> path) {
		double minCap =  Double.POSITIVE_INFINITY;
		if(path == null) return -1;
		for(int i = 0 ; i < path.size()-1; i++){			
			if(path.get(i).getWeight(path.get(i+1)) < minCap){
				minCap = path.get(i).getWeight(path.get(i+1));
			}
		}
		if(minCap == Double.POSITIVE_INFINITY) return 0;
//		System.out.println(minCap);	
		return minCap;
	}

	/**
	 * Update capacity on given path, to be executed on residual graph
	 */
	public void updateResidualCapacity(double minCapacity, LinkedList<Node> path) {
		if(path == null) return;
		for(int i = 0 ; i<path.size()-1; i++){
			
			if(path.get(i).getEdgeTo(path.get(i+1)) == null) continue;
			
			path.get(i).getEdgeTo(path.get(i+1)).weight = path.get(i).getEdgeTo(path.get(i+1)).weight - minCapacity;
			path.get(i+1).getEdgeTo(path.get(i)).weight = path.get(i+1).getEdgeTo(path.get(i)).weight + minCapacity;
			
//			if(path.get(i).getEdgeTo(path.get(i+1)).weight == 0) {
//				System.out.println(path.get(i).getEdgeTo(path.get(i+1)) + " se removio");
//				path.get(i).edges.remove(path.get(i).getEdgeTo(path.get(i+1)));
//     			path.get(i-1).edges.remove(path.get(i-1).getEdgeTo(path.get(i)));
//			}
		}
		
	}

}

