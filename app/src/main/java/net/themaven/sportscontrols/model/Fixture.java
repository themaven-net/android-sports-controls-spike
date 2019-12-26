package net.themaven.sportscontrols.model;

import com.orhanobut.logger.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

//a fixture is basically a match
public class Fixture {

    public Start start;
    public ArrayList<Team> teams;

    public class Start {
        public String utc;
    }

    public Team getHomeTeam() {

        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).location.type.equals("home")) {
                return teams.get(i);
            }
        }

        if (teams.size() == 2) {
            return teams.get(0);
        }

        return null;
    }

    public Team getAwayTeam() {
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).location.type.equals("away")) {
                return teams.get(i);
            }
        }

        if (teams.size() == 2) {
            return teams.get(1);
        }

        return null;
    }

    public String getEasternTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        formatter.setTimeZone(TimeZone.getTimeZone("ETC"));

        try {
            Date date = formatter.parse(start.utc);
            DateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
            String dateTitle = simpleDateFormat.format(date);
            return dateTitle;
        } catch (ParseException error){
            Logger.e(error.getMessage());
            return "Error String";
        }
    }
}
