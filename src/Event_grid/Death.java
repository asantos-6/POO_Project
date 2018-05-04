package Event_grid;

import Event.Event;
import PEC.PEC;
import PEC.ExpDistrib;

public class Death extends Event {

	private static int mew;
	
	
	
	public Death(int mew, PEC pec) {
		super(mew, pec);
		Death.mew = mew;
	}

	public static int getMew() {
		return mew;
	}

	@Override
	public void processEvent(Individual z, Individual[] z_list, State state) {
		/*Decrementar pop_size in State
		 * Mudar bool de live pra dead na lista
		 * return;
		 */
		
	}

}
