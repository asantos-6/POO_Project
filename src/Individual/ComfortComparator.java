package Individual;
import java.util.*;;


public class ComfortComparator implements Comparator<Individual>{
    @Override
    public int compare(Individual x, Individual y){
        if (x.comfort < y.comfort)
        {
            return 1;
        }
        if (x.comfort > y.comfort)
        {
            return -1;
        }
        return 0;
    }
}