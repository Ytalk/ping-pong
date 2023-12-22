package com.pong.pingpong;

import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

import java.util.Set;
import javafx.scene.input.KeyCode;
import java.util.HashSet;

public class collisionSystem{

    double deltaX = -1;  //start final
    double deltaY = 0.5;

    Ball ball;
    Wall lower_wall;
    Wall upper_wall;
    Racquet player;
    Racquet player2;

    public collisionSystem(Ball ball, Wall lower_wall, Wall upper_wall, Racquet player, Racquet player2){
        this.ball = ball;
        this.lower_wall = lower_wall;
        this.upper_wall = upper_wall;
        this.player = player;
        this.player2 = player2;
    }


    public void inertia() {

        Timeline pingpong = new Timeline(new KeyFrame(Duration.millis(20), event -> {

            if (ball.checkCollision(player.getRacquet(), ball.getBall()) || ball.checkCollision(player2.getRacquet(), ball.getBall())) {
                deltaX = deltaX * -1;
                ball.speedUp();
            }

            if (ball.checkCollision(lower_wall.getWall(), ball.getBall()) || ball.checkCollision(upper_wall.getWall(), ball.getBall())) {
                deltaY = deltaY * -1;
            }

            ball.move(deltaX, deltaY);
        }));

        pingpong.setCycleCount(Timeline.INDEFINITE);
        pingpong.play();

    }



}