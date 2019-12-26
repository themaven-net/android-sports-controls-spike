package net.themaven.sportscontrols.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Team {

    public String name;
    public TeamLogo logo;
    public String abbreviation;
    public Record record;
    public ArrayList<Linescore> linescores;
    private Integer score;

    @SerializedName(value = "is_winner")
    public boolean isWinner; //key is is_winner
    public Location location;

    public class Location {
        public String name;
        public String type;
    }

    public class Record {
        public Integer wins;
        public Integer losses;
        public Integer ties;
        public String percentage;
    }

    public class TeamLogo {
        public String base;
        public Map<String, String> cuts;
    }

    public class Linescore {
        public Integer score;
        public Integer teamFouls;
        public Period period;
    }

    public class Period {
        Integer id;
        String name;
        String fullName;
        String unit;
    }

    public int getScore() {
        if (score == null) {
            return 0;
        } else {
            return score;
        }
    }

    public int getWins() {
        if (record == null || record.wins == null) {
            return 0;
        } else {
            return record.wins;
        }
    }

    public int getLosses() {
        if (record == null || record.losses == null) {
            return 0;
        } else {
            return record.losses;
        }
    }

    public String getDefaultImageURL() {
        return logo.base + logo.cuts.get("default");
    }

}
