package sg.com.redhat.interns;


public class Main {

   public static void main(String[] args) {

      for (int i=1; i<=10; i++) {
         Factorial f = new Factorial();
         int calculatedValue = f.calculate(i);
         System.out.println("The factorial for " + i + " is: " + calculatedValue);
      }

       Team.team();

   }
}
