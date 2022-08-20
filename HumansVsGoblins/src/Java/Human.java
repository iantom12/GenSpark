public class Human extends Land {
    public static final String RESET = "\033[0m";
    public static final String BLUE = "\033[1;34m";
    private int HP = 10;
    private int STR = 4;

    public Human(int[] position){
        super(position, BLUE + "H" + RESET);
        }

    public int getHP() {
        return HP;
    }
    public int getSTR(){
        return STR;
    }
    public void setHP(int HP){
        this.HP = HP;
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
