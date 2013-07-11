package sg.com.redhat.interns.beans;

import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class GithubOrganization {

    private String Name;
    private String token;
    private List<GithubTeam> teams;


    public GithubOrganization(String Name) {
        this.Name = Name;
        this.teams = new ArrayList<GithubTeam>();
    }

    public String getName() {
        return Name;
    }


    public String getToken() {
        return token;
    }

    public void addTeam(GithubTeam t) {
        teams.add(t);
    }

    public List<GithubTeam> getTeams() {
        return teams;
    }



}

