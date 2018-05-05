package State;
import java.util.List;

import Individual.Individual;



public class State {
	int t;
	int tot_pop;
	
	Individual[] population;
	
	public State(int n_init_pop, Individual[] init_pop){
		t=0;
		tot_pop=n_init_pop;
		population=init_pop;
	}
	
	
	
}
