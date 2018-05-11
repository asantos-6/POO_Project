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

	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i<4; i++) {
			if(this.neighbors[i] != null)
				s = s + " " + this.neighbors[i].getXy() + " ";
			else
				s = s + " -- ";
		}
		return "Node [obs=" + obs + ", xy=" + xy + ", neighbors=" + s + "]\n";
	}

	
}
