/**
 * This class shows a recursive method to compute factorials. This method
 * calls itself repeatedly based on the formula: n! = n * (n-1)!
 */
public class Factorial2 {
    public static long factorial(long x) {
        if (x < 0) throw new IllegalArgumentException("x must be >= 0");
        if (x <= 1) return 1;               // Stop recursing here
        else return x * factorial(x-1);  // Recurse by calling ourselves
    }
}
