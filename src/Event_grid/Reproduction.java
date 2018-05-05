package Event_grid;

import java.util.PriorityQueue;

import Event.Event;
import Individual.Individual;
import PEC.PEC;
import PiorityQueueExt.PriorityQueueExt;
import Simulation.Simulation;

public class Reproduction extends Event {

	public Reproduction(int ro, PEC pec, int t, Individual z) {
		super(ro, pec, t, z);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Simulation sim = (Simulation) Simulation;
		Individual i = (Individual) this.z; 
		PriorityQueue<Individual> i_list = (PriorityQueueExt<Individual>) z_list;	
		
		
		
	}

}
