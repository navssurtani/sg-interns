package sg.com.redhat.interns.stax;

public class Item {
    private String orgName;
    private String token;
    private String teamName;
    private String id;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "\n Org Name=" + orgName + "\n Token=" + token + "\n Team Name="
                + teamName + "\n ID=" + id + "";
    }
}
