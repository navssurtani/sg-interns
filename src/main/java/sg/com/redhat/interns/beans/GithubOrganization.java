package sg.com.redhat.interns.beans;

import java.util.HashSet;

/**
 * Basic bean
 */
public class GithubOrganization {

    private String orgName;
    private HashSet<GithubTeam> teams;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }


    public HashSet<GithubTeam> getTeams() {
        return teams;
    }

    public void setTeams(HashSet<GithubTeam> teams) {
        this.teams = teams;

    }
}
