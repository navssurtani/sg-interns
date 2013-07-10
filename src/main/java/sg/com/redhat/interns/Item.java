package sg.com.redhat.interns;

/**
 * Created with IntelliJ IDEA.
 * User: winnie
 * Date: 7/10/13
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
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
    public void setUnit(String teamName) {
        this.teamName = teamName;
    }
    public String getId() {
        return id;
    }
    public void setCurrent(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item [Organization Name=" + orgName + ", Token=" + token + ", Team Name="
                + teamName + ", id=" + id + "]";
    }
}
