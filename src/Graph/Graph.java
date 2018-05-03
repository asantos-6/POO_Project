package Graph;
import Graph.Node;
import Graph.Edge;
import java.util.*;


public class Graph {
	int M;//columns
	int N;//rows
	int n_obs;
	int cmax;
	Node[][] nodes;
	List<Edge> cost_edges;
	
	public Graph(int M, int N, int cmax, int n_obs, Coord[] obs, List<Edge> edges){
		this.M = M;
		this.N = N;
		this.cmax = cmax;
		this.n_obs = n_obs;
		cost_edges = edges;
		nodes = new Node[M][N];
		
		
		for (int y = 1; y <= M; y++) {
			for (int x = 1; x <= N; x++) {
			    Coord xy = new Coord(x, y);
			    for(int o = 0; o<n_obs; o++) {
			    	if(obs[o].equals(xy))
			    		nodes[y-1][x-1] = new Node(xy, true);
			    	else
			    		nodes[y-1][x-1] = new Node(xy, false);
			    }			    
			}
		}
		set_neighbors(nodes, M, N);
	}
	
	public void set_neighbors(Node[][] nodes, int M, int N) {
		for (int y = 0; y < M; y++) {
			for (int x = 0; x < N; x++) {
				Node n = nodes[y][x];
			    if (y > 0) {     // has south
			    	if (!nodes[y-1][x].obs)
			    		n.neighbors[0] = nodes[y-1][x];
			    }
			    if (y < M-1) { // has north
			    	if (!nodes[y+1][x].obs)
			    		n.neighbors[2] = nodes[y+1][x];
			    }
			    if (x > 0) {     // has west
			    	if (!nodes[y][x-1].obs)
			    		n.neighbors[3] = nodes[y][x-1];
			    }
			    if (x < N-1) { // has east
			    	if (!nodes[y][x+1].obs)
			    		n.neighbors[1] = nodes[y][x+1];
			    }
			}
		}
	}
	
	public String toString() {
		int i = 1;
		String s = "";
		
		for (Edge e : cost_edges) {
			s = s +"edge "+ i + " : " + e + "\n";
			i++;
		}
		
		
		
		return "\nColumns: "+ M + "\nRows: "+ N +"\nMaximum cost of an edge: "+ cmax+"\nNumber of obstacles: "+ n_obs+"\n Special Edges: \n"+ s;
	}
	
	
}
