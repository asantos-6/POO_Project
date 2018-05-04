package Graph;

import Graph.Node;

public abstract class Graph {
	protected int M;//columns
	protected int N;//rows
	
	Node[][] nodes;
	
	public Graph(int M, int N){
		this.M = M;
		this.N = N;

		nodes = new Node[M][N];
		
		
		for (int y = 1; y <= M; y++) {
			for (int x = 1; x <= N; x++) {
			    Coord xy = new Coord(x, y);
			    nodes[y-1][x-1] = new Node(xy);		    		   
			}
		}
		set_neighbors(nodes, M, N);
	}
	
	protected void set_neighbors(Node[][] nodes, int M, int N) {
		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				Node n = nodes[y][x];
			    if (y > 0) {     // has south
			    	n.neighbors[0] = nodes[y-1][x];
			    }
			    if (y < M-1) { // has north
			    	n.neighbors[2] = nodes[y+1][x];
			    }
			    if (x > 0) {     // has west
			    	n.neighbors[3] = nodes[y][x-1];
			    }
			    if (x < N-1) { // has east
			    	n.neighbors[1] = nodes[y][x+1];
			    }
			}
		}
	}
	
}