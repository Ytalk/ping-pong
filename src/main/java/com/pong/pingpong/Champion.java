package com.pong.pingpong;

import javafx.animation.AnimationTimer;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The Champion class is responsible for determining the winner of a game and displaying a popup message with the winner's name.
 * It uses an AnimationTimer to continuously check the scores and stop the game when a player reaches the goal score.
 * The Champion class also handles the logic for restarting the game after the popup is closed.
 */
public class Champion{

    private AnimationTimer championAnimation;
    private int goal;
    private String name1;
    private String name2;
    private int score1 = 0;
    private int score2 = 0;
    private Scoreboard scoreboard;
    private CollisionSystem collision_sys;
    private Stage primaryStage;
    private RacquetBot bot;
    private RacquetPlayer player1, player2;

    /**
     * Constructs a Champion object with the specified goal score, player names, scoreboard, collision system, and primary stage.
     * @param goal The score that a player needs to reach in order to win the game.
     * @param name1 The name of player 1.
     * @param name2 The name of player 2.
     * @param scoreboard The scoreboard object that keeps track of the scores.
     * @param collision_sys The collision system object that handles the game physics.
     * @param primaryStage The primary stage of the JavaFX application.
     */
    public Champion(int goal, RacquetPlayer player1, String name1, RacquetPlayer player2, String name2, Scoreboard scoreboard, CollisionSystem collision_sys, RacquetBot bot, Stage primaryStage) {
        this.goal = goal;
        this.name1 = name1;
        this.name2 = name2;
        this.scoreboard = scoreboard;
        this.collision_sys = collision_sys;
        this.primaryStage = primaryStage;
        this.player1 = player1;
        this.player2 = player2;
        this.bot = bot;
    }


    /**
     * Starts the AnimationTimer to determine the winner of the game.
     */
    public void findChampion(){
        championAnimation = new AnimationTimer(){
            @Override
            public void handle(long now) {

                score1 = scoreboard.getScore1();
                score2 = scoreboard.getScore2();

                if(score1 >= goal){
                    collision_sys.stopInertia();

                    if(bot != null)
                        bot.stopBot();

                    if(player2 != null)
                        player2.stopPlayer();

                    player1.stopPlayer();

                    showWinnerPopup(name1);
                    championAnimation.stop();
                }

                if(score2 >= goal){
                    collision_sys.stopInertia();

                    if(bot != null)
                        bot.stopBot();

                    if(player2 != null)
                        player2.stopPlayer();

                    player1.stopPlayer();

                    showWinnerPopup(name2);
                    championAnimation.stop();
                }
            }
        };

        championAnimation.start();
    }


    private void showWinnerPopup(String winner){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Game-Over");
            alert.setHeaderText(null);
            alert.setContentText(winner + " wins!");


            //add icon
            ImageView trophyImageView = new ImageView(new Image( getClass().getResourceAsStream( "/com/pong/pingpong/icons/trophy2.png" ) ));
            trophyImageView.setFitWidth(60);
            trophyImageView.setFitHeight(60);
            alert.setGraphic(trophyImageView);

            //define o ícone personalizado usando CSS
            DialogPane dialog_pane = alert.getDialogPane();
            dialog_pane.getStylesheets().add(
                getClass().getResource("/com/pong/pingpong/css/custom-alert.css").toExternalForm()
            );
            dialog_pane.getStyleClass().add("winner");

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().setAll(okButton);

            Button okBtn = (Button) alert.getDialogPane().lookupButton(okButton);
            okBtn.setOnAction(event -> {
                PongFX return_main = new PongFX();
                return_main.start(primaryStage);
                alert.close();
            });

            //adicionar ouvinte ao evento de fechamento do Alert
            alert.setOnHidden(event -> {
                PongFX return_main = new PongFX();
                return_main.start(primaryStage);
            });

            alert.showAndWait();
        });
    }

}
