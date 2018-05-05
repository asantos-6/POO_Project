package Event_grid;

import java.util.PriorityQueue;

import Event.Event;
import Individual.Individual;
import PEC.PEC;
import Simulation.Simulation;

public class Death extends Event {
	
	public Death(int mew, PEC pec, int t, Individual z) {
		super(mew, pec, t, z);
	}

	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Simulation sim = (Simulation) Simulation;
		Individual i = (Individual) this.z;
		
		sim.getState().setTot_pop(sim.getState().getTot_pop()-1);
		
		i.setAlive(false);
		return;
	}
	
	
}
