package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;

public abstract class Racquet{

    private String name;
    private double py;
    private int height;
    private int width;
    protected Rectangle racquet;

    public Racquet(String name){
        this.name = name;
        width = 10;
        height = 60;
        racquet = new Rectangle(width, height, Color.BLACK);
        py = 200;
        racquet.setTranslateY(py);
    }

    public Rectangle getRacquet(){
        return racquet;
    }

    public void moveUp(){
        py = py - 6.5;
        racquet.setTranslateY(py);
    }

    public void moveDown(){
        py = py + 6.5;
        racquet.setTranslateY(py);
    }

    public void move(KeyCode key){

        switch(key){
            case Q:
                if(py > 60)
                    moveUp();
            break;

            case Z:
                if(py < 405)
                    moveDown();
            break;
        }
    }



}