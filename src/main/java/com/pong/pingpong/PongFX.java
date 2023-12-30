package com.pong.pingpong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PongFX extends Application{

    public static void main(String[] args){
        launch(args);
    }


    //GLOBAL VARIABLES
    int window_height = 480;
    int window_width = 854;

    @Override
    public void start(Stage primaryStage){

        ImageView logo = new ImageView(  new Image( getClass().getResourceAsStream("/com/pong/pingpong/icons/bgpfxlogo.png") )  );
        logo.setFitHeight(200);
        logo.setFitWidth(200);

        Button play_button = new Button("Play Local");
        MatchInterfaceScene mi = new MatchInterfaceScene();
        play_button.setPrefSize(100, 30);
        play_button.setOnAction( e -> mi.matchInterfaceScene(primaryStage, window_height, window_width) );

        Button controls_button = new Button("Controls");
        ControlsScene controls = new ControlsScene();
        controls_button.setPrefSize(100, 30);
        controls_button.setOnAction( e -> controls.controlsScene(primaryStage, window_height, window_width) );

        Button exit_button = new Button("Exit");
        exit_button.setPrefSize(100, 30);
        exit_button.setOnAction( e -> primaryStage.close() );


        VBox menu_panel = new VBox(20);
        menu_panel.setAlignment(Pos.CENTER);
        menu_panel.getChildren().addAll( logo, play_button, controls_button, exit_button);
        menu_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );


        Scene menu_scene = new Scene(menu_panel, window_width, window_height);
        primaryStage.setTitle("Pong FX");
        primaryStage.getIcons().add(  new Image( getClass().getResourceAsStream("/com/pong/pingpong/icons/bgpfxlogo.png") )  );
        primaryStage.setScene(menu_scene);
        primaryStage.show();
    }


}