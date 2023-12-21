package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;


public class Ball{

    private double px;
    private double py;
    private double dx;
    private double dy;
    private double radius;
    private double speed;
    private double acceleration;
    private Circle ball;

    public Ball(double centerX, double centerY, double radius){
        px = centerX;
        py = centerY;
        this.radius = radius;
        speed = 20;
        ball = new Circle(px, py, radius, Color.BLUE);
    }


    public Circle getBall(){
        return ball;
    }


    public void move(double dx, double dy){
        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }


    public void speedUp(){
        speed -= 19;
    }


    public double getSpeed(){
        return speed;
    }


    public boolean checkCollision(Node nodeA, Node nodeB){
        return nodeA.getBoundsInParent().intersects(nodeB.getBoundsInParent());
    }


}