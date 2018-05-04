package Event_grid;

import Event.Event;
import PEC.ExpDistrib;
import PEC.PEC;

public class Reproduction extends Event {

	private static int ro;
	
	
	
	public Reproduction(int ro, PEC pec) {
		super(ro, pec);
		Reproduction.ro = ro;
	}

	public static int getRo() {
		return ro;
	}

	@Override
	public void processEvent(Individual z, Individual[] z_list, State state) {		
		
	}

}
