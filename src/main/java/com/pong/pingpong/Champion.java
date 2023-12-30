package com.pong.pingpong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;


public class Champion{

    private AnimationTimer animationTimer;
    private int goal;
    private String name1;
    private String name2;
    private int score1 = 0;
    private int score2 = 0;
    private Scoreboard scoreboard;
    private CollisionSystem cystem;
    private Stage primaryStage;

    public Champion(int goal, String name1, String name2, Scoreboard scoreboard, CollisionSystem cystem, Stage primaryStage) {
        this.goal = goal;
        this.name1 = name1;
        this.name2 = name2;
        this.scoreboard = scoreboard;
        this.cystem = cystem;
        this.primaryStage = primaryStage;
    }


    public void findChampion(){
        startTimer();
    }


    private void startTimer(){
        animationTimer = new AnimationTimer(){
            @Override
            public void handle(long now) {

                score1 = scoreboard.getScore1();
                score2 = scoreboard.getScore2();

                if(score1 >= goal){
                    cystem.stopInertia();
                    showWinnerPopup(name1);
                    animationTimer.stop();
                }
                if(score2 >= goal){
                    cystem.stopInertia();
                    showWinnerPopup(name2);
                    animationTimer.stop();
                }
            }
        };

        animationTimer.start();
    }


    private void showWinnerPopup(String winner) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game-Over");
            alert.setHeaderText(null);
            alert.setContentText(winner + " wins!");

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
            okBtn.setOnAction(event -> {
                PongFX return_main = new PongFX();
                return_main.start(primaryStage);
                alert.close();
            });

            alert.showAndWait();
        });
    }

}
