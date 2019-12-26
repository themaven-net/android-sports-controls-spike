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

    public ArrayList<TimeQuantum> getSchedule() {
        switch (this) {
            case NBA: return SportsScheduleBuilder.buildNBASchedule();
            case NHL: return SportsScheduleBuilder.buildNHLSchedule();
            case NFL: return SportsScheduleBuilder.buildNFLSchedule();
            default: return new ArrayList<>();
        }
    }
}
