package com.pong.pingpong;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import java.util.Set;
import javafx.scene.input.KeyCode;
import java.util.HashSet;

/**
 * The LocalPvPScene class represents a scene for a local player versus player (PvP) game.
 * It sets up the game environment, including the players, walls, ball, and scoreboard.
 * It also handles keyboard input for the players and manages the collision system and scoring.
 */
public class LocalPvPScene{

    //NO CONSTRUCTOR

    /**
     * Sets up the game environment for a local PvP game.
     * Creates the players, walls, ball, and scoreboard.
     * Handles keyboard input for the players.
     * Manages the collision system and scoring.
     * Binds the scene to the primaryStage.
     * 
     * @param primaryStage the primary stage of the JavaFX application
     * @param window_height the height of the game window
     * @param window_width the width of the game window
     * @param name1 the name of player 1
     * @param name2 the name of player 2
     * @param goal the score needed to win the match
     */
    public void localPvpScene(Stage primaryStage, int window_height, int window_width, String name1, String name2, int goal){

        Pane play_panel = new Pane();
        play_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );


        RacquetPlayer player = new RacquetPlayer(name1, 10, true);
        play_panel.getChildren().add( player.getRacquet() );
        player.setServe(true);//o controle1 tem o privilégio de começar sacando

        RacquetPlayer player2 = new RacquetPlayer(name2, 834, false);
        play_panel.getChildren().add( player2.getRacquet() );

        Wall upper_wall = new Wall(0, window_width);
        play_panel.getChildren().add( upper_wall.getWall() );

        Wall lower_wall = new Wall(440, window_width);
        play_panel.getChildren().add( lower_wall.getWall() );

        Ball ball = new Ball(400, 200, 10);
        play_panel.getChildren().add( ball.getBall() );


        Scoreboard scoreboard = new Scoreboard( player.getName(), player2.getName(), window_width );


        Scene pong_scene = new Scene( new VBox(scoreboard.getScoreboard(), play_panel), window_width, window_height );


        //gerencia entrada de teclado para jogadores
        Set<KeyCode> keysPressed = new HashSet<>();

        pong_scene.setOnKeyPressed(event -> {
            keysPressed.add(event.getCode());
            player.handleKeyPressed(keysPressed, ball);
            player2.handleKeyPressed(keysPressed, ball);
        });

        pong_scene.setOnKeyReleased(event -> {
            keysPressed.remove(event.getCode());
            player.handleKeyReleased(keysPressed, event);
            player2.handleKeyReleased(keysPressed, event);
        });


        CollisionSystem collision = new CollisionSystem(lower_wall, upper_wall, player, player2);
        collision.inertia(scoreboard, ball);//timer para comportamento da bola, colisões, movimento, saques e pontuações


        Champion champ = new Champion(goal, player.getName(), player2.getName(), scoreboard, collision, null, primaryStage);
        champ.findChampion();//timer para analisar se atingiu o objetivo


        primaryStage.setScene(pong_scene);
        play_panel.requestFocus();//nó recebe eventos do teclado focado

    }


}