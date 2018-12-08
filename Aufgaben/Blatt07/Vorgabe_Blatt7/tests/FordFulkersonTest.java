import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FordFulkersonTest
{
	
	public interface FindAugmentingPath_test {

	}

	private Network g1, g2, g3;
	Writer writer;
	@Before
	public void setUp(){
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("tests/testgraphen/graph.txt"), "utf-8")); 
				writer.write("10" + System.lineSeparator() + 
						"0 8 6 0 0 0 0 0 0 0" + System.lineSeparator() + 
						"0 0 0 4 0 0 0 0 0 0" + System.lineSeparator() + 
						"0 0 0 5 0 0 0 0 0 0" + System.lineSeparator() + 
						"0 0 0 0 6 3 0 0 0 0" + System.lineSeparator() + 
						"0 0 0 0 0 2 4 0 0 0" + System.lineSeparator() + 
						"0 0 0 0 0 0 3 0 0 0" + System.lineSeparator() +
						"0 0 0 0 0 0 0 4 3 0" + System.lineSeparator() + 
						"0 0 0 0 0 0 0 0 0 3" + System.lineSeparator() + 
						"0 0 0 0 0 0 0 0 0 3" + System.lineSeparator() +
						"0 0 0 0 0 0 0 0 0 0");
			writer.close();
			
			writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream("tests/testgraphen/graph_disconnected.txt"), "utf-8")); 
					writer.write("4" + System.lineSeparator() +
							"0 2 0 0" + System.lineSeparator() + 
							"0 0 0 0" + System.lineSeparator() + 
							"0 0 0 3" + System.lineSeparator() + 
							"0 0 0 0");
			writer.close();
			
			g1 = GraphIO.loadGraph("tests/testgraphen/graph_pokalfinale.txt");
			g1.setShowSteps(false);
			g2 = GraphIO.loadGraph("tests/testgraphen/graph.txt");
			g2.setShowSteps(false);
			g3 = GraphIO.loadGraph("tests/testgraphen/graph_disconnected.txt");
			g3.setShowSteps(false);

			// VisualGraph v = new VisualGraph(g1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Checks if augmenting Path finds a path in the Graph
	 * T
	 */
	
	@Test
	public void testFindAugmentingPath() {
		g2.residualGraph = g2.initializeResidualGraph();	
		List<Node> nodes1 = g2.residualGraph.findAugmentingPath(0, 7);
		List<Node> nodes = g2.residualGraph.getNodes();

		assertEquals("findAugmentingPath does not work correctly yet", nodes.get(0), nodes1.get(0));
		if(nodes1.get(1) != nodes.get(1) && nodes1.get(1) != nodes.get(2)){
			fail("findAugmentingPath does not work correctly yet");
		}
		assertEquals("findAugmentingPath does not work correctly yet", nodes.get(4), nodes1.get(3));
		if(nodes1.get(3) != nodes.get(4) && nodes1.get(3) != nodes.get(5)){
			fail("findAugmentingPath does not work correctly yet");
		}
		if(nodes1.get(4) != nodes.get(5) && nodes1.get(4) != nodes.get(6)){
			fail("findAugmentingPath does not work correctly yet");
		}
		if(nodes1.get(5) != nodes.get(7) && nodes1.get(5) != nodes.get(6)){
			fail("findAugmentingPath does not work correctly yet");
		}
		if(nodes1.size() > 6 && nodes1.get(6) != nodes.get(7)){
			fail("findAugmentingPath does not work correctly yet");
		}
	}

	/*
	 * Checks if an empty List is returned when trying to find an augmented path in a disconnected graph
	 */
	@Test
	public void testFindAugmentingPathDisconnectedGraph() {
		g3.residualGraph = g3.initializeResidualGraph();

		assertTrue("Your function does not work for disconnected graphs",
				g3.residualGraph.findAugmentingPath(0, 3).isEmpty());
	}

	/*
	 * Checks if an empty list is returned when trying to find an augmented path in a graph with no residual capacity left
	 */
	@Test
	public void testFindAugmentingPathNoResidualCapacityLeft() {
		g2.residualGraph = g2.initializeResidualGraph();
		for (Node n : g2.residualGraph.getNodes()) {
			for (Node n2 : n.getAdjacentNodes()) {
				n.getEdgeTo(n2).weight = 0;
			}
		}
		assertTrue("Your function does not work when there is no capacity left in the graph",
				g2.residualGraph.findAugmentingPath(0, 3).isEmpty());
	}

	/*
	 * Checks if the lowest value in the list is returned
	 */
	@Test
	public void testFindMinimumCapacity() {
		g2.residualGraph = g2.initializeResidualGraph();
		LinkedList<Node> list = new LinkedList<Node>();
		List<Node> nodes = g2.getNodes();

		list.add(nodes.get(0));
		list.add(nodes.get(1));
		list.add(nodes.get(3));
		list.add(nodes.get(4));
		list.add(nodes.get(6));
		list.add(nodes.get(7));

		assertEquals("Your function cannot find the minimum capacity in this list", 4.0,
				g2.residualGraph.findMinCapacity(list), 0.01);
	}

	/*
	 * Checks if 0 is returned when trying to find the minimum capacity in an empty list
	 */
	@Test
	public void testFindMinimumCapacityEmptyList() {
		g2.residualGraph = g2.initializeResidualGraph();
		LinkedList<Node> list = new LinkedList<Node>();
		assertEquals("Your function does not work on an empty list", 0.0, g2.residualGraph.findMinCapacity(list), 0.01);
	}

	/*
	 * Checks if the residual capacity is updated correctly
	 */
	@Test
	public void testUpdateResidualCapacity() {
		g2.residualGraph = g2.initializeResidualGraph();
		LinkedList<Node> list = new LinkedList<Node>();
		List<Node> nodes = g2.getNodes();

		list.add(nodes.get(0));
		list.add(nodes.get(1));
		list.add(nodes.get(3));
		list.add(nodes.get(4));
		list.add(nodes.get(6));
		list.add(nodes.get(7));

		g2.residualGraph.updateResidualCapacity(4.0, list);

		assertEquals("Residual-Capacity was not updated correctly", 4.0, list.get(0).getWeight(list.get(1)), 0.01);
		assertEquals("Residual-Capacity was not updated correctly", 0.0, list.get(1).getWeight(list.get(2)), 0.01);
		assertEquals("Residual-Capacity was not updated correctly", 2.0, list.get(2).getWeight(list.get(3)), 0.01);
		assertEquals("Residual-Capacity was not updated correctly", 0.0, list.get(3).getWeight(list.get(4)), 0.01);
		assertEquals("Residual-Capacity was not updated correctly", 0.0, list.get(4).getWeight(list.get(5)), 0.01);

	}

	/*
	 * Tests FordFulkerson for a larger graph than the given one
	 */
	@Test
	public void testFordFulkerson() {

		assertEquals("Error Calculating Max Flow on Graph: graph . ", 6.0, g2.fordFulkerson(), 0.01);
	}

	@Test(timeout = 2000)
	public void testFordFulkersonGraph_pokalfinale() {
		assertEquals("Error Calculating Max Flow on Graph: graph_pokalfinale . ", 11, g1.fordFulkerson(), 0.01);
	}

}
