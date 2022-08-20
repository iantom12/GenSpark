public abstract class Location {
    int[] position;
    String barrier;

    public Location(int[] position, String barrier){
        this.position = position;
        this.barrier = barrier;
    }

    public int[] getPosition() {
        return position;
    }

    public String getBarrier() {
        return barrier;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public void setBarrier(String barrier) {
        this.barrier = barrier;
    }
}
