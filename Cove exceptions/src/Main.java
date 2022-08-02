import java.util.Scanner;

public class Main {

    public static void main( String[] args){
        Scanner keyboard = new Scanner(System.in);
        String Go ;

        System.out.println("You are in a land full of dragons");
        System.out.println ("In front of you, you see two caves.");
        System.out.println("In one cave, the dragon is friendly and will share his treasure with you.");
        System.out.println("The other dragon is greedy and hungry and will eat you on sight.");
        System.out.println("Which cave will you go into? (1 or 2)");
        Go = keyboard.next();

        try {
            if (Go.equals("1")) {
                System.out.println("You approach the cave...");
                System.out.println("It is dark and spooky...");
                System.out.println("A large dragon jumps out in front of you! He opens his jaws and....");
                System.out.println("Gobbles you down in one bite!");
            } else if (Go.equals("2")) {
                System.out.println("You approach the cave...");
                System.out.println("You see a dragon sleeping in the corner...");
                System.out.println("He awakens and says speak child...");
                System.out.println("You shutter and say please help me dragon.");
                System.out.println("The dragon replies fair enough and grants you the ability of flight");
                System.out.println("You learn flight and leave and never speak of this adventure again!");
            } else if(Go != "1" || Go != "2")
                throw  new Exception ("notValid");

        } catch (Exception notValid){
            System.out.println("Not a valid option! Try again.");
        }
    }
}
