package com.pong.pingpong;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;


public class RacquetBot extends Racquet{

    private AnimationTimer bot_animation;

    public RacquetBot(String name, int positionX){
        super(name, positionX);
    }


    public void Move(Ball ball, RacquetBot bot){

        bot_animation = new AnimationTimer(){

            @Override
            public void handle(long now){

                if(py > ball.getBall().getCenterY()){
                    if(py > 10) {
                        moveUp();
                    }
                }
                else if(py < ball.getBall().getCenterY()){
                    if(py < 380) {
                        moveDown();
                    }
                }
                else{
                    py = py;
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