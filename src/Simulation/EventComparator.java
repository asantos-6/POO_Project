package Simulation;

import java.util.Comparator;

public class EventComparator implements Comparator<Event>{
    @Override
    public int compare(Event x, Event y){
        if (x.t < y.t)
        {
            return -1;
        }
        if (x.t > y.t)
        {
            return 1;
        }
        return 0;
    }
}
