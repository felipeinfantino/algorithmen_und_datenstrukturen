import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Uwe + Damien + Arne
 * 
 */
public class Network extends DiGraph {

	ResidualGraph residualGraph;
	
	// -- constructor --
	public Network() {
	}

	/**
	 * Searches for sources in the graph
	 * 
	 * @return All sources found in the graph
	 */
	public Node findSource() {
		LinkedList<Node> sources = new LinkedList<Node>();
		boolean isSource = true;
		// source <-> no incoming edges
		for (Node n : nodes.values()) {
			isSource = true;
			for (Node m : nodes.values()) {
				if (!m.equals(n) && isConnected(m, n)) {
					isSource = false;
					break;
				}
			}
			if (isSource)
				sources.add(n);
		}
		// error handling
		if (sources.size() == 0)
			System.out.println("Found no source in network");
		else if(sources.size() > 1)
			System.out.println("Found more than one source in network");
		
		return sources.getFirst();
	}

	/**
	 * Searches the graph for sinks.
	 * 
	 * @return All sinks found in the graph
	 */
	public Node findSink() {
		LinkedList<Node> sinks = new LinkedList<Node>();
		// sink <-> no outgoing edges
		for (Node n : nodes.values()) {
			if (n.getOutgoingEdges().isEmpty())
				sinks.add(n);
		}
		// error handling
		if (sinks.size() == 0)
			System.out.println("Found no sink in network");
		else if(sinks.size() > 1)
			System.out.println("Found more than one sink in network");
		return sinks.getFirst();
	}

	/**
	 * Computes the maximum flow over the network with the Ford-Fulkerson
	 * Algorithm
	 * 
	 * @returns Value of maximal flow
	 */
	public double fordFulkerson() {
		residualGraph = this.initializeResidualGraph();
		double maxFlow = 0;
		for(Node n : residualGraph.getNodes()){
			if(residualGraph.nodes.get(n.id).edges.isEmpty()){
				nodes.remove(n.id);
				residualGraph.nodes.remove(n.id);
			}
		}

		while(residualGraph.findMinCapacity(residualGraph.findAugmentingPath(findSource().id, findSink().id)) > 0){
			maxFlow = maxFlow + residualGraph.findMinCapacity(residualGraph.findAugmentingPath(findSource().id, findSink().id));
			residualGraph.updateResidualCapacity(residualGraph.findMinCapacity(residualGraph.findAugmentingPath(findSource().id, findSink().id)), residualGraph.findAugmentingPath(findSource().id, findSink().id));
		}

		return maxFlow;
	}


	/**
	 * Builds the residual graph to a flow graph
	 * 
	 * @return the residual graph to this flow graph
	 */
	public ResidualGraph initializeResidualGraph() {

		ResidualGraph residualGraph = new ResidualGraph();

		// adding nodes
		for (int i = 0; i < nodes.values().size(); i++)
			residualGraph.addNode();
		// adding edges
		for (Node n : nodes.values()) {
			for (Edge e : n.getOutgoingEdges()) {
				// Add forward edges with same capacity
				residualGraph.addEdge(n.id, e.endNode.id, e.weight);
				// Add backwards edge
				residualGraph.addEdge(e.endNode.id, n.id, 0);
			}
		}

		return residualGraph;
	}

}

