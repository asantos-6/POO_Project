package Graph;
import Graph.Coord;

public class Edge {
	Coord xy_o;
	Coord xy_d;
	int c;
	
	public Edge(Coord xy_o, Coord xy_d, int cost){
		this.xy_o = xy_o;
		this.xy_d = xy_d;
		c = cost;
	}

	public Coord getXy_o() {
		return xy_o;
	}

	public Coord getXy_d() {
		return xy_d;
	}

	public int getC() {
		return c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xy_d == null) ? 0 : xy_d.hashCode());
		result = prime * result + ((xy_o == null) ? 0 : xy_o.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (xy_d == null) {
			if (other.xy_d != null)
				return false;
		} else if (!xy_d.equals(other.xy_d))
			return false;
		if (xy_o == null) {
			if (other.xy_o != null)
				return false;
		} else if (!xy_o.equals(other.xy_o))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "XY1: " + xy_o + " / XY2: " + xy_d + " / Custo: "+ c;
	}
	
}
