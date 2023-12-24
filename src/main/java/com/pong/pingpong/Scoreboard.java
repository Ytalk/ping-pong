package com.pong.pingpong;

import javafx.scene.layout.Pane;
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

import javafx.scene.shape.Circle;



public class Scoreboard{

    private HBox scoreboard_panel;
    private int score1=0, score2=0;
    private Label name1, name2, x;
    private TextField score1_field, score2_field;

    private boolean pointScored = false;

    private double scene_width;
    private Ball ball;


    public Scoreboard(String name1, String name2, Ball ball){

        this.ball = ball;

        scoreboard_panel = new HBox();

        this.name1 = new Label(name1);
        this.name1.setFont(new Font("Arial", 25));
        //this.name1.setStyle("-fx-border-color:black;");

        this.name2 = new Label(name2);
        this.name2.setFont(new Font("Arial", 25));

        x = new Label("X");
        x.setFont(new Font("Arial", 20));

        this.name1.relocate(10, 0);
        this.name2.relocate(700, 0);

        score1_field = new TextField( String.valueOf(score1) );
        score1_field.setLayoutX(100);
        //this.score1_field.setLayoutY(0);
        score1_field.setPrefWidth(42);
        //this.score1_field.setPrefHeight(10);
        score1_field.setAlignment(Pos.CENTER);
        score1_field.setEditable(false);

        score2_field = new TextField( String.valueOf(score2) );
        score2_field.setLayoutX(400);
        score2_field.setPrefWidth(42);
        score2_field.setAlignment(Pos.CENTER);
        score2_field.setEditable(false);

        scoreboard_panel.setSpacing(30);
        scoreboard_panel.setAlignment(Pos.CENTER);
        scoreboard_panel.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        scoreboard_panel.getChildren().addAll(this.name1, score1_field, x, score2_field, this.name2);
    }


    public void setSceneWidth(double s_width){
        this.scene_width = s_width;
    }


    public void setScore(){
        double ballMinX = ball.getBall().getBoundsInParent().getMinX();
        double ballMaxX = ball.getBall().getBoundsInParent().getMaxX();

        if (ballMinX < 0 && !pointScored) {
            System.out.println("cena: " + scene_width + "min: " + ballMinX);

            score2_field.setText(String.valueOf(++score2));
            ball.draw();
            pointScored = true;
        }
        else if (ballMaxX > scene_width && !pointScored){
            System.out.println("cena: " + scene_width + "max: " + ballMaxX);

            score1_field.setText(String.valueOf(++score1));
            ball.draw();
            pointScored = true;
        }
        else if (ballMinX >= 0 && ballMaxX <= scene_width){
            System.out.println("controller");
            pointScored = false;
        }
    }


    public Pane getScoreboard(){
        return scoreboard_panel;
    }


}