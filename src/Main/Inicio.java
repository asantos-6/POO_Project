package Main;

import java.io.*;
import Graph.*;
import Simulation.*;

public class Inicio {
		
		public static void main(String[] args) {
		
		File filename = new File (""+args[0]+"");
		Graph grid = null;
		Simulation simulation = new Simulation();
		
		
		
		simulation.XML_Parser(filename, grid);
		
		

	}
}
