package Graph_grid;
import Graph.Coord;
import Graph.Node;

public class Node_grid extends Node {
	protected boolean obs;

	public Node_grid(Coord coord, boolean observation) {
		super(coord);
		obs = observation;
	}

	public boolean isObs() {
		return obs;
	}

}
