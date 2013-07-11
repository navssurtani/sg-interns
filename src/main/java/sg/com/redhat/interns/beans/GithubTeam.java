package sg.com.redhat.interns.beans;

import java.io.Serializable;
/**
 * Another bean
 */
public class GithubTeam implements Serializable {

    private String name;
    private int id;

    public GithubTeam(String name, int id){

        if(name == null || id == 0) throw new NullPointerException("Null params");
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;

    }

    public int getId() {
        return id;
    }



}
