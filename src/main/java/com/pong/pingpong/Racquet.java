package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Racquet{

    private String name;
    protected double py;
    private int px;
    private int height;
    private int width;
    private Rectangle racquet;

    public Racquet(String name, int positionX){
        this.name = name;
        width = 10;
        height = 60;
        racquet = new Rectangle(width, height, Color.BLACK);
        py = 200;
        px = positionX;
        racquet.setX(px);
        racquet.setTranslateY(py);
    }

    public Rectangle getRacquet(){
        return racquet;
    }

    public void moveUp(){
        py = py - 5.5;
        racquet.setTranslateY(py);
    }

    public void moveDown(){
        py = py + 5.5;
        racquet.setTranslateY(py);
    }





}