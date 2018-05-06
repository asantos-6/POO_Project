package Event_grid;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Event.Event;
import Graph.Coord;
import Individual.Individual;
import PEC.PEC;
import PiorityQueueExt.PriorityQueueExt;
import Simulation.Simulation;

public class Reproduction extends Event {

	public Reproduction(PEC pec, int t, Individual z) {
		super(pec, t, z);
	}

	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Simulation sim = (Simulation) Simulation;
		Individual father = (Individual) this.z; 
		@SuppressWarnings("unchecked")
		PriorityQueue<Individual> i_list = (PriorityQueueExt<Individual>) z_list;
		
		if(father.isAlive()) {
		
			new Reproduction(sim.getPec(), t, father);
			
			int length_child = (int) Math.ceil(0.9*father.getPath().size());

			ArrayList<Coord> child_path = (ArrayList<Coord>) father.getPath().subList(0, length_child);
			ArrayList<Integer> child_cost = (ArrayList<Integer>) father.getCost().subList(0, length_child);
			
			Individual child = new Individual(child_path, child_cost, sim.getGrid().getNodes(), sim.getPec(), this.t);
			i_list.add(child);
		}
		
		return;		
		
	}

}
