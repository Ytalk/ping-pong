package com.pong.pingpong;

import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import  javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;


public class ControlsScene{

    //NO CONSTRUCTOR

    private Label control1, control2;
    private Label Q, A, Z;
    private Label O, K, M;

    public void controlsScene(Stage primaryStage, int window_height, int window_width){

        control1 = new Label("CONTROL 1");
        control1.setFont(new Font("fantasy", 25));
        control1.setTextFill(Color.WHITE);
        control1.setStyle("-fx-border-color:black; -fx-background-color: blue; -fx-border-width: 2;");

        Q = new Label("Q   -   up");
        Q.setFont(new Font("Arial", 20));
        Q.setTextFill(Color.WHITE);

        A = new Label("A   -   serve");
        A.setFont(new Font("Arial", 20));
        A.setTextFill(Color.WHITE);

        Z = new Label("Z   -   down");
        Z.setFont(new Font("Arial", 20));
        Z.setTextFill(Color.WHITE);

        VBox control1_panel = new VBox(30);
        control1_panel.setAlignment(Pos.CENTER);
        control1_panel.getChildren().addAll(control1, Q, A, Z);



        control2 = new Label("CONTROL 2");
        control2.setFont(new Font("fantasy", 25));
        control2.setTextFill(Color.WHITE);
        control2.setStyle("-fx-border-color:black; -fx-background-color: blue; -fx-border-width: 2;");

        O = new Label("O   -   up");
        O.setFont(new Font("Arial", 20));
        O.setTextFill(Color.WHITE);

        K = new Label("K   -   serve");
        K.setFont(new Font("Arial", 20));
        K.setTextFill(Color.WHITE);

        M = new Label("M   -   down");
        M.setFont(new Font("Arial", 20));
        M.setTextFill(Color.WHITE);

        VBox control2_panel = new VBox(30);
        control2_panel.setAlignment(Pos.CENTER);
        control2_panel.getChildren().addAll(control2, O, K, M);



        Button back_button = new Button("BACK");

        back_button.setStyle(
            "-fx-background-radius: 0 0 10 0;" +
            "-fx-padding: 5;" +
            "-fx-font-family: 'Arial'; -fx-font-size: 12;" +
            "-fx-text-fill: black;" +
            "-fx-background-color: #0000FF;" +
            "-fx-min-width: 100;" +
            "-fx-min-height: 30;"
        );

        back_button.setOnAction(e -> {
            PongFX return_main = new PongFX();
            return_main.start(primaryStage);
        });



        HBox controls_panel = new HBox(120);
        controls_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );
        controls_panel.getChildren().addAll(back_button, control1_panel, control2_panel);



        Scene controls_scene = new Scene( controls_panel, window_width, window_height );//retirado o tamanho.
        primaryStage.setScene(controls_scene);
    }

}