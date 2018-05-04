package Graph;

public class Node {
	protected Coord xy;
	protected Node[] neighbors = new Node[4];

	public Node(Coord coord) {
		xy = coord;
		
		for (int n = 0; n<neighbors.length; n++) {
			neighbors[n] = null;			
		}
			
	}

	public Coord getXy() {
		return xy;
	}

	public Node[] getNeighbors() {
		return neighbors;
	}
}

