package com.pong.pingpong;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;


public class RacquetBot extends Racquet{

    private AnimationTimer bot_animation;
    private double errorMargin = 6.0;

    public RacquetBot(String name, int positionX){
        super(name, positionX);
    }


    public void Move(Ball ball, RacquetBot bot){

        bot_animation = new AnimationTimer(){

            @Override
            public void handle(long now){

                if(  Math.abs( ServeY() - ball.getBall().getCenterY() ) > errorMargin  ){
                    if( ServeY() > ball.getBall().getCenterY() && py > 10 ){
                        moveUp();
                    }
                    else if( ServeY() < ball.getBall().getCenterY() && py < 380 ){
                        moveDown();
                    }
                }


                if(getServe2()){
                    ball.draw(bot);
                    ball.hurl();
                    setServe2(false);
                }

            }

        };
        bot_animation.start();

    }


    public void stopBot(){
        if(bot_animation != null) {
            bot_animation.stop();
        }
    }

}