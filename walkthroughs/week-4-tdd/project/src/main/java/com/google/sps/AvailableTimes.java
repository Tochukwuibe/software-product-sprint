package com.google.sps;

import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;

public class AvailableTimes {

    /* 
        Array that would contain all of the possible time slots 
        in a day in slices of the duration.
    */
    private boolean[] avaliable_times;
    private int duration; // the duration of each time slot in the available_times array

    public AvailableTimes(int duration) {

        this.duration = duration;
        avaliable_times = new boolean[TimeRange.END_OF_DAY / duration];

        for(int i = 0; i <  avaliable_times.length; i++ ) {
            avaliable_times[i] = true;
        }

    }

    /*
        Takes in a time range and uses its 
        start and end to set the curesponding 
        time slots in the available_times array to false
    */
    public void setUnavailable(TimeRange range) {
    
        int startIndex = range.start() / duration;
        int endIndex = range.end() / duration;
   
        while(startIndex < endIndex - 1) {
            avaliable_times[startIndex++] = false;
        }

    }

    /*
        get the available times based on the 
        slots that have true in the available_times 
        array
    */
    public Collection<TimeRange> getAvailableTimes() {
        ArrayList<TimeRange> times = new ArrayList<>();
        int i = 0; 

        while(i < avaliable_times.length) {

                if(avaliable_times[i]) { // once we find an available time
                    int start = i;
        
                    while(i < avaliable_times.length && avaliable_times[i]) { // incement i until the next unavaialble time
                        i++;
                    }

                    /* 
                        add the entire available time range from the start to end,
                        end is calculated as the distance from the current value of i 
                        to the starting poing

                    */
                    times.add(TimeRange.fromStartDuration(start * duration, (i - start)  * duration));
               
                                        
                }
            
            i++;
        }

       

        return times;
    }




    


}