package Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import Graph.Coord;
import Graph_grid.Node_grid;
import PEC.PEC;
import Util.*;
import Event_grid.*;


public class Individual {

	List<Integer> cost = new ArrayList<Integer>();
	int length;
	int dist;
	boolean alive = true;
	boolean finalist = false;
	float comfort;
	List<Coord> path = new ArrayList<Coord>();
	Node_grid node;
	
	static Coord xy_f;
	static int cmax, k, N, M;
	
	static int tot_pop=0;
	static boolean reached_final = false; 
	static Comparator<Individual> comfort_comparator = new ComfortComparator();
	static PriorityQueue<Individual> population = new PriorityQueue<Individual>(comfort_comparator);
	static Comparator<Individual> cost_comparator = new CostComparator();
	static PriorityQueue<Individual> finalists = new PriorityQueue<Individual>(cost_comparator);	
	
	//Init individuals
	public Individual(Coord xy, Node_grid[][] graph, PEC<Individual> pec, int t) {
		cost.add(0);
		path.add(xy);		
		length=0;
		node = graph[xy.getY()-1][xy.getX()-1];
		dist = calcDist();
		comfort = this.comfort_update();
		
		tot_pop++;		
		population.add(this);
				
		
		new Death(pec, t, this);
		new Move(pec, t, this);
		new Reproduction(pec, t, this);
		
	}
	
	//finalists
	public Individual(List<Integer> cost, int length, boolean alive, float comfort, List<Coord> path, Node_grid node) {

		finalist = true;
		
		this.cost = cost;
		this.length = length;		
		this.alive = alive;
		this.comfort = comfort;
		this.path = path;
		this.node = node;
		dist = this.calcDist();
		
	}
	
	//childs
	public Individual(List<Coord> child_path, List<Integer> child_cost, Node_grid[][] graph, PEC<Individual> pec, int t) {
		cost = child_cost; 
		path = child_path;
		length = path.size()-1;
		Coord xy = path.get(path.size()-1);
		node = graph[xy.getY()-1][xy.getX()-1];
		dist = calcDist();
		comfort = this.comfort_update();
		/*
		if(dist == 0) {
			finalist = true;
			finalists.add(this);
		}
		*/
			
		
		tot_pop++;
		population.add(this);
		
		new Death(pec, t, this);
		new Move(pec, t, this);
		new Reproduction(pec, t, this);
		
	}
	
	protected int calcDist() {
		
		return Math.abs(Individual.xy_f.getX() - node.getXy().getX())+Math.abs(Individual.xy_f.getY() - node.getXy().getY());
	}
	public void MoveIndividual(int cost, Node_grid node) {
		this.node = node;
		
		dist = calcDist();
		
		if(!path.contains(node.getXy())) {
			this.cost.add(this.TotCost() + cost);
			length++;											
			path.add(node.getXy());
			
			comfort = this.comfort_update();
			
			if(dist == 0) {
				finalist = true;
				Individual.reached_final = true;
				DeepCopy_Coord dc_c = new DeepCopy_Coord();
				List<Coord> new_path= dc_c.DeepCopylist(0, this.path.size(), this.path);
				DeepCopy_Integer dc_i = new DeepCopy_Integer();
				List<Integer> new_cost= dc_i.DeepCopylist(0, this.cost.size(), this.cost);
				Individual i = new Individual(new_cost, this.length, this.alive, this.comfort, new_path, this.node);
				finalists.add(i);
			}
			
		}else {
			int i = path.indexOf(node.getXy());
			
			DeepCopy_Coord dc_c = new DeepCopy_Coord();
			path = dc_c.DeepCopylist(0, i+1, path);
			DeepCopy_Integer dc_i = new DeepCopy_Integer();
			this.cost = dc_i.DeepCopylist(0, i+1, this.cost);
			
			length = path.size()-1;
			comfort = this.comfort_update();
		}
		
		return;
		
	}
	
    public float comfort_update (){
    	return (float) (Math.pow(1.0-(this.TotCost()-length+2.0)/((cmax-1.0)*length+3.0), k)*Math.pow(1.0-(dist)/(N+M+1.0), k));
    }
    
    public static Individual best_fit() {
    	if(reached_final)
    		return finalists.peek();
		return population.peek();
	}

	public float getComfort() {
		return comfort;
	}

	public List<Integer> getCost() {
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

	public int getLength() {
		return length;
	}

	public static void setTot_pop(int tot_pop) {
		Individual.tot_pop = tot_pop;
	}	
	
	public static PriorityQueue<Individual> getPopulation() {
		return population;
	}
	
	
	public static void setPopulation(PriorityQueue<Individual> population) {
		Individual.population = population;
	}

	public static boolean isReached_final() {
		return reached_final;
	}

	public boolean isFinalist() {
		return finalist;
	}

	public static String print_path(List<Coord> path) {
		String s = "";
		for(Coord c : path) {
			if(path.indexOf(c) == path.size()-1)
				s = s + c.toString();
			else	
				s = s + c.toString() + ",";
		}
		return "{" + s + "}";
	}
	public static String best_path() {
		String s = print_path(best_fit().path);
		return "" + s;
	}
	
	@Override
	public String toString() {
		String s = "";
		
		s = s + "Cost: " + this.TotCost() + ";\n";
		s = s + "Length: " + this.length + ";\n";
		s = s + "Dist: " + this.dist + ";\n";
		s = s + "Alive: " + this.alive + ";\n";
		s = s + "Cost: " + this.TotCost() + ";\n";
		s = s + "Comfort: " + this.comfort + ";\n";
		s = s + "Path: " + print_path(this.path) + ";\n";
		s = s + "\n";
		
		s = s + "Goal: " + Individual.xy_f+"\n";
		s = s + "\n";
		return s;
		
	}
}
