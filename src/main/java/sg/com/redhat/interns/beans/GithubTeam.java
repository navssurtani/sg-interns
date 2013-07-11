package sg.com.redhat.interns.beans;

/**
 * Another bean
 */
public class GithubTeam {

    private String name;
    private int id;

    public GithubTeam(String name, int id){
        if (name == null || id == 0) throw new NullPointerException("Null parameters");
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
