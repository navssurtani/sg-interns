package sg.com.redhat.interns;

/**
 * Created with IntelliJ IDEA.
 * User: winnie
 * Date: 7/10/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        for (int i=1; i<=10; i++) {
            Factorial f = new Factorial();
            int calculatedValue = f.calculate(i);
            System.out.println("The factorial for " + i + " is: " + calculatedValue);
        }

        Organization.team();
        System.out.println();

    }
}