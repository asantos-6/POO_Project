package Simulation;
import Simulation.Event;
import java.util.*;

public class Main {
	public static void main(String[] args) {

		Event ev1 = new Event(1);
		Event ev2 = new Event(2);
		Event ev3 = new Event(3);
		
		Comparator<Event> c = new EventComparator();
		
		PriorityQueue<Event> pec = new PriorityQueue<Event>(10, c);
		
		pec.add(ev1);
		pec.add(ev3);
		pec.add(ev2);
		
		System.out.println("" + pec);

	}

}
