package net.themaven.sportscontrols.dataHelpers;

import net.themaven.sportscontrols.model.TimePeriod;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum NFLWeek
{
    WEEK_1("Week 1"),
    WEEK_2("Week 2"),
    WEEK_3("Week 3"),
    WEEK_4("Week 4"),
    WEEK_5("Week 5"),
    WEEK_6("Week 6"),
    WEEK_7("Week 7"),
    WEEK_8("Week 8"),
    WEEK_9("Week 9"),
    WEEK_10("Week 10"),
    WEEK_11("Week 11"),
    WEEK_12("Week 12"),
    WEEK_13("Week 13"),
    WEEK_14("Week 14"),
    WEEK_15("Week 15"),
    WEEK_16("Week 16"),
    WEEK_17("Week 17"),
    WILDCARD("Wild-Card Rounds"),
    DIVISIONAL("Divisional Rounds"),
    CONFERENCE("Conference Championships"),
    SUPERBOWL("Super Bowl");

    private String weekName;

    NFLWeek(String weekName) {
        this.weekName = weekName;
    }

    public String getWeekName() {
        return weekName;
    }

    public TimePeriod getDates() {

        Date start = new Date();
        Date end = new Date();

        switch (this) {
            case WEEK_1:
                start = getDate(2019, Calendar.SEPTEMBER, 5);
                end = getDate(2019, Calendar.SEPTEMBER, 9);
                break;
            case WEEK_2:
                start = getDate(2019, Calendar.SEPTEMBER, 12);
                end = getDate(2019, Calendar.SEPTEMBER, 16);
                break;
            case WEEK_3:
                start = getDate(2019, Calendar.SEPTEMBER, 19);
                end = getDate(2019, Calendar.SEPTEMBER, 23);
                break;
            case WEEK_4:
                start = getDate(2019, Calendar.SEPTEMBER, 26);
                end = getDate(2019, Calendar.SEPTEMBER, 30);
                break;
            case WEEK_5:
                start = getDate(2019, Calendar.OCTOBER, 3);
                end = getDate(2019, Calendar.OCTOBER, 7);
                break;
            case WEEK_6:
                start = getDate(2019, Calendar.OCTOBER, 10);
                end = getDate(2019, Calendar.OCTOBER, 14);
                break;
            case WEEK_7:
                start = getDate(2019, Calendar.OCTOBER, 17);
                end = getDate(2019, Calendar.OCTOBER, 21);
                break;
            case WEEK_8:
                start = getDate(2019, Calendar.OCTOBER, 24);
                end = getDate(2019, Calendar.OCTOBER, 28);
                break;
            case WEEK_9:
                start = getDate(2019, Calendar.OCTOBER, 31);
                end = getDate(2019, Calendar.NOVEMBER, 4);
                break;
            case WEEK_10:
                start = getDate(2019, Calendar.NOVEMBER, 7);
                end = getDate(2019, Calendar.NOVEMBER, 11);
                break;
            case WEEK_11:
                start = getDate(2019, Calendar.NOVEMBER, 14);
                end = getDate(2019, Calendar.NOVEMBER, 18);
                break;
            case WEEK_12:
                start = getDate(2019, Calendar.NOVEMBER, 21);
                end = getDate(2019, Calendar.NOVEMBER, 25);
                break;
            case WEEK_13:
                start = getDate(2019, Calendar.NOVEMBER, 28);
                end = getDate(2019, Calendar.DECEMBER, 2);
                break;
            case WEEK_14:
                start = getDate(2019, Calendar.DECEMBER, 5);
                end = getDate(2019, Calendar.DECEMBER, 9);
                break;
            case WEEK_15:
                start = getDate(2019, Calendar.DECEMBER, 12);
                end = getDate(2019, Calendar.DECEMBER, 16);
                break;
            case WEEK_16:
                start = getDate(2019, Calendar.DECEMBER, 21);
                end = getDate(2019, Calendar.DECEMBER, 23);
                break;
            case WEEK_17:
                start = getDate(2019, Calendar.DECEMBER, 29);
                end = getDate(2019, Calendar.DECEMBER, 29);
                break;
            case WILDCARD:
                start = getDate(2020, Calendar.JANUARY, 4);
                end = getDate(2020, Calendar.JANUARY, 5);
                break;
            case DIVISIONAL:
                start = getDate(2020, Calendar.JANUARY, 11);
                end = getDate(2020, Calendar.JANUARY, 12);
                break;
            case CONFERENCE:
                start = getDate(2020, Calendar.JANUARY, 19);
                end = getDate(2020, Calendar.JANUARY, 19);
                break;
            case SUPERBOWL:
                start = getDate(2020, Calendar.FEBRUARY, 2);
                end = getDate(2020, Calendar.FEBRUARY, 2);
                break;
        }

        TimePeriod timePeriod = new TimePeriod(start, end);
        return timePeriod;
    }

    public Date getDate(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        return date;
    }

}
