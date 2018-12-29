package JavaBasic;

import java.io.*;

/**
 * A program uses JavaBasic.SortNumbers class to sort an array of 100 floating-point numbers. Then,
 * interactively prompt the user for numeric input and display the next larger and smaller
 * number from the array, using an efficient binary search algorithm to find the desired
 * position in the sorted way.
 */
public class SortFind {
    public static int binarySearch(double[] nums, double x) {
        int l = 0;
        int r = nums.length - 1;
        for(;;){
            if(l>r) {
                System.out.println("Can't sort");
                return -1;
            }
            int m = (l + r) / 2;
            if(nums[m] == x)
                return m;
            else if(nums[m] > x)
                r = m - 1;
            else if(nums[m] < x)
                l = m + 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(">Please input a number: ");
        double x = Double.parseDouble(in.readLine());

        // Generate an array of 100 floating-point numbers
        double[] nums = new double[101];
        for(int i=0; i<nums.length-1; i++) {
            nums[i] = Math.random() * 100;
        }
        nums[100] = x;
        // Sort the array
        SortNumbers.sort(nums);

        int m = binarySearch(nums, x);
        System.out.printf("The next number in array greater than %f is %f" +
                           "the next number in array less than %f is %f\n", x, nums[m+1], x, nums[m-1]);
        System.out.println();
    }
}
