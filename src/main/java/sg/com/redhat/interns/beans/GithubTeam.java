package sg.com.redhat.interns.beans;



public class GithubTeam {

    private String Name;
    private int id;

    public GithubTeam(String Name, int id) {
        if (Name == null || id == 0) throw new NullPointerException("Null params");
        this.Name = Name;
        this.id = id;
    }

    public GithubTeam(){

    }

    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

}
