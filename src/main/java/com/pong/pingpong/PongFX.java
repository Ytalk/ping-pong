package com.pong.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;

public class PongFX extends Application{
    public static void main(String[] args){
        launch(args);
    }


    //GLOBAL VARIABLES
    int window_height = 480;
    int window_width = 854;


    @Override
    public void start(Stage primaryStage){

        Button play_button = new Button("PLay");
        Button exit_button = new Button("Exit");

        play_button.setOnAction(e -> play_scene(primaryStage) );

        exit_button.setOnAction(e -> primaryStage.close() );


        VBox menu_panel = new VBox(20);
        menu_panel.setAlignment(Pos.CENTER);
        menu_panel.getChildren().addAll(play_button, exit_button);
        menu_panel.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(menu_panel, window_width, window_height);


        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void play_scene(Stage primaryStage){

        Pane play_panel = new Pane();
        play_panel.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene pong_scene = new Scene(play_panel, window_width, window_height);


        RacquetPlayer player = new RacquetPlayer("Player", 10);
        play_panel.getChildren().add(player.getRacquet());

        Wall upper_wall = new Wall(50, window_width);
        play_panel.getChildren().add(upper_wall.getWall());

        Wall lower_wall = new Wall(470, window_width);
        play_panel.getChildren().add(lower_wall.getWall());

        Ball ball = new Ball(400, 200, 10);
        play_panel.getChildren().add(ball.getBall());


        pong_scene.setOnKeyPressed(event -> player.move( event.getCode() ) );//jogador se movimenta


        collisionSystem collision = new collisionSystem(ball, lower_wall, upper_wall, player);
        collision.inertia();//comportamento da bola, colisões e movimento


        primaryStage.setScene(pong_scene);


        play_panel.requestFocus();//nó recebe eventos do teclado focado

    }

}