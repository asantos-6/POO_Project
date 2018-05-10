package PEC;
import java.util.*;

import Event.Event;


public class EventComparator<E> implements Comparator<Event<E>>{
    @Override
    public int compare(Event<E> x, Event<E> y){

        if (x.getT() < y.getT()){
            return -1;
        }
        if (x.getT() > y.getT()){
            return 1;
        }
        return 0;
    }
}