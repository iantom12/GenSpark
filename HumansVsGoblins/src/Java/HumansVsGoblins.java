import java.util.ArrayList;
import java.util.Scanner;

public class HumansVsGoblins {
    private final ArrayList<Human> humans = new ArrayList<>();
    private final ArrayList<Goblin> goblins = new ArrayList<>();
    private Scanner userInput;
    Player player;
    boolean gaming = true;
    private Land[][] world = new Land[22][22];

    public void playGame() {
        setupGame();
        setUserInput(new Scanner(System.in));
        System.out.println("Welcome to Humans vs Goblins! \n" +
                "You are the Player and will be shown as the letter P. You will be cyan in color. \n"
                + "You are fighting the goblins which are shown by the letter G and are red. \n " +
                " You move by pressing n for North, s for South, e for East, w for West. \n" +
                "Humans are allies to you and can take the hit for you! But be warned you only get a limited amount. \n" +
                "Have Fun!");
        while (gaming) {
            goblinsChase();

            System.out.println(this.stringGameWorld());

            if (!this.gaming) {
                this.closeScanner();
                break;
            }
            if (goblins.isEmpty()) {
                System.out.println("You did it! You won! You can rest now champion.");
                this.closeScanner();
                break;
            }
            humansCheckCollision();
            System.out.println("Choose which direction to go." + "\n");
            movePlayer();

        }
    }

