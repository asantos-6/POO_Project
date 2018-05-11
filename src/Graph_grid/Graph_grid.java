package Graph_grid;
import Graph.Coord;
import Graph.Edge;
import Graph.Graph;
import Graph_grid.Node_grid;

import java.util.*;


public class Graph_grid extends Graph{

	int n_obs;
	int cmax;
	
	Node_grid[][] nodes;
	

	List<Edge> cost_edges;
	
	
	public Graph_grid(int M, int N, int cmax, int n_obs, Coord[] obs, List<Edge> edges) {
		super(M, N);
		this.cmax = cmax;
		this.n_obs = n_obs;
		cost_edges = edges;
		
		nodes = new Node_grid[M][N];
		boolean obstacle;
		
		
		for (int y = 1; y <= M; y++) {
			for (int x = 1; x <= N; x++) {
			    Coord xy = new Coord(x, y);
			    obstacle = false;
			    for(int o = 0; o<n_obs; o++) {
			    	if(obs[o].equals(xy)) {
			    		obstacle = true;
			    		break;
			    	}			    	
			    } 
			    nodes[y-1][x-1] = new Node_grid(xy, obstacle);
			    			    
			}
		}
		set_neighbors(nodes, M, N);
	}
	
	public int getN_obs() {
		return n_obs;
	}

	public int getCmax() {
		return cmax;
	}

	public Node_grid[][] getNodes() {
		return nodes;
	}

	public List<Edge> getCost_edges() {
		return cost_edges;
	}

	protected void set_neighbors(Node_grid[][] nodes, int M, int N) {
		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				Node_grid n = nodes[y][x];
			    if (y > 0) {     // has south
			    	if (!nodes[y-1][x].obs)
			    		n.getNeighbors()[2] = nodes[y-1][x];
			    }
			    if (y < M-1) { // has north
			    	if (!nodes[y+1][x].obs)
			    		n.getNeighbors()[0] = nodes[y+1][x];
			    }
			    if (x > 0) {     // has west
			    	if (!nodes[y][x-1].obs)
			    		n.getNeighbors()[3] = nodes[y][x-1];
			    }
			    if (x < N-1) { // has east
			    	if (!nodes[y][x+1].obs)
			    		n.getNeighbors()[1] = nodes[y][x+1];
			    }
			}
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		
		for(int i = 0; i<M; i++) {
			for(int j = 0; j<N; j++) {
				s = s + this.nodes[i][j];
			}
		}
		
		return "\nColumns: "+ N + "\nRows: "+ M +"\n"+ s;
	}
	
	
	
	
}
