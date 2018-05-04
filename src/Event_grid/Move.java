package Event_grid;

import Event.Event;
import PEC.ExpDistrib;
import PEC.PEC;

public class Move extends Event {

	private static int delta;	
	

	
	public Move(int delta, PEC pec) {
		super(delta, pec);
		Move.delta = delta;
	}


	public static int getDelta() {
		return delta;
	}

	
	@Override
	public void processEvent(Individual z, Individual[] z_list, State state) {
		
		
	}

}
