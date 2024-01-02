package com.pong.pingpong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

/**
 * The Champion class is responsible for determining the winner of a game and displaying a popup message with the winner's name.
 * It uses an AnimationTimer to continuously check the scores and stop the game when a player reaches the goal score.
 * The Champion class also handles the logic for restarting the game after the popup is closed.
 */
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
    private RacquetBot bot;

    /**
     * Constructs a Champion object with the specified goal score, player names, scoreboard, collision system, and primary stage.
     * @param goal The score that a player needs to reach in order to win the game.
     * @param name1 The name of player 1.
     * @param name2 The name of player 2.
     * @param scoreboard The scoreboard object that keeps track of the scores.
     * @param cystem The collision system object that handles the game physics.
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public Champion(int goal, String name1, String name2, Scoreboard scoreboard, CollisionSystem cystem, RacquetBot bot, Stage primaryStage) {
        this.goal = goal;
        this.name1 = name1;
        this.name2 = name2;
        this.scoreboard = scoreboard;
        this.cystem = cystem;
        this.primaryStage = primaryStage;
        this.bot = bot;
    }


    /**
     * Starts the AnimationTimer to determine the winner of the game.
     */
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

                    if(bot != null)
                        bot.stopBot();

                    showWinnerPopup(name1);
                    animationTimer.stop();
                }
                if(score2 >= goal){
                    cystem.stopInertia();

                    if(bot != null)
                        bot.stopBot();

                    showWinnerPopup(name2);
                    animationTimer.stop();
                }
            }
        };

        animationTimer.start();
    }


    private void showWinnerPopup(String winner){
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
