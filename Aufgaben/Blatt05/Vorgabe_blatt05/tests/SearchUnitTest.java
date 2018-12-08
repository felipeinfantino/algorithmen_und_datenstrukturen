import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SearchUnitTest {
	private Graph g1;

	@Before
	public void setUp() throws Exception {
		// read graph from file
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/graphBFS_VS_DFS.txt");
			g1.setShowSteps(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("tests/testgraphen/graphEmpty"), "utf-8"));) {
			writer.write("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	@Test
	public void testDepthFirstOrderEmptyGraph() {
		try {
			Graph gEmpty = GraphIO.loadGraph("tests/testgraphen/graphEmpty");
			List<Node> list = gEmpty.depthFirstSearch(0);
			assertEquals("List is not null", null, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	@Test
	public void testDepthFirstOrderNonExistingNode() {
		List<Node> list = g1.depthFirstSearch(6);
		assertEquals("List is not null", null, list);
	}

	@Test
	public void testDepthFirstOrder() {
		List<Node> list = g1.depthFirstSearch(0);
		assertTrue("Node 0 is not in the list", list.contains(new Node(0)));
		assertTrue("Node 3 is not in the list", list.contains(new Node(3)));
		assertTrue("Node 1 is not in the list", list.contains(new Node(1)));
		assertTrue("Node 4 is not in the list", list.contains(new Node(4)));
		assertTrue("Node 2 is not in the list", list.contains(new Node(2)));
		assertFalse("Node 5 is in the list but it shouldn't be", list.contains(new Node(5)));
		
		if(list.indexOf(g1.getNodes().get(1)) > list.indexOf(g1.getNodes().get(2))){
			fail("Wrong order");
		}
		if(list.indexOf(g1.getNodes().get(3)) > list.indexOf(g1.getNodes().get(4))){
			fail("Wrong order");
		}
		if(Math.abs(list.indexOf(g1.getNodes().get(3)) - list.indexOf(g1.getNodes().get(1))) != 2){
			fail("Wrong order");
		}
	}

	@Test
	public void testBreadthFirstOrderEmptyGraph() {
		try {
			Graph gEmpty = GraphIO.loadGraph("tests/testgraphen/graphEmpty");
			List<Node> list = gEmpty.breadthFirstSearch(0);
			assertEquals("List is not null", null, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 

	@Test
	public void testBreadthFirstOrderNonExistingNode() {
			List<Node> list = g1.breadthFirstSearch(6);
			assertEquals("List is not null", null, list);
	}
  
	@Test
	public void testBreadthFirstOrder() {
		List<Node> list = g1.breadthFirstSearch(0);
		assertTrue("Node 0 is not in the list", list.contains(new Node(0)));
		assertTrue("Node 3 is not in the list", list.contains(new Node(3)));
		assertTrue("Node 1 is not in the list", list.contains(new Node(1)));
		assertTrue("Node 4 is not in the list", list.contains(new Node(4)));
		assertTrue("Node 2 is not in the list", list.contains(new Node(2)));
		assertFalse("Node 5 is in the list but it shouldn't be", list.contains(new Node(5)));
		if(list.indexOf(g1.getNodes().get(3)) > list.indexOf(g1.getNodes().get(4))){
			fail("Wrong order");
		}
		if(list.indexOf(g1.getNodes().get(1)) > list.indexOf(g1.getNodes().get(2))){
			fail("Wrong order");
		}
	}	
	

}
