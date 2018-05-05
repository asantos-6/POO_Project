package Event_grid;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import Event.Event;
import Graph.Edge;
import Graph.Node;
import Graph_grid.Node_grid;
import Individual.Individual;
import PEC.PEC;
import Simulation.Simulation;

public class Move extends Event {
	
	public Move(int delta, PEC pec, int t, Individual z) {
		super(delta, pec, t, z);
	}
	
	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Simulation sim = (Simulation) Simulation;
		Individual i = (Individual) this.z; 
		
		Random random = new Random();
		
		int count=0;
		for (Node n : i.getNode().getNeighbors()) {
			if(n!=null)
				count++;
		}
		int r = random.nextInt(count-1);
		
		Node_grid node = (Node_grid) i.getNode().getNeighbors()[r];
		
		int cost = 0;
		Edge edge = new Edge(i.getNode().getXy(), node.getXy(), cost);
		List<Edge> edges = sim.getEdges();
		
		if(edges.contains(edge)) {
			for(Edge e : edges) {
				if(e.equals(edge)) {
					cost = e.getC();
					break;
				}			
			}
			
		}else {
			cost = 1;
		}
		
		i.MoveIndividual(cost, node);
		//new Move(delta, sim.getPec(), t, i);

		return;
	}

}
