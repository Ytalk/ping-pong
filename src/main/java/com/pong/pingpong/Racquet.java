package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Racquet{

    private String name;
    private double py;
    private int width;
    private int height;
    private int speed;
    protected Rectangle racquet;

    public Racquet(String name){
        this.name = name;
        speed = 2;
        width = 10;
        height = 60;
        racquet = new Rectangle(width, height, Color.BLACK);
        py = 50;
        racquet.setY(py);
    }

    public Rectangle getRacquet(){
        return racquet;
    }

    public void moveUp(){
        py = py - 7.5;
        racquet.setTranslateY(py);
    }

    public void moveDown(){
        py = py + 7.5;
        racquet.setTranslateY(py);
    }


}