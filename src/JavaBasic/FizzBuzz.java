package JavaBasic;

/**
 * This program plays the game "JavaBasic.FizzBuzz". It counts to 100, replacing each
 * multiple of 5 with the word "fizz", each multiple of 7 with the word "buzz",
 * and each multiple of both with the word "fizzbuzz". It uses the modulo
 * operator (%) to determine if a number is divisible by another.
 **/
public class FizzBuzz {
    public static void main(String[] args){
        for(int i = 1; i <= 100; i++) {
            if (((i % 5) == 0) && ((i % 7) == 0))
                System.out.println("fizzbuss");
            else if ((i % 5) == 0)
                System.out.println("fizz");
            else if ((i % 7) == 0)
                System.out.println("buzz");
            else System.out.println(i);
            System.out.println(" ");
        }
        System.out.println();
    }
}
