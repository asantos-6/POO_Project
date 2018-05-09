package PEC;
import java.util.*;

import Event.Event;

public class PEC{
	protected int t;
	Comparator<Event> c;
	PriorityQueue<Event> pec;
	static int n_events;
	
	public PEC() {
		t = 0;
		c = new EventComparator();
		pec = new PriorityQueue<Event>(30, c);
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
		return "PEC " + pec;
	}

	public PriorityQueue<Event> getPec() {
		return pec;
	}

	public static void setN_events(int n_events) {
		PEC.n_events = n_events;
	}

	public static int getN_events() {
		return n_events;
	}

}
