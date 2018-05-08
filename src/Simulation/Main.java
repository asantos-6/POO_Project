package Simulation;
import java.io.File;
import java.util.*;

import Event.Event;
import Individual.Individual;


public class Main {
	public static void main(String[] args) {

            File filename = new File (""+args[0]+"");
    		Simulation simulation = new Simulation(filename);

    		//System.out.println("Graph Info in main: "+ simulation.getGrid());

    		//while((PriorityQueue<Event>) simulation.pec)
    		System.out.println("PEC: " + simulation.pec.toString());


	}


}
