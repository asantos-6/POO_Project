package Event_grid;

import java.util.List;
import java.util.PriorityQueue;

import Event.Event;
import Graph.Coord;
import Individual.Individual;
import PEC.PEC;
import Util.*;
import Simulation.Simulation;

public class Reproduction extends Event {

	public Reproduction(PEC pec, int t, Individual z) {
		super(pec, t, z);
	}

	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Simulation sim = (Simulation) Simulation;
		Individual father = (Individual) this.z; 
		PriorityQueueExt<?> i_list = (PriorityQueueExt<?>) z_list;
		
		if(father.isAlive()) {
		
			new Reproduction(sim.getPec(), t, father);
			
			int length_child = (int) Math.ceil(0.9*father.getPath().size() + father.getComfort());

			List<Coord> child_path = DeepCopy.DeepCopylist_Coord(0, length_child, father.getPath());
			List<Integer> child_cost = DeepCopy.DeepCopylist_Integer(0, length_child, father.getCost());
			
			Individual child = new Individual(child_path, child_cost, sim.getGrid().getNodes(), sim.getPec(), this.t);			
			
			i_list.add(child);
		}
		
		return;		
		
	}

}
