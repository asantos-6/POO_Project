package Event_grid;

import Event.Event;

public class Move extends Event {

	private static int delta;	
	
	public Move(int time, int delta) {
		super(time);
		Move.delta = delta;
	}
	
	public static int getDelta() {
		return delta;
	}

	
	@Override
	public void processEvent(Individual z, Individual[] z_list) {	
		int n = 0;
	}

}
