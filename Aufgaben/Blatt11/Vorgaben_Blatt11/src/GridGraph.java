import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import javax.management.RuntimeErrorException;

import java.util.LinkedList;
import java.util.List;

/**
 * A data structure to store a graph consisting of a set of unlabeled nodes and
 * a set of directed, weighted edges connecting the nodes.
 * 
 * The interface provides methods to add nodes and edges. Both methods can be
 * called at all time and the data structure should not assume a limitation on
 * the number of nodes or edges.
 * 
 * 
 * Furthermore, there are methods to access a single edge or to check whether
 * two nodes are connected as well as methods to access all nodes connected with
 * a specific node and thus all outgoing edges.
 * 
 * @author Joerg Schneider, modified by AlgoDat-Tutoren
 */
public class GridGraph extends DiGraph {

	public HashSet<CellNode> cellnodes; 
	protected Picture picture;
	

	int rows;
	int columns;

	public GridGraph() {
		super();
		cellnodes = new HashSet<CellNode>();
		rows = 0;
		columns = 0;
	}
	

	public GridGraph(Picture p) {
		super();
		
		
		picture = p;
		cellnodes = new HashSet<CellNode>();

		//Load if node is free
		rows = p.getHeight();
		columns = p.getWidth();
		RGBColor[][] pixels = p.getImageMatrix();
		for (int i = 0; i< rows; i++) {
			for (int j = 0; j< columns; j++) {
				RGBColor pixel = pixels[j][i];
				boolean isFree = pixel.isWhite();
				if (isFree) {
					this.addNode(i,j);
				}
			}
		}

		//connect all nodes with the given moves:
		for (CellNode n : getCellNodes()) {
			int i = n.getRow();
			int j = n.getColumn();

			//Connect every node to its 4 direct neighbors:
			CellNode neighbor;
			neighbor = getCellNode(i-1,j);			
			if (neighbor != null) {n.addEdge(neighbor, 1);}
			neighbor = getCellNode(i+1,j);			
			if (neighbor != null) {n.addEdge(neighbor, 1);}
			neighbor = getCellNode(i,j-1);			
			if (neighbor != null) {n.addEdge(neighbor, 1);}
			neighbor = getCellNode(i,j+1);			
			if (neighbor != null) {n.addEdge(neighbor, 1);}

			/*uncomment this to have diagonal moves:*/
			//			neighbor = getCellNode(i+1,j+1);			
			//			if (neighbor != null) {n.addEdge(neighbor, 1);}
			//			neighbor = getCellNode(i-1,j+1);			
			//			if (neighbor != null) {n.addEdge(neighbor, 1);}
			//			neighbor = getCellNode(i+1,j-1);			
			//			if (neighbor != null) {n.addEdge(neighbor, 1);}
			//			neighbor = getCellNode(i-1,j-1);			
			//			if (neighbor != null) {n.addEdge(neighbor, 1);}

		}


	}


	Picture toPicture(List<Node> path) {

		RGBColor[][] pixels = new RGBColor[rows][columns];

		//create color palette:
		RGBColor white = new RGBColor(255,255,255);
		RGBColor gray  = new RGBColor(128,128,128);
		RGBColor black = new RGBColor(0,0,0);
		RGBColor red   = new RGBColor(255,0,0);

		//Paint all pixels black
		for(int i=0; i<rows; i++)
			for(int j=0; j<columns; j++)
				pixels[i][j] = black;

		//Paint pixels represented in the graph 
		for(CellNode n : cellnodes) {
			RGBColor pixelcolor;
			if (n.status != CellNode.WHITE) {pixelcolor = gray;}
			else 						   {pixelcolor = white;}
			pixels[n.getRow()][n.getColumn()] = pixelcolor;
		}

		//Paint path red
		if(path != null)
		{
			for(Node p : path)				
				pixels[getCellNode(p).getRow()][getCellNode(p).getColumn()] = red;
		}

		Picture p = new Picture(pixels);
		return p;	
	}



	@Override
	public Node addNode() {
		System.out.println("You need to supply a row and column index. Not adding any node.");
		return null;
	}

