package com.pong.pingpong;

import javafx.animation.AnimationTimer;


public class RacquetBot extends Racquet{

    private AnimationTimer bot_animation;
    private double error_margin = 9.0;

    public RacquetBot(String name, int positionX){
        super(name, positionX);
    }


    public void Move(Ball ball, RacquetBot bot){

        bot_animation = new AnimationTimer(){

            @Override
            public void handle(long now){

                if(  Math.abs( ServeY() - ball.getBall().getCenterY() ) > error_margin  ){
                    if( ServeY() > ball.getBall().getCenterY() ){
                        moveUp();
                    }
                    else if( ServeY() < ball.getBall().getCenterY() ){
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