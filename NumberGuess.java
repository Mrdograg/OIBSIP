import java.util.Scanner;
import java.util.Random;
public class NumberGuess {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random n = new Random();
        int number;
        number = n.nextInt(100);
        System.out.println(number); // you can delete this statement if want to hide the random number.
        int i;
        for(i=0;i<5;i++) {
            System.out.println(" Guess the number between 1-100");

            int a = input.nextInt();

            if (number == a) {
                System.out.println("Correct");
                break;
            } else if (number<a) {
                System.out.println("too high");

            } else {
                System.out.println("too low");
            }

        }
        System.out.println("Number of attempts made: "+i);
    }
}