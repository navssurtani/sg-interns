package sg.com.redhat.interns.stax;
import java.util.List;
import sg.com.redhat.interns.stax.Item;
import sg.com.redhat.interns.stax.StaxParser;

public class StaxMain {
    public static void main(String args[]) {
        StaxParser read = new StaxParser();
        List<Item> readConfig = read.readConfig("/home/gerald/code/sg-interns/src/sg-interns/resources/github-team-data.xml");
        for (Item item : readConfig) {
            System.out.println(item);
        }
    }
}
