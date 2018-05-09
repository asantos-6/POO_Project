package PEC;
import java.util.*;

import Event.Event;


public class EventComparator implements Comparator<Event>{
    @Override
    public int compare(Event x, Event y){

        if (x.getT() < y.getT()){
            return -1;
        }
        if (x.getT() > y.getT()){
            return 1;
        }
        return 0;
    }
}