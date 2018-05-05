package Main;

import java.io.*;
import Graph.*;
import Simulation.*;
import Graph_grid.*;

public class Inicio {
		
		public static void main(String[] args) {
		
		File filename = new File (""+args[0]+"");
		Graph_grid grid = null;
		Simulation simulation = new Simulation();
		
		
		
		grid = simulation.XML_Parser(filename);
		
		System.out.println("Graph Info in main: "+ grid);

	}
}
