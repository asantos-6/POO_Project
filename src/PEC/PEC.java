package PEC;
import java.util.*;

import Event.Event;

public class PEC{
	protected int t;
	Comparator<Event> c = new EventComparator();
	PriorityQueue<Event> pec = new PriorityQueue<Event>(10, c);
	
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
		int i = 1;
		while (pec.size() != 0){
            s = s+ "Event_" + i + ":" + pec.remove();
        }
		return s;
	}
	
}
