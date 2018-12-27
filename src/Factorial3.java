public class Factorial3 {
    private static long[] table = new long[21];
    private static int last = 0;

    public static long factorial(int x) throws IllegalArgumentException {
        if (x >= table.length)
            throw new IllegalArgumentException("Overflow; x is too large.");
        if (x <= 0) throw new IllegalArgumentException("x must be non-negative.");

        while(last < x) {
            table[last + 1] = table[last] * (last + 1);
            last++;
        }
        return table[x];
    }
}
