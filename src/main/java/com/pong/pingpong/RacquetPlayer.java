package com.pong.pingpong;

import javafx.scene.input.KeyCode;
import java.util.Set;
import javafx.scene.input.KeyEvent;


public class RacquetPlayer extends Racquet{

    boolean control;

    public RacquetPlayer(String name, int positionX, boolean control){
        super(name, positionX);
        this.control = control;
    }


    public void handleKeyPressed(Set<KeyCode> keysPressed, Ball ball){

        if(control){
            if(keysPressed.contains(KeyCode.Q)){
                if(py > 10)
                    moveUp();
            }
            if(keysPressed.contains(KeyCode.Z)){
                if(py < 405)
                    moveDown();
            }
            if(serve){
                if(keysPressed.contains(KeyCode.A)){
                    ball.hurl();
                    setServe(false);
                }
            }
        }

        else{
            if(keysPressed.contains(KeyCode.O)){
                if(py > 10)
                    moveUp();
            }
            if(keysPressed.contains(KeyCode.M)){
                if(py < 405)
                    moveDown();
            }
            if(serve2){
                if(keysPressed.contains(KeyCode.K)){
                    ball.hurl();
                    setServe2(false);
                }
            }
        }

    }


    public void handleKeyReleased(Set<KeyCode> keysPressed, KeyEvent event){
        keysPressed.remove(event.getCode());
    }

}