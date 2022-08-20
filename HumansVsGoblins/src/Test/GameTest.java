import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class GameTest {
    HumansVsGoblins hvg;
    @BeforeEach
    void setUp() {
        hvg = new HumansVsGoblins();
        hvg.getWorld();

        Land[][] test = new Land[22][22];
        for (int i = 0;i<22;i++){
            for(int j = 0; j<22;j++){
                test[i][j] = new Land(new int[]{i,j});
            }
        }
        hvg.setWorld(test);
    }

    @DisplayName("goblin collision")
    @Test
    void goblinFight() {
        boolean fight = false;
        Goblin g = new Goblin(new int[]{3, 3});
        hvg.getGoblins().add(g);
        hvg.getWorld()[3][3] = g;

        Player p = new Player(new int[]{3, 2});
        hvg.setPlayer(p);
        hvg.getWorld()[3][2] = p;
        hvg.goblinsChase();

        if (hvg.getWorld()[3][3] instanceof Goblin) {
            fight = true;
        }
        assertFalse(fight);
    }

    @Test
    void goblinChase(){
        boolean chase = false;
        Goblin g = new Goblin(new int[]{5,5});
        hvg.getGoblins().add(g);
        hvg.getWorld()[5][5] = g;

        Player p = new Player(new int[]{7,5});
        hvg.setPlayer(p);
        hvg.getWorld()[5][4] = p;
        hvg.goblinsChase();
        if(hvg.getWorld()[6][5] instanceof Goblin && !(hvg.getWorld()[5][5] instanceof Goblin)){
            chase = true;
        }
        assertTrue(chase);
    }

    @Test
    void playerFight(){
        boolean collision = false;
        Player p = new Player(new int[]{5,5});
        hvg.setPlayer(p);
        hvg.getWorld()[5][5] = p;

        Goblin g = new Goblin(new int[]{6,5});
        hvg.getGoblins().add(g);
        hvg.getWorld()[6][5] = g;
        InputStream input = new ByteArrayInputStream("s".getBytes());
        hvg.setUserInput(new Scanner(input));
        hvg.movePlayer();
        if(hvg.getWorld()[6][5] instanceof Goblin){
            collision = true;
        }
        assertFalse(collision);
    }


    @Test
    void move(){
        Player p = new Player(new int[]{5,5});
        hvg.setPlayer(p);
        InputStream input = new ByteArrayInputStream("s".getBytes());
        hvg.setUserInput(new Scanner(input));
        hvg.movePlayer();
        boolean move = hvg.getWorld()[6][5] instanceof Player;
        assertFalse(move);

    }

    @AfterEach
    void tearDown() {
        if(hvg.getUserInput()!= null) {
            hvg.closeScanner();
        }
    }
}
