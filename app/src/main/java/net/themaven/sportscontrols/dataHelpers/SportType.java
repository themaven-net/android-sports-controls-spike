package net.themaven.sportscontrols.dataHelpers;

import net.themaven.sportscontrols.model.TimeQuantum;
import java.util.ArrayList;

public enum SportType {
    NBA("nba"),
    NFL("nfl"),
    NHL("nhl");

    private String sportKey;

    SportType(String sportKey) {
        this.sportKey = sportKey;
    }

    public String getSportKey() {
        return sportKey;
    }

    public int numberOfMatchPeriods () {
        switch (this) {
            case NBA: return 4;
            case NFL: return 4;
            case NHL: return 3;
            default: return 0;
        }
    }

    public String longestTeamName() {
        switch (this) {
            case NBA:
            case NHL: return "GOLDEN KNIGHTS";
            case NFL: return "BUCCANEERS";
            default: return "";
        }
    }

    public ArrayList<TimeQuantum> getSchedule() {
        switch (this) {
            case NBA: return SportsScheduleBuilder.buildNBASchedule();
            case NHL: return SportsScheduleBuilder.buildNHLSchedule();
            case NFL: return SportsScheduleBuilder.buildNFLSchedule();
            default: return new ArrayList<>();
        }
    }
}
