
public class Main {

	public static void main(String[] args) {
		DiGraph g = new DiGraph();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		g.addNode();
		
		g.addEdge(0, 1, -1);
		g.addEdge(0, 4, 3);
		g.addEdge(0, 5, 5);
		
		g.addEdge(1, 0, -1);
		g.addEdge(1, 2, 5);
		
		g.addEdge(2, 1, 5);
		g.addEdge(2, 5, 2);
		
		g.addEdge(3, 5, 3);
		
		g.addEdge(4, 0, 3);
		g.addEdge(4, 5, 2);
		
		g.addEdge(5, 0, 5);
		g.addEdge(5, 2, 2);
		g.addEdge(5, 3, 3);
		g.addEdge(5, 4, 2);
		
		System.out.println(g.getShortestPathDijkstra(0, 3));
//		for(int i = 0; i< g.nodes.size() ; i++){
//			System.out.println("Node " + g.nodes.get(i).id +" Distance to Node 0 : " +g.nodes.get(i).distance + "  Predecessor : "+ g.nodes.get(i).predecessor);
//		}
//		System.out.println(g.nodes.toString());

	}

}
