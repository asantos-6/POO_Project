package State;
import java.util.Comparator;
import java.util.PriorityQueue;

import Individual.Individual;



public class State {
	int t;
	int tot_pop;
	boolean reached_final = false; 
	
	Comparator<Individual> c = new ComfortComparator();
	PriorityQueue<Individual> population = new PriorityQueue<Individual>(c);
	
	public State(int n_init_pop, PriorityQueue<Individual> init_pop){
		t=0;
		tot_pop=n_init_pop;
		population=init_pop;
	}
	
	public void addIndividual(Individual z) {
		population.add(z);
	}
	
	public Individual best_fit() {
		return population.peek();
	}

	public int getTot_pop() {
		return tot_pop;
	}

	public void setTot_pop(int tot_pop) {
		this.tot_pop = tot_pop;
	}	
	
	
}
