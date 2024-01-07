package com.pong.pingpong;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class responsible for being the template for a racquet for a player or bot
 */
public abstract class Racquet{

    private String name;
    protected double py;
    private int px;
    private int height;
    private int width;
    private Rectangle racquet;
    protected boolean serve = false;
    protected boolean serve2 = false;
    private int direct_ball = 0;

    /**
     * Initializes the racquet with a name and initial X position (defines the racquet's side).
     * It also sets the racquet's size and position on the screen.
     * 
     * @param name The "name of the racquet".
     * @param positionX The x-coordinate position of the racquet.
     */
    public Racquet(String name, int positionX){
        this.name = name;

        width = 10;
        height = 70;
        racquet = new Rectangle(width, height, Color.BLACK);

        racquet.setArcWidth(10);
        racquet.setArcHeight(10);

        py = 200;
        px = positionX;
        racquet.setX(px);
        racquet.setTranslateY(py);
    }


    /**
     * Returns the Rectangle object representing the racquet.
     * 
     * @return The Rectangle object representing the racquet.
     */
    public Rectangle getRacquet(){
        return racquet;
    }


    /**
     * Moves the racquet up by decreasing its y-coordinate.
     */
    public void moveUp(){
        if(py > 20) {

            py = py - 11.5;
            racquet.setTranslateY(py);

            direct_ball = -1;
        }
    }


    /**
     * Moves the racquet down by increasing its y-coordinate.
     */
    public void moveDown(){
        if(py < 360) {
            py = py + 11.5;
            racquet.setTranslateY(py);

            direct_ball = 1;
        }
    }


    public double directedDeltaY(double deltaY){

        if(deltaY > 0){//vindo de baixo
            if(direct_ball > 0){//baixo
                return deltaY;
            }

            else if(direct_ball < 0){//cima
                return deltaY * -1;
            }
        }

        else if(deltaY < 0){//vindo de cima
            if(direct_ball > 0){//baixo
                return deltaY * -1;
            }

            else if(direct_ball < 0){//cima
                return deltaY;
            }
        }

        else{//vindo do center
            if(direct_ball > 0){//baixo
                return deltaY + 1;
            }

            else if(direct_ball < 0){//cima
                return deltaY + (-1);
            }
        }

        return 0;//centro
    }


    public void setDirectBallCenter(){
        direct_ball = 0;
    }


    /**
     * Returns the name of the racquet.
     * 
     * @return The name of the racquet.
     */
    public String getName(){
        return name;
    }


    /**
     * Calculates and returns the x-coordinate to serve the ball based on the racquet's side(px).
     * 
     * @return The x-coordinate for serving the ball.
     */
    public double ServeX(){
        if(px > 400)
            return px - 11;//11
        else
            return (px + 21);//21
    }


    /**
     * Calculates and returns the y-coordinate to serve the ball centrally based on the position and size of the racket.
     * 
     * @return The y-coordinate to serve the ball.
     */
    public double ServeY(){
        return ( py + (height / 2) );
    }


    /**
     * Sets the serve state of the racquet.
     * 
     * @param state The serve state to be set.
     */
    public void setServe(boolean state){
        serve = state;
    }


    /**
     * Sets the serve state of a possible player two.
     * 
     * @param state The player two's serve state to be set.
     */
    public void setServe2(boolean state){
        serve2 = state;
    }


    /**
     * Returns the serve state of the racquet.
     * 
     * @return The serve state of the racquet.
     */
    public boolean getServe(){
        return serve;
    }


    /**
     * Returns the serve state of a possible player two.
     * 
     * @return The serve state of a possible player two.
     */
    public boolean getServe2(){
        return serve2;
    }

}