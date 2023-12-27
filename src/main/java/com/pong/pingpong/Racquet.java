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
    boolean serve = false;
    boolean serve2 = false;

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


    public String getName(){
        return name;
    }




    public double ServeX(){
        if(px > 400)
            return px - 30;
        else
            return (px + 30);
    }


    public double ServeY(){
        return ( py + (height / 2) );
    }


    public void setServe(boolean state){
        serve = state;
    }

    public void setServe2(boolean state){
        serve2 = state;
    }


    public boolean getServe(){
        return serve;
    }

    public boolean getServe2(){
        return serve2;
    }

}