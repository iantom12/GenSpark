public class Goblin extends Land {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private int HP = 8;
    private int STR = 3;

    public Goblin(int[] position){
        super(position,RED + "G" + RESET);
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
