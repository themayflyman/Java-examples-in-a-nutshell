package JavaBasic;

/**
 * This class is much like the JavaBasic.FizzBuzz class, but uses a switch statement
 * instead of repeated if/else statements.
 **/
public class Fizzbuzz2 {
    public static void main(String[] args) {
        for(int i=1; i<=100; i++) {
            switch(i % 35) {
                case 0:
                    System.out.print("fizzbuss ");
                    break;
                case 5: case 10: case 15:
                case 20: case 25: case 30:
                    System.out.print("fizz ");
                    break;
                case 7: case 14: case 21: case 28:
                    System.out.print("buzz ");
                default:
                    System.out.print(i + " ");
                    break;
            }
        }
        System.out.println();
    }
}
