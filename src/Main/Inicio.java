package Main;

import java.io.*;
import Simulation.*;

public class Inicio {
		
		public static void main(String[] args) {
		
		File filename = new File (""+args[0]+"");
		Simulation simulation = new Simulation(filename);
		
		System.out.println("Graph Info in main: "+ simulation.getGrid());

	}
}
