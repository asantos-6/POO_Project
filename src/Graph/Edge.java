package Graph;
import Graph.Coord;

public class Edge {
	Coord xy_o;
	Coord xy_d;
	int c;
	
	Edge(Coord xy_o, Coord xy_d, int cost){
		this.xy_o = xy_o;
		this.xy_d = xy_d;
		c = cost;
	}
}
