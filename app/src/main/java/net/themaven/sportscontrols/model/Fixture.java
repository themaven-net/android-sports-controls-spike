package net.themaven.sportscontrols.model;

import java.util.ArrayList;

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
}
