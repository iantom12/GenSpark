
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;
//int number = 1 + (int) (20.0 * Math.random());
public class Main {
    private static final Random num = new Random();
    private Scanner sc;
    private int guesses;
    private String userName;
    private boolean pwin;
    private char inp;
    private int guess;
    private int wnum;

    public void PlayGame() {
        setGuesses(0);
        generateWnum();
        System.out.printf("Well, %s, I am thinking of a number between 1 and 20.", userName);
        System.out.println(" Guess the number within 6 trials.");
        while (guesses < 6 && !pwin) {
            getGuessFromStream();
            checkGuess();
        }

        if (pwin) {
            System.out.printf("Nice job, %s! You guessed my number is %d guesses\n", userName, guesses);
        } else {
            System.out.printf("You have failed 6 times %s. Your number was %d.", userName, wnum);
        }
        getInpFromStream();
        if (inp == 'y') {
            PlayGame();
        }
    }
    public static void main(String[] args) {
        Main game = new Main();
        game.setScanner(System.in);
        game.getUserNameFromStream();
        game.PlayGame();
    }
    public void setScanner(InputStream sc) {
        this.sc = new Scanner(sc);
    }
    public int getUserNameFromStream() {
        System.out.println("Hello! What is your name!\n");
        try {
            userName = sc.nextLine();
            return 0;
        } catch (Exception str) {
            System.out.println("Come on......\n" +
                    "Maybe try Fred");
            userName = "Fred";
            return 1;
        }
    }
    public int getGuessFromStream(){
        System.out.println("Take a guess.\n");
        incGuesses();
        try{
            guess = Integer.parseInt(sc.next());
            if (!(guess <= 20 && guess >= 1))
                throw new Exception("DNF");
            return 0;
        }
        catch(Exception DNF){
            System.out.println("That's not an option.....\n"+
                    "So I choose for you.");
            guess = 15;
            return 1;
        }
    }
    public int getInpFromStream(){
        System.out.println("You wanna go again? y or n \n");
        try{
            String s1 = sc.next();
            if(s1.length() > 1){
                throw new Exception("Too many letters");
            }
            inp = s1.charAt(0);
            if(!(inp == 'y' || inp == 'n')){
                throw new Exception("No can do");
            }
            return 0;
        }
        catch(Exception noCanDo){
            System.out.println("You can't put that.....\n" +
                    "Goodbye!");
            setInp('n');
            return 1;
        }
    }

    public int checkGuess() {
        if (guess < wnum) {
            System.out.println("Your guess is too low.");
            return 1;
        } else if (guess > wnum) {
            System.out.println("Your guess is too high.");
            return 2;
        } else {
            setWin(true);
            return 0;
        }
    }
    private void generateWnum(){
        wnum = num.nextInt(19+1);
    }
    public void setWnum(int wnum){
        this.wnum = wnum;
    }
    public int getWnum(){
        return wnum;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setInp(char inp){
        this.inp = inp;
    }
    public char getInp(){
        return inp;
    }
    public void setGuesses(int guesses){
        this.guesses = guesses;
    }
    public void incGuesses(){
        guesses = guesses +1;
    }
    public int getGuesses(){
        return guesses;
    }
    public void setWin(boolean pwin){
        this.pwin = pwin;
    }
    public boolean getWin(){
        return pwin;
    }
}



