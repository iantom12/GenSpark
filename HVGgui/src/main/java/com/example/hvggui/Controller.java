package com.example.hvggui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import java.util.Arrays;
import java.util.function.Predicate;

public class Controller {
    final SetUp game = new SetUp();
    Human human;
    Goblin goblin;

    @FXML
    ImageView humImg;

    @FXML
    ImageView gobImg;

    @FXML
    AnchorPane gameBoard;

    @FXML
    TextArea statusText;

    @FXML
    TextArea fightText;

    public Controller() {}

    @FXML
    public void initialize() {
        human = new Human(10, 4);
        goblin = new Goblin(game.randomNum(1, 8), game.randomNum(1, 6));
        setHumanImage();
        setGoblinImage();
        humanStatus();
        goblinStatus();
    }


    @FXML
    public void setHumanImage() {
        int[] arr = game.placement();
        humImg.setImage(new Image("human.png"));
        humImg.setTranslateX(arr[0]);
        humImg.setTranslateY(arr[1]);
        human.setHumanLocation(arr);
    }

    @FXML
    public void setGoblinImage() {
        gobImg.setImage(new Image("goblin.png"));
        while (true) {
            int[] arr = this.game.placement();
            if (!Arrays.equals(arr,human.humanLocation)) {
                gobImg.setTranslateX(arr[0]);
                gobImg.setTranslateY(arr[1]);
                goblin.setGoblinLocation(arr);
                return;
            }
            System.out.println("goblin same");
        }
    }

    @FXML
    public void humanVsGoblin() {
        while (true) {
            fightText.appendText(human.fight(human, goblin) + "\n");
            humanStatus();
            goblinStatus();
            if (goblin.HP <= 0) {
                fightText.appendText("\n You killed the Goblin!\n\n");
                goblin = new Goblin(game.randomNum(1, 10), game.randomNum(1, 6));
                setGoblinImage();
                humanStatus();
                goblinStatus();
            } else {
                fightText.appendText(goblin.fight(goblin, human) + "\n\n");
                if (human.HP > 0) {
                    continue;
                }
                fightText.appendText("The Goblin killed you. \n\n Game Over.");
                human.HP = 0;
                humanStatus();
                gameBoard.getChildren().remove(humImg);
            }
            return;

        }
    }

    @FXML
    public void humanStatus() {
        if (human.HP < 0) {
            human.HP = 0;
        }
        statusText.setText("             Status:\nYour Health: " + human.HP + "\n" + "Your Strength: " + human.STR +
                "\n-----------------------\n");
    }

    @FXML
    public void goblinStatus() {
        if (goblin.HP < 0) {
            goblin.HP = 0;
        }
        statusText.appendText("Goblin Health: " + goblin.HP + "\n" + "Goblin Strength: " + goblin.STR);

    }

    @FXML
    public void setHumanLocation() {
        int[] arr = new int[]{(int) humImg.getTranslateX(), (int) humImg.getTranslateY()};
        human.setHumanLocation(arr);
    }

    @FXML
    public void moveRight() {
        switch (game.checkMovement("right", human, goblin)) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() + 37.0);
                setHumanLocation();
            }
            case "goblin" -> {
                fightText.appendText("\n** Human Versus Goblin **\n\n");
                humanVsGoblin();
            }
        }
    }

    @FXML
    public void moveLeft() {
        switch (game.checkMovement("left", human, goblin)) {
            case "move" -> {
                humImg.setTranslateX(humImg.getTranslateX() - 37.0);
                setHumanLocation();
            }
            case "goblin" -> {
                fightText.appendText("\n** Human Versus Goblin **\n\n");
                humanVsGoblin();
            }
        }
    }

    @FXML
    public void moveUp() {
        switch (game.checkMovement("up", human, goblin)) {
            case "move" -> {
                humImg.setTranslateY(humImg.getTranslateY() - 37.0);
                setHumanLocation();
            }
            case "goblin" -> {
                fightText.appendText("\n** Human Versus Goblin **\n\n");
                humanVsGoblin();
            }
        }
    }


    @FXML
    public void moveDown() {
        switch (game.checkMovement("down", human, goblin)) {
            case "move" -> {
                humImg.setTranslateY(humImg.getTranslateY() + 37.0);
                setHumanLocation();
            }
            case "goblin" -> {
                fightText.appendText("\n** Human Versus Goblin **\n\n");
                humanVsGoblin();
            }
        }
    }
}


