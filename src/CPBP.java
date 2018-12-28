/**
 * A program that counts from 1 to 15, printing out each number, and then
 * counts backwards by twos to 1, again printing out each number
 */
public class CPBP {
    /**
     * Using loop
     */
    public static void cpbp1() {
        // Loop through 1 to 15 and print out
        for(int i=1; i<16; i++) {
            System.out.println(i + " ");
        }

        System.out.println();     // Dividing line

        // counts backwards by twos
        for(int j=15; j>=1; j--) {
            System.out.print(j + " ");
            j--;
            if(j != 0)
                System.out.println(j);
            else
                System.out.println();

        }
        System.out.println();      // Terminate the line
    }

    /**
     * Using array
     */
    public static void cpbp2() {
        int[] arr = new int[15];               // Initialize an array of 15
        for(int i=1; i<16; i++) arr[i-1] = i;  // Insert 1-15 into the array

        for(int x: arr)
            System.out.println(x + " ");

        System.out.println();

        for(int j=13; j>=-1; j-=2) {
            System.out.print(arr[j+1] + " ");
            try {System.out.println(arr[j]);}   // dealing with the exception of index of -1
            catch (ArrayIndexOutOfBoundsException e) {};
        }
        System.out.println();      // Terminate the line
    }

    public static void main(String[] args) {
        cpbp1();
        cpbp2();
    }
}
