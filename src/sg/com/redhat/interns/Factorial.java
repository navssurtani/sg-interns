package sg.com.redhat.interns;

public class Factorial {

   public Factorial() {
   }

   public int calculate(int toCalculate) {
      if (toCalculate == 1) return 1;
      return toCalculate * calculate(toCalculate - 1);
   }
}
