package Main;

import java.io.*;
import Graph.*;
import Simulation.*;

public class Inicio {
		
		public static void main(String[] args) {
		
		File filename = new File (""+args[0]+"");
		Graph grid = null;
		Simulation simulation = new Simulation();
		
		
		
		grid = simulation.XML_Parser(filename);
		
		System.out.println("Graph Info:  22222 "+ grid);

	}
}
