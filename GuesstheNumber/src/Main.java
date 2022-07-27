
import java.util.Scanner;

public class Main {
    public static void main( String[] args) {
        int number = 1 + (int) (20.0 * Math.random());
        int J = 6;
        String str,playAgain = "y";
        Scanner sc = new Scanner(System.in);
        int guesses = 0;
        while (playAgain.equals("y") || playAgain.equals("Y")){
            System.out.println("Hello! What is your name?");
            String userName = sc.next();
            System.out.printf("Well, %s, I am thinking of a number between 1 and 20.", userName);
            System.out.println(" Guess the number within 6 trials.");
            guesses+=1;
            int i;
            for (i = 0; i < J; ++i) {
                System.out.println("Take a guess.");
                int guess = sc.nextInt();
                if (number == guess) {
                    System.out.println(" Good Job, " +userName + "! You guessed my number in " + guesses + " guesses!");
                    break;
                }

                if (number > guess && i != J - 1) {
                    System.out.println("Your guess is too low.");
                    guess++;
                } else if (number < guess && i != J - 1) {
                    System.out.println("Your guess is too high.");
                    guess++;
                }
            }

            if (i == J) {
                System.out.println("You have failed 6 times.");
                System.out.println("The number was " + number);
                }
            System.out.println(" Would you like to play again? (y or n)");
            playAgain = sc.next();

        }
    }
}