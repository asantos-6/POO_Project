package PEC;
import java.util.*;

import Event.Event;

public class PEC{
	protected int t;
	Comparator<Event> c;
	PriorityQueue<Event> pec;
	
	public PEC() {
		t = 0;
		c = new EventComparator();
		pec = new PriorityQueue<Event>(c);;
	}
	
	public void addEvPEC(Event ev) {
		pec.add(ev);
		return;
	}
	public Event nextEvPEC() {
		return pec.poll();
	}

	@Override
	public String toString() {
		return "PEC [pec=" + pec + "]";
	}
}
