package PEC;
import java.util.*;

import Event.Event;

public class PEC<E>{
	protected int t;
	Comparator<Event<E>> c;
	PriorityQueue<Event<E>> pec;
	static int n_events;
	
	public PEC() {
		t = 0;
		c = new EventComparator<E>();
		pec = new PriorityQueue<Event<E>>(c);
	}
	
	public void addEvPEC(Event<E> ev) {
		pec.add(ev);
		return;
	}
	public Event<E> nextEvPEC() {
		return pec.poll();
	}

	@Override
	public String toString() {
		return "PEC " + pec;
	}

	public PriorityQueue<Event<E>> getPec() {
		return pec;
	}

	public static void setN_events(int n_events) {
		PEC.n_events = n_events;
	}

	public static int getN_events() {
		return n_events;
	}

}
