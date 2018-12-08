
public class Main {

	public static void main(String[] args) {
		DiGraph g = new DiGraph();
		
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		
		g.addEdge(0, 1, 2);
		g.addEdge(0, 4, 2);
		g.addEdge(0, 5, 4);
		
		g.addEdge(1, 0, 2);
		g.addEdge(1, 2, 4);
		
		g.addEdge(2, 1, 4);
		g.addEdge(2, 5, 1);
		
		g.addEdge(3, 5, 2);
		
		g.addEdge(4, 0, 2);
		g.addEdge(4, 5, 1);
		
		g.addEdge(5, 0, 4);
		g.addEdge(5, 2, 1);
		g.addEdge(5, 3, 2);
		g.addEdge(5, 4, 1);
		
		g.breadthFirstSearch(0);
		//muy raro que si runeo los dos me tira cosas diferentes a runear solo uno y despues el otro
//		g.depthFirstSearch(0);
		
//		ejemplo 2
		
		
//		g.addEdge(0, 1, 1);
//		g.addEdge(1, 2, 1);
//		g.addEdge(2, 0, 1);
//		
//		g.breadthFirstSearch(0);
		
	}

}
