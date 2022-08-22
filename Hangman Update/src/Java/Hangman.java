import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Hangman {

    String[] index = {"dexter", "house", "quality", "question", "quickly", "quite", "race", "radio", "raise", "range", "rate", "gun", "guy", "hair", "half", "discussion", "disease", "do", "doctor"};
    Random rand = new Random();
    String word;
    Scanner input;
    ArrayList<Character> missedLetters = new ArrayList<>();
    char[] myAnswers;
    boolean gaming = true;
    int incGuesses = 0;
    String userName;

    public Hangman() {
        setWord(index[(rand.nextInt(this.index.length - 1) + 1)]);
        String x = Arrays.stream(word.split("")).map(c -> "_").reduce("", (a, b) -> a + b);
        setMyAnswers(x.toCharArray());
    }

    public void playGame() {
        setInput(new Scanner(System.in));
        System.out.println("Please enter your name..\n");
        System.out.println(startGame() + "\n");
        System.out.println("HANGMAN");

        while (this.gaming) {
            System.out.println(drawHangman());
            System.out.println("\nMissed Letters: ");
            System.out.print(missedLetters.stream().map(Object :: toString).reduce("",(a,b)-> a+b));
            System.out.println("\n");
            System.out.println(this.myAnswers);
            System.out.println("\n");

            if (this.incGuesses == 7) {
                System.out.println("You lose! Your word was..\n" +
                        this.word + ".");
                addPlayer();
                System.out.println(getTopPlayer());
                playAgain();
            } else {
                makeGuess();
            }
            if (this.word.compareTo(new String(this.myAnswers)) == 0) {
                System.out.println("Yes " + this.userName + "! The secret word is " + this.word + "! " + "You have won!");
                addPlayer();
                System.out.println(getTopPlayer());
                playAgain();
            }
        }
    }

    public void addPlayer() {
        Path path = Path.of("/Applications/GenSpark/Tests for GS/Hangman/src/scoreList.txt");
        try {
            Files.writeString(path, "Name: " + this.userName + ", score: " + this.incGuesses + "\n", StandardOpenOption.APPEND);
        } catch (Exception IO) {
            System.out.println("Error");
        }
    }

    public String getTopPlayer() {
        String score = "";
        Path path = Path.of("/Applications/GenSpark/Tests for GS/Hangman/src/scoreList.txt");
        try {
            Stream<String> s1 = Files.lines(path);
            Optional<String> topScore = s1.filter(s -> Character.getNumericValue(s.charAt(s.length() - 1)) < this.incGuesses)
                    .min(Comparator.comparingInt(a -> Character.getNumericValue(a.charAt(a.length() - 1))));
            if (topScore.isPresent()) {
                score += "You did not beat the high score... Sorry you have to beat:\n ";
                score += topScore.get() + "\n";
            } else {
                score += "Good job you have the top score with " + this.incGuesses + " guesses!" + "\n";
            }
            s1.close();
        } catch (Exception IO) {
            score += "Error with scoring..." + "\n";
        }
        return score;
    }

    public String startGame() {
        try {
            setUserName(this.input.next());
            return "Good luck " + this.userName + "! Guess within 7 tries.";
        } catch (Exception IO) {
            return "User name is invalid";
        }
    }

    public void playAgain() {
        System.out.println("Do you want to play again? (yes or no)\n");
        try {
            String replay = this.input.next();
            if (replay.compareTo("yes") == 0) {
                setGaming(true);
                setIncGuesses(0);
                this.missedLetters.clear();
                setWord(index[(rand.nextInt(this.index.length - 1) + 1)]);
                String x = Arrays.stream(word.split("")).map(c-> "_").reduce("",(a,b)-> a+b);
                setMyAnswers(x.toCharArray());
            } else if (replay.compareTo("no") == 0) {
                setGaming(false);
                closeScanner();
            } else {
                System.out.println("Huh....\n" + "yes or no");
                playAgain();
            }
        } catch (Exception e) {
            System.out.println("That's not valid. DUECES!");
        }
    }

    public void makeGuess() {
        System.out.println("Guess a letter");
        char guessedLetter;
        boolean correct = false;
        try {
            guessedLetter = input.next().charAt(0);
        } catch (Exception e) {
            System.out.println("Invalid Response");
            makeGuess();
            return;
        }
        if (missedLetters.contains(guessedLetter)) {
            System.out.println("You have already guessed that letter. Choose again.");
            makeGuess();

        } else if(String.valueOf(this.myAnswers).contains(String.valueOf(guessedLetter))) {
            System.out.println("You have already guessed that letter. Choose again.");
            makeGuess();
        }else if(this.word.contains(String.valueOf(guessedLetter))) {
            IntStream.range(0, this.word.length()).filter(i -> this.word.toCharArray()[i] == guessedLetter)
                    .forEach(n -> this.myAnswers[n] = guessedLetter);
        }
        else {
            this.missedLetters.add(guessedLetter);
            this.incGuesses++;
        }
    }


    public String drawHangman() {
        String x = "";
        try{
            String y = Files.readString(Path.of("/Applications/GenSpark/Tests for GS/Hangman/src/fullHangman.txt"));
            String z = Arrays.stream(y.split("guesses")).filter(e-> e.contains(Integer.toString(this.incGuesses))).reduce("",(a,b) -> a+b);
            x += z.substring(1,z.length()-3);
        }catch (Exception IO){
            x += "File not found";
        }
        return x;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

