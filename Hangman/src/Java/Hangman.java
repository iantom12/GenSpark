import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {

        String [] index = {"dexter", "house", "quality", "question", "quickly", "quite","race","radio","raise","range","rate","gun","guy","hair","half","discussion","disease","do","doctor"};
        Random rand= new Random();
        String word;
        Scanner input;
        ArrayList<Character> missedLetters = new ArrayList<>();
        char [] myAnswers;
        boolean gaming = true;
        int incGuesses = 0;

        public Hangman() {
            setWord(index[(rand.nextInt(this.index.length-1) + 1)]);
            this.myAnswers = new char[word.length()];
            Arrays.fill(this.myAnswers, '_');
        }

        public void playGame(){
            setInput(new Scanner(System.in));
            System.out.println("HANGMAN");

            while(this.gaming){
                System.out.println(drawHangman(this.incGuesses));
                System.out.print("\nMissed letters: ");
                for (char c: this.missedLetters){
                    System.out.print(c);
                }
                System.out.println("\n");
                System.out.println(this.myAnswers);
                System.out.println();

                if (this.incGuesses == 7){
                    System.out.println("You lose! Your word was..\n" +
                            this.word + ".");
                    playAgain();
                }
                else{
                    makeGuess();
                }
                if(this.word.compareTo(new String (this.myAnswers)) == 0){
                    System.out.println("Yes! The secret word is " + this.word + "! " + "You have won!");
                    playAgain();
                }
            }
        }

        public void playAgain(){
            System.out.println("Do you want to play again? (yes or no)\n");
            try{
                String replay = this.input.next();
                if(replay.compareTo("yes") == 0){
                    setGaming(true);
                    setIncGuesses(0);
                    this.missedLetters.clear();
                    setWord(index[(rand.nextInt(this.index.length-1) + 1)]);
                    this.myAnswers = new char[this.word.length()];
                    Arrays.fill(this.myAnswers, '_');
                }
                else if (replay.compareTo("no")== 0){
                    setGaming(false);
                    closeScanner();
                }
                else{
                    System.out.println("Huh....\n"+ "yes or no");
                    playAgain();
                }
            } catch (Exception e){
                System.out.println("That's not valid. DUECES!");
            }
        }
        public void makeGuess(){
            System.out.println("Guess a letter");
            char guessedLetter;
            boolean correct = false;
            try{
                guessedLetter = input.next().charAt(0);
            }catch(Exception e){
                System.out.println("Invalid Response");
                makeGuess();
                return;
            }
            if(missedLetters.contains(guessedLetter)){
                System.out.println("You have already guess that letter. Choose again.");
                makeGuess();
                return;
            }else{
                for (int i = 0; i < this.word.length();i++){
                    char letter = this.word.charAt(i);
                    if(myAnswers[i] == guessedLetter){
                        System.out.println("You have already guess that letter. Choose again.");
                        makeGuess();
                        return;
                    }else if(guessedLetter == letter ){
                        this.myAnswers[i] = letter;
                        correct = true;
                    }
                }
            }
            if(!correct){
                this.missedLetters.add(guessedLetter);
                this.incGuesses++;
            }
        }

        public String drawHangman(int guesses){
            String top = "\n  +---+";
            String pole = "      |";
            String ground = "     ===";
            String head = "  O   |";
            String upperBody = "  |   |";
            String lowerBody = "  |   |";
            String leftLeg = " /";
            String leftRightLeg = " / \\";
            String leftArm = " \\";
            String leftRightArm = " \\ /";
            switch (guesses){
                case 1:
                    return top + "\n\n" + head + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
                case 2:
                    return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + pole + "\n\n" +ground;
                case 3:
                    return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n\n" +ground;
                case 4:
                    return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftLeg+"\n" +ground;
                case 5:
                    return top + "\n\n" + head + "\n\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
                case 6:
                    return top + "\n\n" + head + "\n"+ leftArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
                case 7:
                    return top + "\n\n" + head + "\n"+ leftRightArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
                default:
                    return top + "\n\n" + pole + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
            }
        }
    public void closeScanner(){
        this.input.close();
    }
    public void setGaming(boolean gaming) {
        this.gaming = gaming;
    }
    public void setIncGuesses(int incGuesses) {
        this.incGuesses = incGuesses;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public void setInput(Scanner input) {
        this.input = input;
    }
    public void setMyAnswers(char[] myAnswers) {
        this.myAnswers = myAnswers;
    }
    public boolean getGaming(){
        return this.gaming;
    }
    public char[] getMyAnswers() {
        return myAnswers;
    }
    public String getWord() {
        return word;
    }
    public ArrayList<Character> getMissedLetters() {
        return missedLetters;
    }
    public Scanner getInput() {
        return input;
    }
    public static void main(String[] args) {
        Hangman h = new Hangman();
        h.playGame();
    }
}

