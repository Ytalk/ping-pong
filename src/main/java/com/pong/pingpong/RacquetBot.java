package com.pong.pingpong;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;


public class RacquetBot extends Racquet{

    private AnimationTimer bot_animation;

    public RacquetBot(String name, int positionX){
        super(name, positionX);
    }


    public void Move(Ball ball){

        bot_animation = new AnimationTimer(){

            @Override
            public void handle(long now){

                if(py > ball.getBall().getCenterY()){
                    if(py > 10)
                        moveUp();
                    //System.out.println("suba");
                }
                else if(py < ball.getBall().getCenterY()){
                    if(py < 380)
                        moveDown();
                    //System.out.println("desça");
                }
                else{
                    py = py;
                    //System.out.println("nada");
                }

                if(getServe2()){
                    //System.out.println("sacar");
                    //ball.draw();
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