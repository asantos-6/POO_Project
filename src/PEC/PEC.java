package PEC;
import java.util.*;

import Event.Event;

public class PEC{
	protected int t;
	Comparator<Event> c = new EventComparator();
	PriorityQueue<Event> pec = new PriorityQueue<Event>(c);
	
	public PEC() {
		t = 0;
		pec = null;
	}
	
	public void addEvPEC(Event ev) {
		pec.add(ev);
		return;
	}
	public Event nextEvPEC() {
		return pec.poll();
	}
	
}
