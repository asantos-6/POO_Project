package Simulation;
import java.io.File;

import Individual.Individual;


public class Main {
	public static void main(String[] args) {

            File filename = new File (""+args[0]+"");
    		Simulation simulation = new Simulation(filename);    
    		
    		simulation.simulate();
    		System.out.println("Path of the best individual = " + Individual.best_path());  					
	}


}
