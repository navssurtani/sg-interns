package sg.com.redhat.interns.beans;

import java.util.Set;

/**
 * Basic bean
 */
public class GithubOrganization {

    private String Name;
    private Set<GithubTeam> teams;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Set<GithubTeam> getTeams() {
        return teams;
    }

    public void setTeams(Set<GithubTeam> teams) {
        this.teams = teams;
    }

}
