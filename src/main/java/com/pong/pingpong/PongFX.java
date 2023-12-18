package com.pong.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class PongFX extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        int window_height = 480;
        int window_width = 854;

        Pane root = new Pane();


        Racquet player = new RacquetPlayer("Player", 10);
        root.getChildren().add(player.getRacquet());

        Wall upper_wall = new Wall(50, window_width);
        root.getChildren().add(upper_wall.getWall());

        Wall lower_wall = new Wall(470, window_width);
        root.getChildren().add(lower_wall.getWall());


        Scene scene = new Scene(root, window_width, window_height, Color.DIMGRAY);


        scene.setOnKeyPressed(event -> player.move( event.getCode() ) );


        root.requestFocus();//nó StackPane recebe eventos do teclado


        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}