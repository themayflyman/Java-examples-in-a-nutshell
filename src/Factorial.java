/**
 * This class doesn't define a main() method, so it isn't a program by itself.
 * It does define useful method that we can use in other programs, though.
 **/
public class Factorial {
    /* Compute and return x!, the factorial of x */
    public static int factorial(int x) {
        if(x < 0) throw new IllegalArgumentException("x must be >= 0");
        int fact = 1;
        for(int i=2; i<=x; i++) {
            fact *= i;
        }
        return fact;
    }
}
