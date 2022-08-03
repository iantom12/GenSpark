import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
class DragonCoveTest {
    DragonCove testCave;
    InputStream stdin = System.in;

    @BeforeEach
    void setUp() {
        testCave = new DragonCove();
    }

    @DisplayName("inp: 1, winc: 1")
    @Test
    void testCase1(){
        String testInput = "1\r\n";
        testCave.setCave(1);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setChoice();
        assertEquals(1, testCave.pwin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("inp: 1, winc: 2")
    @Test
    void testCase2(){
        String testInput = "1\r\n";
        testCave.setCave(2);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setChoice();
        assertEquals(0, testCave.pwin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("inp: 2, winc: 1")
    @Test
    void testCase3(){
        String testInput = "2\r\n";
        testCave.setCave(1);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setChoice();
        assertEquals(0, testCave.pwin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("inp: 2, winc: 2")
    @Test
    void testCase4(){
        String testInput = "2\r\n";
        testCave.setCave(2);
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        testCave.setChoice();
        assertEquals(1, testCave.pwin(), "Test1 failed - in:1 win:1");
    }

    @DisplayName("input a string")
    @Test
    void testString(){
        String testInput = "Hello World!\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testCave.setChoice(), "String Test Failed");
    }

    @DisplayName("input too high")
    @Test
    void testOTooHigh(){
        String testInput = "999999\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testCave.setChoice(), "String Test Failed");
    }

    @DisplayName("input too low")
    @Test
    void testTooLow(){
        String testInput = "0\r\n";
        System.setIn(new ByteArrayInputStream(testInput.getBytes()));
        assertEquals(0, testCave.setChoice(), "String Test Failed");
    }

    @AfterEach
    void tearDown() {
        System.setIn(stdin);
    }
}

