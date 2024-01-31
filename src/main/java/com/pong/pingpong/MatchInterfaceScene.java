package com.pong.pingpong;

import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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


import com.jfoenix.controls.JFXTextField;

/**
 * The `MatchInterfaceScene` class is responsible for creating a scene where the user can enter player names, select a game mode, and set the goal score.
 * It also handles error checking and confirmation of the selected options.
 *
 * Example Usage:
 * MatchInterfaceScene mi = new MatchInterfaceScene();
 * mi.matchInterfaceScene(primaryStage, window_height, window_width);
 *
 * Code Analysis:
 * Main functionalities:
 * - Display a scene with input fields for player names, a choice box for game mode, and a text field for the goal score.
 * - Show or hide the second player name input field based on the selected game mode.
 * - Handle the confirmation button click event and perform error checking on the input fields.
 * - Create a new instance of the `LocalPvPScene` class and pass the selected options to start the game.
 *
 * Methods:
 * - `matchInterfaceScene(Stage primaryStage, int window_height, int window_width)`: Creates and displays the match interface scene with input fields for player names, a choice box for game mode, and a text field for the goal score.
 * Handles the confirmation button click event and performs error checking on the input fields.
 * Creates a new instance of the `LocalPvPScene` class and passes the selected options to start the game.
 *
 * Fields:
 * - None.
 */
public class MatchInterfaceScene{

    //NO CONSTRUCTOR

    public void matchInterfaceScene(Stage primaryStage, int window_height, int window_width, Color bgcolor){


        Button back_button = new Button("BACK");
        back_button.getStyleClass().add("back-button");

        back_button.setOnAction(e -> {
            PongFX return_main = new PongFX();
            return_main.start(primaryStage);
        });



        //PANEL FOR NAMES
        Label name1_label = new Label("Name P1");
        name1_label.setFont(new Font("Arial", 25));
        name1_label.setTextFill(Color.WHITE);

        TextField name1_field = new TextField();
        name1_field.setPromptText("Nickname here");
        name1_field.getStyleClass().add("glamour-field");


        Label name2_label = new Label("Name P2");
        name2_label.setFont(new Font("Arial", 25));
        name2_label.setTextFill(Color.WHITE);

        TextField name2_field = new TextField();
        name2_field.setPromptText("Nickname here");
        name2_field.getStyleClass().add("glamour-field");


        VBox names_panel = new VBox(30);
        names_panel.setAlignment(Pos.CENTER);
        names_panel.getChildren().addAll(name1_label, name1_field, name2_label, name2_field);



        //PANEL TO SELECT OPPONENT AND GOAL POINTS
        Label points_label = new Label("Score to Win");
        points_label.setFont(new Font("Arial", 25));
        points_label.setTextFill(Color.WHITE);

        TextField points_field = new TextField();
        points_field.setPromptText("Score here");
        points_field.getStyleClass().add("glamour-field");
        points_field.setAlignment(Pos.CENTER);


        Label mode_label = new Label("Mode");
        mode_label.setFont(new Font("Arial", 25));
        mode_label.setTextFill(Color.WHITE);

        ChoiceBox<String> mode_chbox = new ChoiceBox<>(FXCollections.observableArrayList(
                "PvP",
                "bot"
        ));
        mode_chbox.getStyleClass().add("choice-box");

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
        confirm_button.getStyleClass().add("confirm-button");

        confirm_button.setOnAction(e -> {
            try{
                if(points_field.getText().isEmpty()){
                    throw new MatchInterfaceException("objective score not reported!", "OBJECTIVE SCORE ERROR");
                }
                else if(!points_field.getText().matches("-?\\d+")){
                    throw new MatchInterfaceException("objective score cannot contain letters!", "OBJECTIVE SCORE ERROR");
                }
                else if(Integer.parseInt(points_field.getText()) <= 0){
                    throw new MatchInterfaceException("objective score cannot be below 1!", "OBJECTIVE SCORE ERROR");
                }
                else if(  (name1_field.getText().isEmpty())  ||  ( name1_field.getText().length() >= 15 )  ){
                    throw new MatchInterfaceException("the name must contain between 1 and 15 characters!", "NAME ERROR");
                }

                else {
                    int goal = Integer.parseInt(points_field.getText());

                    if( "PvP".equals(mode_chbox.getValue()) ){
                        if(  (name2_field.getText().isEmpty())  ||  ( name2_field.getText().length() >= 15 )  ){
                            throw new MatchInterfaceException("the name must contain between 1 and 15 characters!", "NAME ERROR");
                        }

                        LocalPvPScene local_pvp = new LocalPvPScene();
                        local_pvp.localPvpScene(primaryStage, window_height, window_width, bgcolor, name1_field.getText(), name2_field.getText(), goal);
                    }
                    else if( "bot".equals(mode_chbox.getValue()) ){
                        LocalPvBScene local_pvb = new LocalPvBScene();
                        local_pvb.localPvbScene(primaryStage, window_height, window_width, bgcolor, name1_field.getText(), goal);
                    }
                    else{
                        throw new MatchInterfaceException("no mode has been selected!", "MODE ERROR");
                    }
                }

            }
            catch(MatchInterfaceException ex){
                ex.showMessage();
            }
        });

        VBox confirm_panel = new VBox();
        confirm_panel.setAlignment(Pos.BOTTOM_RIGHT);
        confirm_panel.getChildren().add(confirm_button);



        //MI SCENE
        HBox mi_panel = new HBox(120);
        mi_panel.setBackground(  new Background( new BackgroundFill(bgcolor, CornerRadii.EMPTY, Insets.EMPTY) )  );
        mi_panel.getChildren().addAll(back_button, names_panel, pointsmode_panel, confirm_panel);

        Scene mi_scene = new Scene( mi_panel, window_width, window_height );
        mi_scene.getStylesheets().add(getClass().getResource("/com/pong/pingpong/css/half-leaf-button.css").toExternalForm());
        mi_scene.getStylesheets().add(getClass().getResource("/com/pong/pingpong/css/modern-field.css").toExternalForm());
        mi_scene.getStylesheets().add(getClass().getResource("/com/pong/pingpong/css/modern-choice-box.css").toExternalForm());
        primaryStage.setScene(mi_scene);
    }
}


