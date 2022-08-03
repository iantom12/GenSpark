import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;

class GTNTest {
    Main testGame;

    @BeforeEach
    void setUp() {
        testGame = new Main();
    }

    @Test
    void getUserNameTest() {
        String testInput = "Ian\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testGame.getUserNameFromStream(), "usernameTestFailed");
    }

    @Test
    void getGuessPassCase() {
        String testInput;
        for(int i = 1; i < 21; i++) {
            testInput = i + "\r\n";
            testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
            assertEquals(0, testGame.getGuessFromStream(), "userGuessTestFailed");
        }
    }
    @Test
    void getGuessFailCase() {
        String testInput = "Hello\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(1, testGame.getGuessFromStream(), "userGuessTestFailed");
    }


    @Test
    void getAckPassCase() {
        String testInput = "y\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testGame.getInpFromStream(), "userInpTestFailed");
    }

    @Test
    void getAckFailCase() {
        String testInput1 = "This Should Fail\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput1.getBytes()));
        assertEquals(1, testGame.getInpFromStream(), "userInpTestFailed");

        String testInput2 = "No\r\n";
        testGame.setScanner(new ByteArrayInputStream(testInput2.getBytes()));
        assertEquals(1, testGame.getInpFromStream(), "userInpTestFailed");
    }

    @Test
    void setWinNumTest() {
        testGame.setWnum(10);
        assertEquals(10, testGame.getWnum(), "setWnumTestFailed");
    }

    @Test
    void setUserNameTest() {
        testGame.setUserName("Janet");
        assertEquals("Janet", testGame.getUserName(), "setUserNameTestFailed");
    }



    @Test
    void setUserAckTest() {
        testGame.setInp('y');
        assertEquals('y', testGame.getInp(), "setInpTestFailed");
    }

    @Test
    void setGuessCountTest() {
        testGame.setGuesses(99);
        assertEquals(99, testGame.getGuesses(), "setGuessesTestFailed");
    }

    @Test
    void incGuessCountTest() {
        testGame.setGuesses(10);
        testGame.incGuesses();
        assertEquals(11, testGame.getGuesses(), "incGuessesTestFailed");
    }

    @Test
    void setWinTest() {
        testGame.setWin(true);
        assertTrue(testGame.getWin(), "setWinTestFailed");

        testGame.setWin(false);
        assertFalse(testGame.getWin(), "setWinTestFailed");
    }

    @Test
    void checkGuessTooHigh(){
        String testInput = "20\r\n";
        testGame.setWnum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getGuessFromStream();
        assertEquals(2, testGame.checkGuess(), "checkGuessTooHighTestFailed");
    }

    @Test
    void checkGuessTooLow(){
        String testInput = "1\r\n";
        testGame.setWnum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getGuessFromStream();
        assertEquals(1, testGame.checkGuess(), "checkGuessTooLowTestFailed");
    }

    @Test
    void checkGuessEquals(){
        String testInput = "10\r\n";
        testGame.setWnum(10);
        testGame.setScanner(new ByteArrayInputStream(testInput.getBytes()));
        testGame.getGuessFromStream();
        assertEquals(0, testGame.checkGuess(), "checkGuessEqualsTestFailed");
    }

    @AfterEach
    void tearDown() {
    }
}
