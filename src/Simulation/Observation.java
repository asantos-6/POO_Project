package Simulation;

import Individual.Individual;
import PEC.PEC;
import Util.Column_printing;

public class Observation {
		private static int t=0;
		private static int events;
		private static int size;
		private static boolean final_point;
		private static String best_path;
		private static float cost_comfort;
		private static int n_obs = 0;
		
		static void update_observation() {
			Observation.t = Simulation.t;
			Observation.best_path = Individual.best_path();
			Observation.events = PEC.getN_events();
			Observation.final_point = Individual.isReached_final();
			Observation.size = Individual.getTot_pop();
			Observation.n_obs++;
			if(Observation.final_point)
				Observation.cost_comfort = Individual.best_fit().TotCost();
			else
				Observation.cost_comfort = Individual.best_fit().getComfort();
		return;
		}
		
		@Override
		public String toString() {
			Column_printing c = new Column_printing();
			c.addLine("Observation: " + n_obs + ":", "", "");
			
			c.addLine("", "Present instant:", ""+t);
			c.addLine("", "Number of realized events:", ""+events);
			c.addLine("", "Population size:", ""+size);
			if(final_point)
				c.addLine("", "Final point has been hit:", "yes");
			else
				c.addLine("", "Final point has been hit:", "no");
			c.addLine("", "Path of the best fit individual:", best_path);
			if(final_point)
				c.addLine("", "Cost:", ""+cost_comfort);
			else
				c.addLine("", "Comfort:", ""+cost_comfort);
			c.addLine("","","");
			
			
			return c.toString();
		}
		
		
		
}
