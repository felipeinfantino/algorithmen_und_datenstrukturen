import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//		UnionFindSet<Integer> ufd = new UnionFindSet<Integer>();
		//	    
		//		ufd.add(1);
		//		ufd.add(2);
		//		ufd.add(3);
		//		ufd.add(4);
		//		ufd.add(5);
		//		
		//		ufd.union(1, 2);
		//		ufd.union(1, 3);
		//
		//		ufd.union(4, 3);
		//		ufd.union(4, 5);
		////		System.out.println(ufd.getRepresentative(3));

		Graph g1 = new SimpleGraph();
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/test_kruskal01.txt");
//			System.out.println(g1.getNodes().size() == g1.getNodes().size());
	
			Graph mst = g1.toMinSpanTree();
			System.out.println(mst.getNodes());
			int edgeCount = 0;
			for (Node n : mst.getNodes()) {
				for (Edge e : n.getIncidentEdges()) {
					edgeCount++;
				}
			}
			int edgeCount2 = 0;
			for (Node n : g1.getNodes()) {
				for (Edge e : n.getIncidentEdges()) {
					edgeCount2++;
				}
			}
//			System.out.println(edgeCount +" > "+ edgeCount2);
			
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
