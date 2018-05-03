package Event;

public abstract class Event {
	public int t;
	
	public Event(int time){
		t = time;
	}

	public abstract void processEvent(Individual z, Individual[] z_list);
	
	
}
