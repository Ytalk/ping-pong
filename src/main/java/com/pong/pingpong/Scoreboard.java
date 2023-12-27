package com.pong.pingpong;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import  javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Scoreboard{

    private HBox scoreboard_panel;
    private int score1 = 0, score2 = 0;
    private Label name1, x, name2;
    private TextField score1_field, score2_field;
    private boolean pointScored = false;
    private double scene_width;


    public Scoreboard(String name1, String name2){


        scoreboard_panel = new HBox();

        this.name1 = new Label(name1);
        this.name1.setFont(new Font("Arial", 25));
        this.name1.setTextFill(Color.BLACK);
        //this.name1.setStyle("-fx-border-color:black;");

        this.name2 = new Label(name2);
        this.name2.setFont(new Font("Arial", 25));
        this.name2.setTextFill(Color.BLACK);

        x = new Label("X");
        x.setFont(new Font("Arial", 20));
        x.setTextFill(Color.BLACK);


        score1_field = new TextField( String.valueOf(score1) );
        score1_field.setPrefWidth(42);
        //this.score1_field.setPrefHeight(100);
        score1_field.setAlignment(Pos.CENTER);
        score1_field.setEditable(false);

        score2_field = new TextField( String.valueOf(score2) );
        score2_field.setPrefWidth(42);
        score2_field.setAlignment(Pos.CENTER);
        score2_field.setEditable(false);

        scoreboard_panel.setSpacing(30);
        scoreboard_panel.setAlignment(Pos.CENTER);
        scoreboard_panel.setBackground(  new Background( new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY) )  );
        scoreboard_panel.getChildren().addAll(this.name1, score1_field, x, score2_field, this.name2);
    }


    public void setSceneWidth(double s_width){
        this.scene_width = s_width;
    }


    public void setScore(Ball ball, Racquet control1, Racquet control2){
        double ballMinX = ball.getBall().getBoundsInParent().getMinX();
        double ballMaxX = ball.getBall().getBoundsInParent().getMaxX();

        if( (ballMinX < 0) && (!pointScored) ){
            System.out.println("cena: " + scene_width + "min: " + ballMinX);

            score2_field.setText( String.valueOf(++score2) );
            control1.setServe(true);

            pointScored = true;
        }

        else if( (ballMaxX > scene_width) && (!pointScored) ){
            System.out.println("cena: " + scene_width + "max: " + ballMaxX);

            score1_field.setText( String.valueOf(++score1) );
            control2.setServe2(true);

            pointScored = true;
        }

        else if( (ballMinX >= 0) && (ballMaxX <= scene_width) ){
            System.out.println("controller");
            pointScored = false;
        }

    }


    public HBox getScoreboard(){
        return scoreboard_panel;
    }


}