package Event_grid;

import Event.Event;

public class Death extends Event {

	private static int mew;
	
	public Death(int time, int mew) {
		super(time);
		Death.mew = mew; 
	}
	
	public static int getMew() {
		return mew;
	}

	@Override
	public void processEvent(Individual z, Individual[] z_list) {
		
		
	}

}
