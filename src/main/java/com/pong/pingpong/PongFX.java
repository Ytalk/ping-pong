package com.pong.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
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
        StackPane root = new StackPane();


        Racquet player = new RacquetPlayer("Player", 100);
        root.getChildren().add(player.getRacquet());


        root.setOnKeyPressed(event -> {
            switch (event.getCode()) {

                case Q:
                    player.moveUp();
                break;

                case Z:
                    player.moveDown();
                break;
            }
        });

        Scene scene = new Scene(root, 854, 480);
        primaryStage.setTitle("Pong FX");
        primaryStage.setScene(scene);

        //nó StackPane recebe eventos do teclado
        root.requestFocus();

        primaryStage.show();
    }

}