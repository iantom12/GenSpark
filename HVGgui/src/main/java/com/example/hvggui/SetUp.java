package com.example.hvggui;

import java.util.Arrays;

public class SetUp {

    public SetUp(){}

    public String checkMovement(String s, Human human, Goblin goblin) {

        int x = 0;
        switch (s) {
            case "up" -> {
                x += human.humanLocation[1] - 37;
                if (Arrays.equals(new int[]{human.humanLocation[0], x}, goblin.goblinLocation)) {
                    return "goblin";
                }
                if (x < 0) {
                    return "nope";
                }
            }
            case "down" -> {
                x += human.humanLocation[1] + 37;
                if (Arrays.equals(new int[]{human.humanLocation[0], x}, goblin.goblinLocation)) {
                    return "goblin";
                }
                if (x > 222) {
                    return "nope";
                }
            }
            case "left" -> {
                x += human.humanLocation[0] - 37;
                if (Arrays.equals(new int[]{human.humanLocation[1], x}, goblin.goblinLocation)) {
                    return "goblin";
                }
                if (x < 0) {
                    return "nope";
                }
            }
            case "right" -> {
                x += human.humanLocation[0] + 37;
                if (Arrays.equals(new int[]{human.humanLocation[1], x}, goblin.goblinLocation)) {
                    return "goblin";
                }
                if (x > 333) {
                    return "nope";
                }
            }
        }
        return"move";
    }
    public int[] placement(){
        int x = randomNum(0,8) * 37;
        int y = randomNum(0,5) * 37;
        return new int[]{x,y};
    }

    public int randomNum(int i, int j){
        double x = (double)i + Math.random() * (double)(j - i);
        return (int) x;
    }

}
