package PEC;
import java.util.*;

import Event.Event;

public class PEC<E>{
	protected int t;
	protected Comparator<Event<E>> c;
	protected PriorityQueue<Event<E>> pec;
	protected static int n_events;
	
	public PEC() {
		t = 0;
		c = new EventComparator<E>();
		pec = new PriorityQueue<Event<E>>(c);
	}
	
	public void addEvPEC(Event<E> ev) {
		pec.add(ev);
		return;
	}
	
	public void removeEvPEC() {
		pec.remove();
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
	
	public int getT() {
		if(!pec.isEmpty())
			return pec.peek().getT();
		return -1;
	}

}
