package Individual;

import java.util.List;
import Graph.*;


public class Individual {
	int cost;
	int lenght;
	int dist;
	boolean alive = true;
	float comfort;
	List<Coord> path;
	
	public Individual(Simulation sim ) {
		cost=0;
		lenght=0;
		comfort=0;
		path= null;
		final int dist = ((sim.xy_f.x - sim.xy_i.x)+(sim.xy_f.y - sim.xy_i.y));
	}
	
	public void lenght_update () {
		lenght= path.size();
	}

	
    public void comfort_update (int cost, int lenght, int n, int m, int distance, int k, int cmax ){
    	comfort=(  		((  1-   (cost-lenght+2)/((cmax-1)*lenght + 3)   )^k) 		 * 		(( 1- (dist/(n+m+1)))^k)    	);
    
    	
    }
    
	
		
}
