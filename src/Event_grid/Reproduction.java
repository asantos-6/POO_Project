package Event_grid;

import Event.Event;

public class Reproduction extends Event {

	private static int ro;
	
	public Reproduction(int time, int ro) {
		super(time);
		Reproduction.ro = ro;
	}

	public static int getRo() {
		return ro;
	}

	@Override
	public void processEvent(Individual z, Individual[] z_list) {		
	}

}
