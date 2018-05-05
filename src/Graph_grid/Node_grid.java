package Graph_grid;
import Graph.Coord;
import Graph.Node;

public class Node_grid extends Node {
	protected boolean obs;

	public Node_grid(Coord coord, boolean obstacle) {
		super(coord);
		obs = obstacle;
	}

	public boolean isObs() {
		return obs;
	}

}
