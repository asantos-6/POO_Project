package State;
import java.util.*;

import Individual.Individual;;


public class CostComparator implements Comparator<Individual>{
    @Override
    public int compare(Individual x, Individual y){
        if (x.getCost() < y.getCost())
        {
            return 1;
        }
        if (x.getCost() > y.getCost())
        {
            return -1;
        }
        return 0;
    }
}