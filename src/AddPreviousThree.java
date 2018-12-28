/**
 * This program prints out the first 20 numbers of a series.
 * Each term of this series is formed by adding the previous three terms,
 * starting with 1, 1, and 1.
 */
public class AddPreviousThree {
    public static void main(String[] args) {
        int n0 = 1, n1 = 1, n2 = 1, n3;
        System.out.print(n0 + " " + n1 + " " + n2 + " ");

        for(int i=0; i<20; i++) {
            n3 = n0 + n1 + n2;
            System.out.print(n3 + " ");

            n0 = n1;
            n1 = n2;
            n2 = n3;
        }
        System.out.println();
    }
}
