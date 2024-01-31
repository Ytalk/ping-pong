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


public class LocalPvBScene{

    //NO CONSTRUCTOR

    public void localPvbScene(Stage primaryStage, int window_height, int window_width, Color bgcolor, String name, int goal){

        Pane play_panel = new Pane();
        play_panel.setBackground(  new Background( new BackgroundFill(bgcolor, CornerRadii.EMPTY, Insets.EMPTY) )  );


        RacquetPlayer player = new RacquetPlayer(name, 10, true);
        play_panel.getChildren().add( player.getRacquet() );
        player.setServe(true);//control 1 has the privilege of starting by serving

        RacquetBot bot = new RacquetBot("Pingus T.", 834);
        play_panel.getChildren().add( bot.getRacquet() );

        Wall upper_wall = new Wall(0, window_width);
        play_panel.getChildren().add( upper_wall.getWall() );

        Wall lower_wall = new Wall(445, window_width);
        play_panel.getChildren().add( lower_wall.getWall() );

        Ball ball = new Ball();
        play_panel.getChildren().add( ball.getBall() );


        Scoreboard scoreboard = new Scoreboard( player.getName(), bot.getName(), window_width );


        Scene pong_scene = new Scene( new VBox(scoreboard.getScoreboard(), play_panel), window_width, window_height );


        //manages keyboard input to the player
        Set<KeyCode> keysPressed = new HashSet<>();

        pong_scene.setOnKeyPressed(event -> {
            keysPressed.add(event.getCode());
        });

        pong_scene.setOnKeyReleased(event -> {
            player.handleKeyReleased(keysPressed, event);
        });

        player.Move(keysPressed, ball);


        CollisionSystem collision = new CollisionSystem(lower_wall, upper_wall, player, bot);
        collision.inertia(scoreboard, ball);//timer for ball behavior, collisions, movement, serves, and scores


        bot.Move(ball, bot);


        Champion champ = new Champion(goal, player, player.getName(), null, bot.getName(), scoreboard, collision, bot, primaryStage);
        champ.findChampion();//timer to analyze if the goal has been achieved


        primaryStage.setScene(pong_scene);
        play_panel.requestFocus();//node receives events from the focused keyboard

    }


}