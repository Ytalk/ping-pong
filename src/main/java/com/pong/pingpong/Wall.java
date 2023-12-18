package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall{

    private int py;
    private int height;
    private int width;
    private Rectangle wall;

    public Wall(int positionY, int window_width){
        py = positionY;
        width = window_width;
        height = 10;
        wall = new Rectangle(width, height, Color.SILVER);
        wall.setY(py);
    }

    public Rectangle getWall(){
        return wall;
    }
}