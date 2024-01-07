package com.pong.pingpong;

import javafx.scene.control.Label;
import javafx.scene.Scene;
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


/**
 * The ControlsScene class is responsible for creating a scene that displays the controls for the game.
 * It includes a back button to return to the main menu and two sets of controls, each with labels for the keys.
 */
public class ControlsScene{

    private Label control1, control2;
    private Label Q, A, Z;
    private Label O, K, M;

    /**
     * This method creates the controls scene by setting up the labels for the keys with their corresponding actions,
     * as well as the back button. It also sets the background color and size of the scene.
     * 
     * @param primaryStage the primary stage of the JavaFX application
     * @param window_height the height of the window
     * @param window_width the width of the window
     */
    public void controlsScene(Stage primaryStage, int window_height, int window_width){

        control1 = new Label("CONTROL 1");
        control1.setFont(new Font("fantasy", 23));
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
        control2.setFont(new Font("fantasy", 23));
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
        back_button.getStyleClass().add("back-button");

        back_button.setOnAction(e -> {
            PongFX return_main = new PongFX();
            return_main.start(primaryStage);
        });



        Label tip_label = new Label("Tip: Clicking a\nmovement button\nsimultaneously with\n the serve will\ndirect the ball.");
        tip_label.setFont(new Font("Times New Roman", 15));
        tip_label.setTextFill(Color.WHITE);
        tip_label.getStyleClass().add("tip-mon");

        VBox tip_panel = new VBox();
        tip_panel.setAlignment(Pos.BOTTOM_RIGHT);
        tip_panel.getChildren().add(tip_label);



        HBox controls_panel = new HBox(125);
        controls_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );
        controls_panel.getChildren().addAll(back_button, control1_panel, control2_panel, tip_panel);



        Scene controls_scene = new Scene( controls_panel, window_width, window_height );//retirado o tamanho.
        controls_scene.getStylesheets().add(getClass().getResource("/com/pong/pingpong/css/half-leaf-button.css").toExternalForm());
        primaryStage.setScene(controls_scene);
    }

}