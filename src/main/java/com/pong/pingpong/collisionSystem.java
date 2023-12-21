package com.pong.pingpong;

import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;

public class collisionSystem{

    double deltaX = -1;  //start final
    double deltaY = 0.5;

    Ball ball;
    Wall lower_wall;
    Wall upper_wall;
    RacquetPlayer player;

    public collisionSystem(Ball ball, Wall lower_wall, Wall upper_wall, RacquetPlayer player){
        this.ball = ball;
        this.lower_wall = lower_wall;
        this.upper_wall = upper_wall;
        this.player = player;
    }


    public void inertia(){
        Timeline pingpong = new Timeline(new KeyFrame(Duration.millis( ball.getSpeed() ), event -> {//bola se movimenta

            if(ball.checkCollision(player.getRacquet(), ball.getBall())){
                deltaX = deltaX * -1;
                ball.speedUp();
            }

            if( ball.checkCollision( lower_wall.getWall(), ball.getBall() ) || ball.checkCollision( upper_wall.getWall(), ball.getBall() ) ){
                deltaY = deltaY * -1;
            }

            ball.move(deltaX, deltaY);
        }));

        pingpong.setCycleCount(Timeline.INDEFINITE);
        pingpong.play();
    }

}