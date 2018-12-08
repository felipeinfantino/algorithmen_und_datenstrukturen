
public class Main {
	
	public static void main(String[] arg){
		Picture pictureA = new Picture("tests/testgrids/10x100.png");
		GridGraph g = new GridGraph(pictureA);
		Node startNode = g.getCellNode(4,50);
		Node targetNode = g.getCellNode(5,50);
		System.out.println(g.getShortestPathAStar(startNode, targetNode));
	}
}
