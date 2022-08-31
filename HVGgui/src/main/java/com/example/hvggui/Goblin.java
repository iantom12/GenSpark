package com.example.hvggui;

public class Goblin{
    int HP;
    final int STR;
    final int [] goblinLocation = new int[2];

    public Goblin(int HP, int STR){
        this.HP = HP;
        this.STR = STR;
    }
    public void setGoblinLocation(int[] arr){
        this.goblinLocation[0] = arr[0];
        this.goblinLocation[1] = arr[1];
    }

    public int[] getGoblinLocation() {
        return goblinLocation;
    }

    public String fight(Goblin goblin, Human human){
        double x = 1 + Math.random() * (double) goblin.STR;
        int attackSTR = (int)x;
        human.HP = human.HP - attackSTR;
        return "!*!BAM!*! The goblin hit you for " + attackSTR;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSTR() {
        return STR;
    }
}