    public void setupGame() {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                this.world[i][j] = new Land(new int[]{i,j});
            }
        }
        for (int i = 0; i < 22; i++) {
            this.world[0][i].setBarrier("|");
            this.world[i][0].setBarrier("|");
            this.world[21][i].setBarrier("|");
            this.world[i][21].setBarrier("|");
        }
        //map spawn
        for (int i = 0; i < 40; i++) {
            int x = (int) (Math.random() * 20) + 1;
            int y = (int) (Math.random() * 20) + 1;
            if (this.world[y][x].getBarrier().compareTo("|") != 0) {
                this.world[y][x].setBarrier("|");
            } else {
                i--;
            }

        }
        //human spawn
        for (int i = 0; i < 4; i++) {
            int x = (int) (Math.random() * 20) + 1;
            int y = (int) (Math.random() * 20) + 1;
            if (this.world[y][x].getBarrier().compareTo("|") != 0 && !(this.world[y][x] instanceof Human)) {
                Human human = new Human(new int[]{y, x});
                this.world[y][x] = human;
                this.humans.add(human);
            } else {
                i--;
            }
        }
        //Goblin spawn
        for (int i = 0; i < 3; i++) {
            int x = (int) (Math.random() * 20) + 1;
            int y = (int) (Math.random() * 20) + 1;
            if (this.world[y][x].getBarrier().compareTo("|") != 0 && !(this.world[y][x] instanceof Human) && !(this.world[y][x] instanceof Goblin)) {
                Goblin goblin = new Goblin(new int[]{y, x});
                this.world[y][x] = goblin;
                this.goblins.add(goblin);
            } else {
                i--;
            }
        }
        //Player spawn
        while (true) {
            int x = (int) (Math.random() * 20) + 1;
            int y = (int) (Math.random() * 20) + 1;
            if (this.world[y][x].getBarrier().compareTo("|") != 0 && !(this.world[y][x] instanceof Human) && !(this.world[y][x] instanceof Goblin)) {
                Player p = new Player(new int[]{y, x});
                this.world[y][x]= p;
                this.player = p;
                break;
            }
        }
    }

    public void humansCheckCollision() {
        for (int i = 0; i < this.humans.size(); i++) {
            int y = this.humans.get(i).getPosition()[0];
            int x = this.humans.get(i).getPosition()[1];

            if (this.world[y + 1][x] instanceof Goblin) {
                String result = Fight.goblinVsHuman(this.humans.get(i), (Goblin) this.getWorld()[y + 1][x], this);
                System.out.println(result);
                System.out.println(this.stringGameWorld());
            } else if (this.world[y - 1][x] instanceof Goblin) {
                String result = Fight.goblinVsHuman(this.humans.get(i), (Goblin) this.getWorld()[y - 1][x], this);
                System.out.println(result);
                System.out.println(this.stringGameWorld());
            } else if (this.world[y][x + 1] instanceof Goblin) {
                String result = Fight.goblinVsHuman(this.humans.get(i), (Goblin) this.getWorld()[y][x + 1], this);
                System.out.println(result);
                System.out.println(this.stringGameWorld());
            } else if (this.world[y][x - 1] instanceof Goblin) {
                String result = Fight.goblinVsHuman(this.humans.get(i), (Goblin) this.getWorld()[y][x - 1], this);
                System.out.println(result);
                System.out.println(this.stringGameWorld());
            }
        }
    }

    public void goblinsChase(){
        for (int i = 0; i<this.goblins.size();i++){
            int goblin1 = goblins.get(i).getPosition()[0];
            int goblin2 = goblins.get(i).getPosition()[1];

            int player1 = this.player.getPosition()[0];
            int player2 = this.player.getPosition()[1];
            if(player1>goblin1 && this.world[goblin1+1][goblin2].getBarrier().contains("*")){
                int[] newPosition = new int[]{goblin1+1,goblin2};
                changeLand(goblins.get(i).getPosition(),newPosition);
                goblins.get(i).setPosition(newPosition);
            }else if(player1<goblin1 && this.world[goblin1-1][goblin2].getBarrier().contains("*")){
                int[] newPosition = new int[]{goblin1-1,goblin2};
                changeLand(goblins.get(i).getPosition(),newPosition);
                goblins.get(i).setPosition(newPosition);
            }else if(player2>goblin2 && this.world[goblin1][goblin2+1].getBarrier().contains("*")){
                int[] newPosition = new int[]{goblin1,goblin2+1};
                changeLand(goblins.get(i).getPosition(),newPosition);
                goblins.get(i).setPosition(newPosition);
            }else if(player2<goblin2 && this.world[goblin1][goblin2-1].getBarrier().contains("*")) {
                int[] newPosition = new int[]{goblin1, goblin2 - 1};
                changeLand(goblins.get(i).getPosition(), newPosition);
                goblins.get(i).setPosition(newPosition);
            }
            goblin1 = this.goblins.get(i).getPosition()[0];
            goblin2 = this.goblins.get(i).getPosition()[1];

            if(this.world[goblin1+1][goblin2] instanceof Player){
                String result = Fight.playerVsGoblin((Player) this.world[goblin1+1][goblin2],goblins.get(i),this);
                System.out.println(result);

            } else if (this.world[goblin1-1][goblin2] instanceof Player) {
                String result = Fight.playerVsGoblin((Player) this.world[goblin1-1][goblin2],goblins.get(i),this);
                System.out.println(result);

            } else if (this.world[goblin1][goblin2+1] instanceof Player) {
                String result = Fight.playerVsGoblin((Player) this.world[goblin1][goblin2+1],goblins.get(i),this);
                System.out.println(result);

            } else if (this.world[goblin1][goblin2-1] instanceof Player) {
                String result = Fight.playerVsGoblin((Player) this.world[goblin1][goblin2 - 1], goblins.get(i), this);
                System.out.println(result);
            }
        }
    }
    public void movePlayer() {
        char move;
        try {
            move = this.userInput.next().charAt(0);
        } catch (Exception e) {
            System.out.println("Enter n for North, s for South, e for East, and w for West");
            movePlayer();
            return;
        }
        int y = this.player.getPosition()[0];
        int x = this.player.getPosition()[1];
        if (move == 'n' && this.world[y + 1][x] instanceof Goblin) {
            String result = Fight.playerVsGoblin(this.player, (Goblin) this.world[y - 1][x], this);
            System.out.println(result);
        } else if (move == 's' && this.world[y + 1][x] instanceof Goblin) {
            String result = Fight.playerVsGoblin(this.player, (Goblin) this.world[y + 1][x], this);
            System.out.println(result);
        } else if (move == 'e' && this.world[y + 1][x] instanceof Goblin) {
            String result = Fight.playerVsGoblin(this.player, (Goblin) this.world[y][x + 1], this);
            System.out.println(result);
        } else if (move == 'w' && this.world[y + 1][x] instanceof Goblin) {
            String result = Fight.playerVsGoblin(this.player, (Goblin) this.world[y][x - 1], this);
            System.out.println(result);
        } else {
            if (move == 'n' && this.world[y - 1][x].getBarrier().contains("*")) {
                int[] newPosition = new int[]{y - 1, x};
                changeLand(this.player.getPosition(), newPosition);
                this.player.setPosition(newPosition);
            } else if (move == 's' && this.world[y + 1][x].getBarrier().contains("*")) {
                int[] newPosition = new int[]{y + 1, x};
                changeLand(this.player.getPosition(), newPosition);
                this.player.setPosition(newPosition);
            } else if (move == 'e' && this.world[y][x + 1].getBarrier().contains("*")) {
                int[] newPosition = new int[]{y, x + 1};
                changeLand(this.player.getPosition(), newPosition);
                this.player.setPosition(newPosition);
            } else if (move == 'w' && this.world[y][x - 1].getBarrier().contains("*")) {
                int[] newPosition = new int[]{y, x - 1};
                changeLand(this.player.getPosition(), newPosition);
                this.player.setPosition(newPosition);
            } else {
                System.out.println("Please give n for north, s for south, e for east, and w for west.");
                movePlayer();

            }
        }
    }


    public void changeLand( int[] from, int[] to){
        Land temp = this.world[to[0]][to[1]];
        this.world[to[0]][to[1]] = this.world[from[0]][from[1]];
        this.world[from[0]][from[1]] = temp;
    }


    public String stringGameWorld() {
        String output = "";
        for (Location[] lands : this.getWorld()) {
            for (Location land : lands) {
                if (land.getBarrier().compareTo("|") == 0) {
                    output += "|" + land.getBarrier() + "|";
                } else {
                    output += " " + land.getBarrier() + " ";
                }
            }
            output += "\n";
        }
        return output;
    }
    public void rHuman(Human h){
        int[] position = h.getPosition();
        this.world[position[0]][position[1]] = new Land(position);
        this.getHumans().remove(h);
    }
    public void rGoblin(Goblin g){
        int[] position = g.getPosition();
        this.world[position[0]][position[1]] = new Land(position);
        this.getGoblins().remove(g);
    }

    public boolean getGaming(){
        return this.gaming;
    }
    public Location[][] getWorld(){
        return world;
    }
    public Player getPlayer(){
        return player;
    }

    public Scanner getUserInput() {
        return userInput;
    }

    public ArrayList<Human> getHumans() {
        return humans;
    }

    public ArrayList<Goblin> getGoblins() {
        return goblins;
    }

    public void setGaming(boolean gaming){
        this.gaming = gaming;
    }
    public void setWorld(Land[][] world){
        this.world = world;
    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setUserInput(Scanner userInput){
        this.userInput = userInput;
    }
    public void closeScanner(){
        this.userInput.close();
    }
    public static void main(String[] args){
        HumansVsGoblins humansVsGoblins = new HumansVsGoblins();
        humansVsGoblins.playGame();
    }
}
