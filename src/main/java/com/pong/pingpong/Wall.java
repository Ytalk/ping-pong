package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The Wall class represents a wall object to initiate collisions.
 * It has fields to store the Y position (X is not necessary as the wall is the width of the window), height, width and a Rectangle object to visually represent the wall.
 */
public class Wall{

    private int py;
    private int height;
    private int width;
    private Rectangle wall;

    /**
     * Constructs a Wall object with the given position and width.
     * The height is set to a default value of 10.
     *
     * @param positionY the position of the wall along the Y-axis
     * @param window_width the width of the wall based on the width of the window
     */
    public Wall(int positionY, int window_width){
        py = positionY;
        width = window_width;
        height = 10;
        wall = new Rectangle(width, height, Color.SILVER);
        wall.setY(py);
    }


    /**
     * Returns the Rectangle object representing the wall.
     *
     * @return the Rectangle object representing the wall
     */
    public Rectangle getWall(){
        return wall;
    }

}