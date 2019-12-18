package net.themaven.sportscontrols.model;

import java.util.ArrayList;

//a fixture is basically a match
public class Fixture {

    public Start start;
    public ArrayList<Team> teams;

    public class Start {
        public String utc;
    }
}
