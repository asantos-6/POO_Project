package Event_grid;

import java.util.List;
import java.util.PriorityQueue;

import Event.Event;
import Graph.Coord;
import Individual.Individual;
import PEC.ExpDistrib;
import PEC.PEC;
import Util.*;
import Simulation.Simulation;

public class Reproduction extends Event<Individual> {
	protected static int ro;

	public Reproduction(PEC<Individual> pec, int t, Individual z) {
		super(z);
		this.t = t+1 +(int) Math.ceil(ExpDistrib.expRandom((double) (1-Math.log(z.getComfort()))*ro));
		pec.addEvPEC(this);
	}

	@Override
	public void processEvent(PriorityQueue<Individual> z_list, Object Simulation) {		
		Simulation sim = (Simulation) Simulation;
		Individual father = (Individual) this.z; 
		PriorityQueue<Individual> i_list = z_list;
		
		if(father.isAlive()) {
			new Reproduction(sim.getPec(), t, father);
			
			int length_child = (int) Math.ceil(0.9*father.getPath().size() + father.getComfort());

			DeepCopy_Coord dc_c = new DeepCopy_Coord();
			List<Coord> child_path = dc_c.DeepCopylist(0, length_child, father.getPath());	
			DeepCopy_Integer dc_i = new DeepCopy_Integer();
			List<Integer> child_cost = dc_i.DeepCopylist(0, length_child, father.getCost());
			
			Individual child = new Individual(child_path, child_cost, sim.getGrid().getNodes(), sim.getPec(), this.t);			
			
			i_list.add(child);
			
			PEC.setN_events(PEC.getN_events()+1);
		}
		
		return;		
		
	}

	public static int getRo() {
		return ro;
	}

	public static void setRo(int ro) {
		Reproduction.ro = ro;
	}

	@Override
	public String toString() {
		return "Reproduction [t=" + t + ", ro=" + ro + "]";
	}
	
}
