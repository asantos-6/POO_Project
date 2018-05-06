package Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Graph.Coord;
import Graph_grid.Node_grid;
import PEC.PEC;
import PiorityQueueExt.PriorityQueueExt;
import Event_grid.*;


public class Individual {

	ArrayList<Integer> cost = new ArrayList<Integer>();
	int length;
	int dist;
	boolean alive = true;
	float comfort;
	ArrayList<Coord> path = new ArrayList<Coord>();
	Node_grid node;
	
	static Coord xy_f;
	static int cmax, k, N, M;
	
	static int tot_pop=0;
	static boolean reached_final = false; 
	static Comparator<Individual> c = new ComfortComparator();
	static PriorityQueueExt<Individual> population = new PriorityQueueExt<Individual>(c);
	
	public Individual(Coord xy, Node_grid[][] graph,PEC pec, int t) {
		cost.add(0);
		path.add(xy);		
		length=0;
		node = graph[xy.getX()][xy.getY()];
		dist = calcDist();
		comfort=0;
		
		tot_pop++;
		population.add(this);
				
		
		new Death(pec, t, this);
		new Move(pec, t, this);
		new Reproduction(pec, t, this);
		
	}
	
	public Individual(ArrayList<Coord> child_path, ArrayList<Integer> child_cost, Node_grid[][] graph, PEC pec, int t) {
		cost = child_cost; 
		path = child_path;
		length = path.size()-1;
		Coord xy = path.get(path.size()-1);
		node = graph[xy.getX()][xy.getY()];
		dist = calcDist();
		comfort = this.comfort_update();
		
		tot_pop++;
		population.add(this);
		
		new Death(pec, t, this);
		new Move(pec, t, this);
		new Reproduction(pec, t, this);
		
	}
	
	protected int calcDist() {
		return (Math.abs(Individual.xy_f.getX() - node.getXy().getX())+Math.abs(Individual.xy_f.getY() - node.getXy().getY()));
	}
	public void MoveIndividual(int cost, Node_grid node) {
		this.node = node;
		dist = calcDist();
		
		if(!path.contains(node.getXy())) {
			this.cost.add(this.TotCost() + cost);
			length++;											
			path.add(node.getXy());
			this.comfort_update();
			
			if(!reached_final) {
				if(dist == 0) {
					Individual.reached_final = true;
					c = new CostComparator();
					population.ChangeComparator(c);
				}
			}
		}else {
			int i = path.indexOf(node.getXy());
			
			path.subList(0, i+1);
			this.cost.subList(0, i+1);
			
			length = path.size()-1;
			this.comfort_update();
		}
		
		return;
		
	}
	
    public float comfort_update (){
    	return (((1-(this.TotCost()-length+2)/((cmax-1)*length + 3))^k)*((1-(dist/(N+M+1)))^k));    	
    }
    
    public Individual best_fit() {
		return population.peek();
	}

	public float getComfort() {
		return comfort;
	}

	public ArrayList<Integer> getCost() {
		return cost;
	}

	public int TotCost() {
		return this.cost.get(this.cost.size()-1);
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

	public List<Coord> getPath() {
		return path;
	}

	public void setNode(Node_grid node) {
		this.node = node;
	}
	
	public static void setXy_f(Coord xy_f) {
		Individual.xy_f = xy_f;
	}

	public static void setCmax(int cmax) {
		Individual.cmax = cmax;
	}

	public static void setK(int k) {
		Individual.k = k;
	}

	public static void setN(int n) {
		Individual.N = n;
	}

	public static void setM(int m) {
		Individual.M = m;
	}

	public static int getTot_pop() {
		return tot_pop;
	}

	public static void setTot_pop(int tot_pop) {
		Individual.tot_pop = tot_pop;
	}	
	
}
