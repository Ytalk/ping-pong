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

    public void matchInterfaceScene(Stage primaryStage, int window_height, int window_width){

        //RETURN TO MENU
        Button back_button = new Button("BACK");
        back_button.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15;");
        back_button.setStyle("-fx-text-fill: black;");

        CornerRadii cr = new CornerRadii(15);
        back_button.setBackground(new Background(new BackgroundFill(Color.BLUE, cr, javafx.geometry.Insets.EMPTY)));
        back_button.setPrefSize(100, 30);

        back_button.setOnAction(e -> {
            PongFX return_main = new PongFX();
            return_main.start(primaryStage);
        });



        //PANEL FOR NAMES
        Label name1_label = new Label("name P1");
        name1_label.setFont(new Font("Arial", 25));
        name1_label.setTextFill(Color.WHITE);
        //control1.setStyle("-fx-border-color:black; -fx-background-color: blue; -fx-border-width: 2;");

        TextField name1_field = new TextField(  );
        name1_field.setPrefWidth(150);
        //this.score1_field.setPrefHeight(100);


        Label name2_label = new Label("name P2");
        name2_label.setFont(new Font("Arial", 25));
        name2_label.setTextFill(Color.WHITE);

        TextField name2_field = new TextField(  );
        name2_field.setPrefWidth(150);


        VBox names_panel = new VBox(30);
        names_panel.setAlignment(Pos.CENTER);
        names_panel.getChildren().addAll(name1_label, name1_field, name2_label, name2_field);



        //PANEL FOR OPPONENT AND GOAL POINTS
        Label points_label = new Label("points to win");
        points_label.setFont(new Font("Arial", 25));
        points_label.setTextFill(Color.WHITE);

        TextField points_field = new TextField(  );
        points_field.setPrefWidth(150);
        points_field.setAlignment(Pos.CENTER);


        Label mode_label = new Label("mode");
        mode_label.setFont(new Font("Arial", 25));
        mode_label.setTextFill(Color.WHITE);

        ChoiceBox<String> mode_chbox = new ChoiceBox<>(FXCollections.observableArrayList(
                "PvP",
                "bot"
        ));

        mode_chbox.setPrefWidth(150);
        mode_chbox.setStyle("-fx-font-size: 14px; -fx-background-color: #ececec; -fx-alignment: CENTER;");

        // Inicialmente, esconde os elementos
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



        //
        Button confirm_button = new Button("CONFIRM");
        confirm_button.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 15;");
        confirm_button.setStyle("-fx-text-fill: black;");

        CornerRadii cr2 = new CornerRadii(15);
        confirm_button.setBackground(new Background(new BackgroundFill(Color.BLUE, cr2, javafx.geometry.Insets.EMPTY)));
        confirm_button.setPrefSize(100, 30);

        confirm_button.setOnAction(e -> {
            LocalPvPScene local_pvp = new LocalPvPScene();
            int goal = Integer.parseInt(points_field.getText());
            local_pvp.localPvpScene(primaryStage, window_height, window_width, name1_field.getText(), name2_field.getText(), goal);
        });

        back_button.setOnAction(e -> {
            PongFX return_main = new PongFX();
            return_main.start(primaryStage);
        });

        VBox confirm_panel = new VBox();
        confirm_panel.setAlignment(Pos.BOTTOM_RIGHT);
        confirm_panel.getChildren().add(confirm_button);



        //MI SCENE
        HBox interface_panel = new HBox(120);
        //controls_panel.setAlignment(Pos.CENTER);
        interface_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );
        interface_panel.getChildren().addAll(back_button, names_panel, pointsmode_panel, confirm_panel);

        Scene mi_scene = new Scene( interface_panel, window_width, window_height );
        primaryStage.setScene(mi_scene);
    }
}


