package Graph;
import Graph.Coord;

class Node {
	Coord xy;
	protected boolean obs;
	Node[] neighbors = new Node[4];

	Node(Coord coord, boolean obstacle) {
		xy = coord;
		obs = obstacle;
		
		for (int n = 0; n<neighbors.length; n++) {
			neighbors[n] = null;			
		}
			
	}
}
