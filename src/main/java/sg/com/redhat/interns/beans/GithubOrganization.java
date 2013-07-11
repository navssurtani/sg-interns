package sg.com.redhat.interns.beans;



import java.util.ArrayList;
import java.util.List;


/**
 * Basic bean
 */
public class GithubOrganization {


    private String name;
    private List<GithubTeam> teams;

    public GithubOrganization(String name) {
        this.name = name;
        this.teams = new ArrayList<GithubTeam>();
    }


    public void addTeam(GithubTeam t){

        teams.add(t);
    }

    public List<GithubTeam> getTeams() {
        return teams;
    }

    public String getName() {
        return name;
    }




}