	/**
	 * Adds a node to the graph.
	 * 
	 * As the nodes are unlabeled this method has no parameter. An identifier is
	 * returned which can be used to access this specific node when calling the
	 * other operations.
	 * 
	 * @return the added Node.
	 */
	public Node addNode(int i,int j) {
		CellNode newOne = null;
		newOne = new CellNode(i,j);
		nodes.put(newOne.id, newOne);
		cellnodes.add(newOne);
		return newOne;
	}



	public Collection<CellNode> getCellNodes() {
		return cellnodes;
	}

	public CellNode getCellNode(int id) {
		return getCellNode(nodes.get(id));  //Simply downcast, as we ensure that we only add CellNodes
	}

	public CellNode getCellNode(int i, int j) {
		if (i < 0 || j < 0 || i >= rows || j >= columns) {return null;} //that  node cannot exist
		int id = CellNode.computeID(i,j);
		CellNode n;
		try {
			n = getCellNode(nodes.get(id));  //Simply downcast, as we ensure that we only add CellNodes
		} catch (Exception e){
			n = null;
		}
		return n;
	}

	/**
	 * type-safe accessor to convert Node objects back to CellNode objects 
	 */
	public CellNode getCellNode(Node n) {
		if (cellnodes.contains(n)) {
			return (CellNode) n;   //downcast
		} else {
			throw new IllegalArgumentException("node is not in this graph!");			
		}
	}

	public int getNodeRow(Node n) {
		CellNode cellnode = (CellNode) n; //Downcast, as we ensure that we only add CellNodes
		return cellnode.getRow();
	}

	public int getNodeColumn(Node n) {
		CellNode cellnode = (CellNode) n; //Downcast, as we ensure that we only add CellNodes
		return cellnode.getRow();
	}


	public void populateAStar(int startNodeID, int targetNodeID) {
		populateAStar(getCellNode(startNodeID), getCellNode(targetNodeID));
	}

	public void populateAStar(CellNode startCNode, CellNode targetCNode) {		
		if(startCNode == null || !cellnodes.contains(startCNode)){return;}
		if(targetCNode == null || !cellnodes.contains(targetCNode)){return;}
		//Hier verwende die Euklidische Heuristik , in der Methode getShortestPathAstar ist die Begründung
		Comparator<CellNode> c = new HeuristicEuclidean(targetCNode);
		PriorityQueue<Node> p = new PriorityQueue(1000,c);
		
		for(CellNode cn : cellnodes){
			cn.distance = Integer.MAX_VALUE;
		}
		startCNode.predecessor= null;
		startCNode.distance = 0;
		p.add(startCNode);
		while(!p.isEmpty()){
			Node its = p.poll();
			if((Node)its == targetCNode)break;
			for(Node n : its.getAdjacentNodes()){
				int weight = its.getWeight(n);
				int distance = its.distance + weight;
				if(distance < 0) throw new RuntimeException("Negative Edge");
				if(distance < n.distance){
					n.status = Node.GRAY;
					n.distance = distance;
					n.predecessor = its;
					p.add(n);
				}
			}

		}

	}

	public List<Node> getShortestPathAStar(int startNodeID, int targetNodeID) {
		return getShortestPathAStar(getCellNode(startNodeID), getCellNode(targetNodeID));
	}

	/**
	 * Calculates a List of Node indices that describe the shortet path from
	 * start node to target node. The A*-algorithm is used for
	 * calculation.
	 * 
	 * @param startNode
	 *            the the target node
	 * @param targetNode
	 *            the the target node
	 * @return the list of nodes, or null if no path exists
	 */
	public List<Node> getShortestPathAStar(Node startNode, Node targetNode) {
		//Habe die euklidische Heuristik verwendet, denn das representiert eine Luftlinie und es ist genauer als
		//Manhatan und wie im Tutorium das gesehen haben die Manhantan kann zu fehler führen.
		
		CellNode s = getCellNode(startNode);
		CellNode t = getCellNode(targetNode);
		
		LinkedList<Node> l = new LinkedList<Node>();
		Stack <Node> st = new Stack<Node>();
		if(s == null || t == null) return null;
		populateAStar(s, t);
		CellNode its = t;
		while(its != null){
			st.add(its);
			its = (CellNode)(its.predecessor);
		}
		while(!st.isEmpty()){
			l.add(st.pop());
		}
		return l;
	}


	// ---- Methods to visualize the operations of a graph algorithm -----




}

