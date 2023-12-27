package com.pong.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import java.util.Set;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;


public class PongFX extends Application{

    public static void main(String[] args){
        launch(args);
    }


    //GLOBAL VARIABLES
    int window_height = 480;
    int window_width = 854;

    @Override
    public void start(Stage primaryStage){

        Button play_button = new Button("Play PvP");
        play_button.setOnAction( e -> play_scene(primaryStage) );

        Button exit_button = new Button("Exit");
        exit_button.setOnAction( e -> primaryStage.close() );


        VBox menu_panel = new VBox(20);
        menu_panel.setAlignment(Pos.CENTER);
        menu_panel.getChildren().addAll( play_button, exit_button );
        menu_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );

        Scene menu_scene = new Scene(menu_panel, window_width, window_height);


        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(menu_scene);
        primaryStage.show();
    }



    private void play_scene(Stage primaryStage){

        Pane play_panel = new Pane();
        play_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );


        RacquetPlayer player = new RacquetPlayer("Player 1", 10, true);
        play_panel.getChildren().add( player.getRacquet() );

        RacquetPlayer player2 = new RacquetPlayer("Player 2", 834, false);
        play_panel.getChildren().add( player2.getRacquet() );

        Wall upper_wall = new Wall(0, window_width);
        play_panel.getChildren().add( upper_wall.getWall() );

        Wall lower_wall = new Wall(470, window_width);
        play_panel.getChildren().add( lower_wall.getWall() );

        Ball ball = new Ball(400, 200, 10);
        play_panel.getChildren().add(ball.getBall());


        Scoreboard scoreboard = new Scoreboard( player.getName(), player2.getName() );

        Scene pong_scene = new Scene( new VBox(scoreboard.getScoreboard(), play_panel) );//retirado o tamanho.

        scoreboard.setSceneWidth(window_width);


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
        collision.inertia(scoreboard, ball);//comportamento da bola, colisões e movimento


        primaryStage.setScene(pong_scene);
        play_panel.requestFocus();//nó recebe eventos do teclado focado

    }

}