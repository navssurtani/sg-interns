package sg.com.redhat.interns;

/**
 * Created with IntelliJ IDEA.
 * User: winnie
 * Date: 7/10/13
 * Time: 5:42 PM
 * To change this template use File | Settings | File Templates.
 */

public class Factorial {

    public Factorial() {
    }

    public int calculate(int toCalculate) {
        if (toCalculate == 1) return 1;
        return toCalculate * calculate(toCalculate - 1);
    }
}