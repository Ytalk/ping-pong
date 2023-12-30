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
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;


public class MatchInterfaceScene{

    //NO CONSTRUCTOR


    public void matchInterfaceScene(Stage primaryStage, int window_height, int window_width){


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



        //PANEL FOR NAMES
        Label name1_label = new Label("Name P1");
        name1_label.setFont(new Font("Arial", 25));
        name1_label.setTextFill(Color.WHITE);

        TextField name1_field = new TextField();
        name1_field.setPrefWidth(150);
        //this.score1_field.setPrefHeight(100);


        Label name2_label = new Label("Name P2");
        name2_label.setFont(new Font("Arial", 25));
        name2_label.setTextFill(Color.WHITE);

        TextField name2_field = new TextField();
        name2_field.setPrefWidth(150);


        VBox names_panel = new VBox(30);
        names_panel.setAlignment(Pos.CENTER);
        names_panel.getChildren().addAll(name1_label, name1_field, name2_label, name2_field);



        //PANEL TO SELECT OPPONENT AND GOAL POINTS
        Label points_label = new Label("Score to Win");
        points_label.setFont(new Font("Arial", 25));
        points_label.setTextFill(Color.WHITE);

        TextField points_field = new TextField();
        points_field.setPrefWidth(150);
        points_field.setAlignment(Pos.CENTER);


        Label mode_label = new Label("Mode");
        mode_label.setFont(new Font("Arial", 25));
        mode_label.setTextFill(Color.WHITE);

        ChoiceBox<String> mode_chbox = new ChoiceBox<>(FXCollections.observableArrayList(
                "PvP",
                "bot"
        ));

        mode_chbox.setPrefWidth(150);

        //Inicialmente, esconde os elementos
        name2_label.setVisible(false);
        name2_field.setVisible(false);

        mode_chbox.setOnAction(event -> {
            String selectedOption = mode_chbox.getValue();
        });

        mode_chbox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if ("PvP".equals(newValue)) {
                name2_label.setVisible(true);
                name2_field.setVisible(true);
            }
            else {
                name2_label.setVisible(false);
                name2_field.setVisible(false);
            }
        });


        VBox pointsmode_panel = new VBox(30);
        pointsmode_panel.setAlignment(Pos.CENTER);
        pointsmode_panel.getChildren().addAll(points_label, points_field, mode_label, mode_chbox);



        //CONFIRM OR HANDLE ERRORS
        Button confirm_button = new Button("CONFIRM");
        confirm_button.setStyle(
            "-fx-background-radius: 10 0 0 0;" +
            "-fx-padding: 5;" +
            "-fx-font-family: 'Arial'; -fx-font-size: 12;" +
            "-fx-text-fill: black;" +
            "-fx-background-color: #0000FF;" +
            "-fx-min-width: 100;" +
            "-fx-min-height: 30;"
        );

        confirm_button.setOnAction(e -> {
            try{
                if(points_field.getText().isEmpty()){
                    throw new NumberFormatException("objective score not reported!");
                }
                else if(!points_field.getText().matches("-?\\d+")){
                    throw new NumberFormatException("objective score cannot contain letters!");
                }
                else if (Integer.parseInt(points_field.getText()) <= 0){
                    throw new NumberFormatException("objective score cannot be below 1!");
                }

                else{
                    int goal = Integer.parseInt(points_field.getText());

                    LocalPvPScene local_pvp = new LocalPvPScene();
                    local_pvp.localPvpScene(primaryStage, window_height, window_width, name1_field.getText(), name2_field.getText(), goal);
                }
            }
            catch(NumberFormatException ex){
                ex.showMessage();
            }
        });

        VBox confirm_panel = new VBox();
        confirm_panel.setAlignment(Pos.BOTTOM_RIGHT);
        confirm_panel.getChildren().add(confirm_button);



        //MI SCENE
        HBox mi_panel = new HBox(120);
        mi_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );
        mi_panel.getChildren().addAll(back_button, names_panel, pointsmode_panel, confirm_panel);

        Scene mi_scene = new Scene( mi_panel, window_width, window_height );
        primaryStage.setScene(mi_scene);
    }
}


