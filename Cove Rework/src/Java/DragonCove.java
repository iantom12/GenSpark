import java.util.Random;
import java.util.Scanner;

public class DragonCove {
    private static final Random num = new Random();
    private int winc;
    private int choice;

    public static void main(String[] args){
        DragonCove game = new DragonCove();
        game.playGame();
    }
    public int playGame(){
        System.out.println("You are in a land full of Dragons.\n"+
                "In front of you, you see two caves.\n"+
                "In one cave, the Dragon is friendly and will share his treasure with you.\n"+
                "The other dragon is greedy and hungry and will eat you on sight.\n"+
                "Which cave will you go into? (1 or 2)");
        setCave(num.nextInt(2) + 1);
        setChoice();
        pwin();
        return 0;
    }
    public int pwin(){
        System.out.println("You approach the cave....\n"+
                "It is dark and spooky....\n"+
                "A large dragon jumps out in front of you! He opens his jaws and.....");
        if(this.choice == this.winc){
            System.out.println("Speaks an ancient tone granting you the ability of flight!3\n"+
                    "You leave this cave and never speak of it again....");
            return 1;
        }
        else{
            System.out.println("Gobbles you down in one bite!");
            return 0;
        }

    }
    public void setCave(int cave){
        this.winc=cave;
    }
    public int setChoice(){
        try{
            Scanner sc = new Scanner(System.in);
            this.choice = Integer.parseInt(sc.next());
            if(!(this.choice > 0 && this.choice < 3)){
                throw new Exception("huh");
            }
            return 0;
        }
        catch(Exception huh){
            System.out.println("Come on man you can't do that.....\n" +
                    "My turn...");
            this.choice = 0;
            return 0;
        }
    }

}
