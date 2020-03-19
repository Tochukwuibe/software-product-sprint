package com.google.sps;

import java.util.Set;
import java.util.Collection;

public class AvailableTimes {

    private Collection<Set<String>> times;
    private int duration;

    public AvailableTimes(int duration) {

        this.duration = duration;
        times = new ArrayList<Set<String>>;

        for(int i = TimeRange.START_OF_DAY; i < TimeRange.START_OF_DAY; i += duration ) {
            times.add(new HashSet());
        }

    }


    public void addAttendee(TimeRange range, String attendee) {
        
    }




    


}