package Event_grid;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import Event.Event;
import Graph.Edge;
import Graph_grid.Node_grid;
import Individual.Individual;
import PEC.PEC;
import Simulation.Simulation;

public class Move extends Event {
	
	public Move(PEC pec, int t, Individual z) {
		super(pec, t, z);
	}
	
	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {		
		Simulation sim = (Simulation) Simulation;
		Individual i = (Individual) this.z; 
		
		if(i.isAlive()) {
		
			Random random = new Random();
			
			int r = random.nextInt(3);
			Node_grid node = (Node_grid) i.getNode().getNeighbors()[r];
			
			while(node == null) {
				r = random.nextInt(3);
				node = (Node_grid) i.getNode().getNeighbors()[r];
			}
			
			int cost = 0;
			Edge edge = new Edge(i.getNode().getXy(), node.getXy(), cost);
			List<Edge> edges = sim.getEdges();
			
			if(edges.contains(edge)) {
				cost = edges.get(edges.indexOf(edge)).getC();
				
			}else {
				cost = 1;
			}
			
			i.MoveIndividual(cost, node);
			new Move(sim.getPec(), t, i);
		}
		return;
	}

}
