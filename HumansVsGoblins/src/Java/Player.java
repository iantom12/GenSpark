public class Player extends Land {
    public static final String RESET = "\033[0m";
    public static final String CYAN = "\033[1;36m";
    private int HP = 15;
    private int STR = 5;

    public Player(int[] position){
        super(position, CYAN + "P" + RESET);
    }

    public int getHP() {
        return HP;
    }

    public int getSTR() {
        return STR;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    @Override
    public String toString(){
        String result = "";
        result += "Str: " + this.getBarrier() + "\n";
        result += "Position: " + "x = " + this.getPosition()[1] +", " + "y = " + this.getPosition()[0] + "\n";
        result += "Health: " + this.getHP() + "\n";
        result += "Strength: " + this.getSTR() + "\n";
        return result;
    }
}
