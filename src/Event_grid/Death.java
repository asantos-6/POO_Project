package Event_grid;

import java.util.PriorityQueue;

import Event.Event;
import Individual.Individual;
import PEC.PEC;

public class Death extends Event {
	
	public Death(PEC pec, int t, Individual z) {
		super(pec, t, z);
	}

	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Individual i = (Individual) this.z;
		
		Individual.setTot_pop(Individual.getTot_pop()-1);
		
		i.setAlive(false);
		return;
	}
	
	
	
}
