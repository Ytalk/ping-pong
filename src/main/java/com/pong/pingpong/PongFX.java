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
import com.jfoenix.controls.JFXButton;

/**
 * The PongFX class is the main class of the PongFX application. It extends the Application class and is responsible for starting the application, creating the main menu scene, and handling button actions.
 */
public class PongFX extends Application{

    /**
     * The entry point of the application. It calls the launch method to start the JavaFX application.
     * @param args The command line arguments
     */
    public static void main(String[] args){
        launch(args);
    }


    int window_height = 480;
    int window_width = 854;

    /**
     * Overrides the start method of the Application class. It creates the main menu scene and sets it as the primary stage's scene.
     * @param primaryStage The primary stage of the application
     */
    @Override
    public void start(Stage primaryStage){

        ImageView logo = new ImageView(  new Image( getClass().getResourceAsStream("/com/pong/pingpong/icons/bgpfxlogo.png") )  );
        logo.setFitHeight(200);
        logo.setFitWidth(200);


        JFXButton play_button = new JFXButton("Play Local");
        play_button.getStyleClass().add("menu-jfx-button");
        play_button.setOnAction(e -> {
            MatchInterfaceScene mi = new MatchInterfaceScene();
            mi.matchInterfaceScene(primaryStage, window_height, window_width);
        });


        JFXButton controls_button = new JFXButton("Controls");
        controls_button.getStyleClass().add("menu-jfx-button");
        controls_button.setOnAction(e -> {
            ControlsScene controls = new ControlsScene();
            controls.controlsScene(primaryStage, window_height, window_width);
        });


        JFXButton exit_button = new JFXButton("Exit");
        exit_button.getStyleClass().add("menu-jfx-button");
        exit_button.setOnAction( e -> primaryStage.close() );


        VBox menu_panel = new VBox(20);
        menu_panel.setAlignment(Pos.CENTER);
        menu_panel.getChildren().addAll( logo, play_button, controls_button, exit_button );
        menu_panel.setBackground(  new Background( new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY) )  );


        Scene menu_scene = new Scene(menu_panel, window_width, window_height);
        menu_scene.getStylesheets().add(getClass().getResource("/com/pong/pingpong/css/oval-button.css").toExternalForm());
        primaryStage.setTitle("Pong FX");
        primaryStage.getIcons().add(  new Image( getClass().getResourceAsStream("/com/pong/pingpong/icons/bgpfxlogo.png") )  );
        primaryStage.setScene(menu_scene);
        primaryStage.show();
    }

}