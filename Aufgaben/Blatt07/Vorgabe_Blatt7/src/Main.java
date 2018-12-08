import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.SynchronousQueue;

public class Main {

	public static void main(String[] args) {
		Network g = new Network();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		
//		g.addEdge(0, 1, 0);
//		g.addEdge(0, 2, 0);
//		
//		g.addEdge(1, 3, 0);
//		g.addEdge(1, 2, 0);
//		
//		g.addEdge(2, 1, 0);
//		g.addEdge(2, 3, 0);
//		
//		g.addEdge(3, 4, 0);
//		g.addEdge(3, 5, 0);
//		
//		g.addEdge(4, 6, 0);
//		g.addEdge(4, 5, 0);
//		
//		g.addEdge(5, 6, 0);
//		g.addEdge(5, 4, 0);
		
		g.addEdge(0, 1, 200);
		g.addEdge(0, 2, 1000);
		
		g.addEdge(1, 3, 400);
		g.addEdge(1, 2, 1000);
		
		g.addEdge(2, 1, 1000);
		g.addEdge(2, 3, 800);
		
		g.addEdge(3, 4, 800);
		g.addEdge(3, 5, 300);
		
		g.addEdge(4, 6, 1000);
		g.addEdge(4, 5, 1000);
		
		g.addEdge(5, 6, 200);
		g.addEdge(5, 4, 1000);
		
		
		g.residualGraph = g.initializeResidualGraph();
		System.out.println(g.fordFulkerson());
//		System.out.println(g.residualGraph.breadthFirstSearch(0));
//		ArrayList<Node> a = new ArrayList<Node>((g.residualGraph.findAugmentingPath(0, 6)));
//		for(int i =0 ; i< a.size(); i++){
//			System.out.println(a.get(i).id);
//		}
	//	System.out.println(g.residualGraph.findAugmentingPath(0, 5));
//		System.out.println(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)));
//    	g.residualGraph.updateResidualCapacity(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)), g.residualGraph.findAugmentingPath(0, 6));
//    	System.out.println(g.residualGraph.findAugmentingPath(0, 6));
//		System.out.println(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)));
//    	g.residualGraph.updateResidualCapacity(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)), g.residualGraph.findAugmentingPath(0, 6));
//    	System.out.println(g.residualGraph.findAugmentingPath(0, 6));
//		System.out.println(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)));
//    	g.residualGraph.updateResidualCapacity(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)), g.residualGraph.findAugmentingPath(0, 6));
//    	System.out.println(g.residualGraph.findAugmentingPath(0, 6));
//		System.out.println(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)));
//        g.residualGraph.updateResidualCapacity(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)), g.residualGraph.findAugmentingPath(0, 6));
//        System.out.println(g.residualGraph.findAugmentingPath(0, 6));
//		System.out.println(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)));
//    	g.residualGraph.updateResidualCapacity(g.residualGraph.findMinCapacity(g.residualGraph.findAugmentingPath(0, 6)), g.residualGraph.findAugmentingPath(0, 6));
		
	}

}
