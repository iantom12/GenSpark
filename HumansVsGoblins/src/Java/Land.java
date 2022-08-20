public class Land extends Location{
    public Land(int[]position){
        super(position,"*");
    }
    public Land(int[] position,String barrier){
        super(position,barrier);
    }
}
