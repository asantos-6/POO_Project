package Individual;
import java.util.*;;


public class CostComparator implements Comparator<Individual>{
    @Override
    public int compare(Individual x, Individual y){
        if (x.TotCost() < y.TotCost())
        {
            return -1;
        }
        if (x.TotCost() > y.TotCost())
        {
            return 1;
        }
        return 0;
    }
}