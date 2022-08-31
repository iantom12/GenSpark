package com.example.hvggui;

public class Human {
    int HP;
    final int STR;
    final int[] humanLocation = new int[2];

    public Human(int HP, int STR){
        this.HP = HP;
        this.STR = STR;
    }
    public String fight(Human human, Goblin goblin){
        double x = 1 + Math.random() * (double)human.STR;
        int attackSTR = (int) x;
        goblin.HP = goblin.HP - attackSTR;
        return "!*!BOP!*! You hit for " + attackSTR;
    }
    public void setHumanLocation(int[]arr) {
        humanLocation[0] = arr[0];
        humanLocation[1] = arr[1];
    }

    public int[] getHumanLocation() {
        return humanLocation;
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
