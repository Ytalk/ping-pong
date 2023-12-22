package com.pong.pingpong;

import javafx.scene.input.KeyCode;
import java.util.Set;

public class RacquetPlayer extends Racquet{

    boolean control1;

    public RacquetPlayer(String name, int positionX, boolean control1){
        super(name, positionX);

        this.control1 = control1;
    }


    public void handleKeyPressed(Set<KeyCode> keysPressed){

        if(control1 == true){
            if(keysPressed.contains(KeyCode.Q)) {
                if (py > 60)
                    moveUp();
            }
            if(keysPressed.contains(KeyCode.Z)){
                    if (py < 405)
                        moveDown();
            }
        }

        if(control1 == false){
            if(keysPressed.contains(KeyCode.O)) {
                if (py > 60)
                    moveUp();
            }
            if (keysPressed.contains(KeyCode.M)) {
                if (py < 405)
                    moveDown();
            }
        }

    }



    public void handleKeyReleased(Set<KeyCode> keysPressed){
        keysPressed = null;
    }


}