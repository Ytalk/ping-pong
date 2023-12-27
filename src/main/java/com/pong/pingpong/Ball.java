package com.pong.pingpong;

import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


public class Ball{

    private boolean dx;
    private boolean dy;

    private double px;
    private double py;
    private double radius;
    private double speed;
    private Circle ball;

    public Ball(double centerX, double centerY, double radius){
        px = centerX;
        py = centerY;
        this.radius = radius;
        speed = 2;
        ball = new Circle(px, py, radius, Color.BLUE);
    }


    public Circle getBall(){
        return ball;
    }


    public double getSpeed(){
        return speed;
    }


    public void move(double dx, double dy){
        dy = getSpeed() * dy;
        dx = getSpeed() * dx;
        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }


    public void speedUp(){
        if(speed < 15){
            speed += 0.8;
        }
    }


    public void draw(Racquet racquet){
        speed = 0;
        ball.setCenterX(racquet.ServeX());
        ball.setCenterY(racquet.ServeY());
    }


    public void hurl(){
        speed = 2;
    }


}