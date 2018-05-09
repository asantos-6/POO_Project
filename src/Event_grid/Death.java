package Event_grid;

import java.util.PriorityQueue;

import Event.Event;
import Individual.Individual;
import PEC.ExpDistrib;
import PEC.PEC;

public class Death extends Event {
	protected static int mew;
	
	public Death(PEC pec, int t, Individual z) {
		super(z);
		this.t = t +(int) Math.ceil(ExpDistrib.expRandom((double) mew));
		pec.addEvPEC(this);
		
	}

	@Override
	public <E> void processEvent(PriorityQueue<E> z_list, Object Simulation) {
		Individual i = (Individual) this.z;
		
		Individual.setTot_pop(Individual.getTot_pop()-1);
		
		i.setAlive(false);
		PEC.setN_events(PEC.getN_events()+1);
		return;
	}

	@Override
	public String toString() {
		return "Death [t=" + t + ", mew=" + mew + "]";
	}

	public static int getMew() {
		return mew;
	}

	public static void setMew(int mew) {
		Death.mew = mew;
	}
	
}
