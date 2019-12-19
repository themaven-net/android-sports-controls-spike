package net.themaven.sportscontrols.model;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Date;

public class TimeQuantum {

    public String title;
    public TimePeriod timePeriod;
    public ArrayList<StatsApi.DateElement> dateElements;
    boolean realized = false;

    public TimeQuantum(String title, TimePeriod timePeriod) {
        this.title = title;
        this.timePeriod = timePeriod;
    }

    public void realize(StatsApi statsApi){
        dateElements = statsApi.data.dates;
        realized = true;
    }

    public ArrayList<StatsApi.DateElement> daysWithMatches() {

        ArrayList<StatsApi.DateElement> daysWithMatches = new ArrayList<StatsApi.DateElement>();
        if (dateElements != null) {
            for (int i = 0; i < dateElements.size(); i++) {
                if (dateElements.get(i).fixtures.size() > 0) {
                    daysWithMatches.add(dateElements.get(i));
                }
            }
        }
        return daysWithMatches;
    }
}
