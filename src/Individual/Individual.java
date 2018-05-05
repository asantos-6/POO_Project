package Individual;

import java.util.List;
import Graph.Coord;
import Graph_grid.Node_grid;
import PEC.PEC;
import Event_grid.*;


public class Individual {
	int cost;
	int length;
	int dist;
	boolean alive = true;
	float comfort;
	List<Coord> path = null;
	Node_grid node;
	
	Coord xy_f;
	int cmax, k, N, M;
	
	public Individual(Coord xy_f, Coord xy, Node_grid[][] graph, int mew, int delta, int ro, PEC pec, int t, int cmax, int k, int N, int M) {
		cost=0;
		length=0;
		comfort=0;
		path.add(xy);
		node = graph[xy.getX()][xy.getY()];
		
		this.xy_f = xy_f;
		this.cmax = cmax;
		this.k = k;
		this.N=N;
		this.M=M;
		
		
		dist = calcDist(xy_f, xy);
		
		new Death(mew, pec, t, this);
		new Move(delta, pec, t, this);
		new Reproduction(ro, pec, t, this);
		
	}
	
	protected int calcDist(Coord xy_f, Coord xy) {
		return (Math.abs(xy_f.getX() - xy.getX())+Math.abs(xy_f.getY() - xy.getY()));
	}
	
	public void MoveIndividual(int cost, Node_grid node) {
		if(!this.path.contains(node.getXy())) {
			this.cost +=cost;
			this.length++;
			this.node = node;
			
			this.dist = calcDist(this.xy_f, node.getXy());
			this.comfort_update(this.cost, this.length, this.N, this.M, this.dist, this.k, this.cmax);
			this.path.add(node.getXy());
		}else {
			//Eliminar ciclos do caminho aqui
		}
		
		return; //Adicionar return se dist == 0; ??
		
	}
	
	public void lenght_update () {

	}

	
    public void comfort_update (int cost, int lenght, int n, int m, int distance, int k, int cmax){
    	this.comfort=(((1-(cost-lenght+2)/((cmax-1)*lenght + 3))^k)*((1-(dist/(n+m+1)))^k));
    	
    }

	public float getComfort() {
		return comfort;
	}

	public int getCost() {
		return cost;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Node_grid getNode() {
		return node;
	}

	public void setNode(Node_grid node) {
		this.node = node;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alive ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(comfort);
		result = prime * result + cost;
		result = prime * result + dist;
		result = prime * result + length;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		Individual other = (Individual) obj;
		if (alive != other.alive)
			return false;
		if (Float.floatToIntBits(comfort) != Float.floatToIntBits(other.comfort))
			return false;
		if (cost != other.cost)
			return false;
		if (dist != other.dist)
			return false;
		if (length != other.length)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}   
	
	
	
}
