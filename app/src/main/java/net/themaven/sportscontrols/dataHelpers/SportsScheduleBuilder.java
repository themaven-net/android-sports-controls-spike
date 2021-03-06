package net.themaven.sportscontrols.dataHelpers;

import net.themaven.sportscontrols.model.TimePeriod;
import net.themaven.sportscontrols.model.TimeQuantum;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SportsScheduleBuilder {

    public static ArrayList<TimeQuantum> buildNBASchedule() {

        Date start = new GregorianCalendar(2019, Calendar.OCTOBER, 22).getTime();
        Date end = new GregorianCalendar(2020, Calendar.APRIL, 15).getTime();

        return buildDailyQuanta(start, end);
    }

    public static ArrayList<TimeQuantum> buildNHLSchedule() {
        Date start = new GregorianCalendar(2019, Calendar.OCTOBER, 2).getTime();
        Date end = new GregorianCalendar(2020, Calendar.APRIL, 4).getTime();

        return buildDailyQuanta(start, end);
    }

    //NFL TimeQuantums are for entire weeks - hence the TimeQuantum has multiple days unlike
    //NHL and NBA
    public static ArrayList<TimeQuantum> buildNFLSchedule() {
        ArrayList<TimeQuantum> quanta = new ArrayList<>();
        NFLWeek[] weeks = NFLWeek.values();
        for (int i = 0; i < weeks.length; i++) {
            TimePeriod timePeriod = weeks[i].getDates();
            TimeQuantum quantum = new TimeQuantum(weeks[i].getWeekName(), timePeriod);
            quanta.add(quantum);
        }
        return quanta;
    }

    //create an array of TimeQuantum objects for each day in a given time period
    private static ArrayList<TimeQuantum> buildDailyQuanta(Date start, Date end) {
        ArrayList<TimeQuantum> quanta = new ArrayList<>();
        long difference = end.getTime() - start.getTime();
        long millisecondsInDay = (1000*60*60*24);
        long days = (difference / millisecondsInDay);
        Date beginningDate = start;
        for (int i = 0; i < days; i++) {

            //get start and end of quantum as date objects
            long dayStartLong = beginningDate.getTime() + (millisecondsInDay * i);
            //subtract one second from the milliseconds in a day to get the end of the day at 23:59
            long dayEndLong = dayStartLong + (millisecondsInDay - (1000 * 60));
            Date dayStartDate = new Date(dayStartLong);
            Date dayEndDate = new Date(dayEndLong);

            //create title for quantum using start date
            DateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateTitle = simpleDateFormat.format(dayStartDate);

            TimePeriod timePeriod = new TimePeriod(dayStartDate, dayEndDate);
            TimeQuantum timeQuantum = new TimeQuantum(dateTitle, timePeriod);
            quanta.add(timeQuantum);
        }
        return quanta;
    }

}
