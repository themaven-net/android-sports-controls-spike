package net.themaven.sportscontrols.model;

import java.util.ArrayList;

//This is the format of the stats api response
public class StatsApi {

    public String status;
    public DataClass data;

    public class DataClass {
        public ArrayList<DateElement> dates;
    }

    public class DateElement {
        public DateDate date;
        public ArrayList<Fixture> fixtures;
    }

    public class DateDate {
        public Integer month;
        public Integer day;
        public Integer year;
        public Integer epoch;
        public String text;
    }
}
