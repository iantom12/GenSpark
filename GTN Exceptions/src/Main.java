
import java.util.Random;
import java.util.Scanner;
//int number = 1 + (int) (20.0 * Math.random());
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Hello! What is your name?");
        String userName = null;
        userName = GetUserName(userName);
        PlayGame(userName);
        sc.close();
    }

    private static void PlayGame(String userName) {
        System.out.printf("Well, %s, I am thinking of a number between 1 and 20.", userName);
        System.out.println(" Guess the number within 6 trials.");
        int num = random.nextInt(19) + 1;
        int guess = 0, guesses = 0;
        while (guesses < 6) {
            System.out.println("Take a guess.");
            guess = GetUserGuess(guess);
            guesses++;
            if (guess > num) {
                System.out.println("Your guess is too high");

            } else if (guess < num) {
                System.out.println("your guess is too low");

            } else{
                System.out.printf("Nice job, %s! You guessed my number is %d guesses\n", userName, guesses);
                sc.nextLine();
                PlayAgain(userName);
                return;
            }
        }

        System.out.printf("You have failed 6 times %s. Your number was %d.", userName,num);
        sc.nextLine();
        PlayAgain(userName);
        return;
    }

    private static void PlayAgain(String userName){
        String inp = "";
        System.out.println(" Would you like to play again? y or n ?");
        inp = GetUserInp(inp);
        if (inp.length() > 1){
            System.out.println("It's just y or no.....");
            PlayAgain(userName);
            return;
        }
        if (inp.charAt(0) != 'y' && inp.charAt(0) != 'n'){
            System.out.println("Goodbye\n");
            PlayAgain(userName);
        }
        else if (inp.charAt(0) == 'y')
            PlayGame(userName);
        else if (inp.charAt(0) == 'n')
            return;
    }
    private static String GetUserName(String userName){
        try{
            userName = sc.nextLine();
        }
        catch(Exception str){
            System.out.println("Try again....\n");
            userName = GetUserName(userName);
        }
        return userName;
    }
    private static int GetUserGuess(int guess){
        try{
            guess = sc.nextInt();
        }
        catch (Exception noint){
            System.out.println("Not a number......\n" +
                    "Try Again...\n");
            sc.next();
            guess = GetUserGuess(guess);
        }
        return guess;
    }
    private static String GetUserInp(String inp){
        try{
            inp = sc.nextLine();
        }
        catch(Exception idk){
            System.out.println("Try again...\n");
            inp = GetUserInp(inp);
        }
        return inp;
    }
}
