import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
class HangmanTest {
    Hangman hangman;
    @BeforeEach
    void setUp() {
        hangman = new Hangman();
    }
    @DisplayName("Start Hangman")
    @Test
    void initiateHangman(){
        String top = "\n  +---+";
        String pole = "      |";
        String ground = "     ===";
        String result = top + "\n\n" + pole + "\n\n" + pole +"\n\n" + pole + "\n\n" +ground;
        assertEquals(result,hangman.drawHangman(0),"Initiate Hangman Failed");
    }
    @DisplayName("Complete Hangman")
    @Test
    void fullHangman(){
        String top = "\n  +---+";
        String ground = "     ===";
        String head = "  O   |";
        String upperBody = "  |   |";
        String lowerBody = "  |   |";
        String leftRightLeg = " / \\";
        String leftRightArm = " \\ /";
        String result = top + "\n\n" + head + "\n"+ leftRightArm +"\n" + upperBody +"\n\n" + lowerBody + "\n"+ leftRightLeg+"\n" +ground;
        assertEquals(result,hangman.drawHangman(7),"Full Hangman Failed");
    }
    @DisplayName("Testing yes to play again")
    @Test
    void yesPlayAgain(){
        InputStream input = new ByteArrayInputStream("yes".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.playAgain();
        assertTrue(hangman.getGaming(), "Test YES play again failed");
    }
    @DisplayName("Testing no to play again")
    @Test
    void noPlayAgain(){
        InputStream input = new ByteArrayInputStream("no".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.playAgain();
        assertFalse(hangman.getGaming(), "Test NO play again failed");
    }
    @DisplayName("Test correct guess from myAnswers")
    @Test
    void makeGuessCorrectUserBank(){
        hangman.setWord("dexter");
        hangman.setMyAnswers(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("x".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.makeGuess();
        assertTrue(new String(hangman.getMyAnswers()).contains("x"), "Correct Guess failed (userBank)");
    }

    @DisplayName("Test bad guess from myAnswers")
    @Test
    void makeGuessBadUserBank(){
        hangman.setWord("dexter");
        hangman.setMyAnswers(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("n".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.makeGuess();
        assertFalse(new String(hangman.getMyAnswers()).contains("n"), "Made bad guess failed (userBank)");
    }
    @DisplayName("Test correct guess from missedLetters")
    @Test
    void makeGuessCorrectMissedLetters(){
        hangman.setWord("house");
        hangman.setMyAnswers(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("o".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.makeGuess();
        assertFalse(hangman.getMissedLetters().contains('o'), "Correct guess failed (missedLetters)");
    }
    @DisplayName("Test bad guess from missedLetters")
    @Test
    void makeGuessBadMissedLetters(){
        hangman.setWord("quite");
        hangman.setMyAnswers(new char[hangman.getWord().length()]);
        InputStream input = new ByteArrayInputStream("b".getBytes());
        hangman.setInput(new Scanner(input));
        hangman.makeGuess();
        assertTrue(hangman.getMissedLetters().contains('b'), "Bad guess failed (missedLetters)");
    }
    @AfterEach
    void tearDown(){
        if (hangman.getInput()!=null){
            hangman.closeScanner();
        }
    }
}

