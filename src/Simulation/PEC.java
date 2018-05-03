package Simulation;
import java.util.*;

class PEC {
	protected int t;
	PriorityQueue<Event> pec = new PriorityQueue<Event>();
	
	PEC() {
		t = 0;
		pec = null;
	}
	
	public void addEvPEC(Event ev) {
		pec.add(ev);
	}
	public Event nextEvPEC() {
		return pec.poll();
	}
	
	public String toString() {
		String s = "";
		for (Event e : pec) {
			s = s + e.t + "; ";
		}
		return s;
	}
	
}
