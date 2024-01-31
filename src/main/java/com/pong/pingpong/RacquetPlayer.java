package com.pong.pingpong;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import java.util.Set;
import javafx.scene.input.KeyEvent;

/**
 * Represents a player in the game and handles key events for controlling the player's racquet.
 * Extends the Racquet class.
 */
public class RacquetPlayer extends Racquet{

    boolean control;//player 1 or 2
    private AnimationTimer player_animation;

    /**
     * Constructs a RacquetPlayer object with the specified name, positionX, and control.
     * @param name the name of the player
     * @param positionX the x position (side) of the player's racquet
     * @param control true for player one and false for player two
     */
    public RacquetPlayer(String name, int positionX, boolean control){
        super(name, positionX);
        this.control = control;
    }

    /**
     * Handles key pressed events for controlling the player's racquet.
     * Moves the racquet up or down based on the pressed keys.
     * Hurls the ball when the serve key is pressed.
     * @param keys_pressed the set of keys that are currently pressed
     * @param ball the ball object in the game
     */
    public void handleKeyPressed(Set<KeyCode> keys_pressed, Ball ball) {
        if (control) {
            if (keys_pressed.contains(KeyCode.Q)) {
                moveUp();
            }
            if (keys_pressed.contains(KeyCode.Z)) {
                moveDown();
            }
            if (serve && keys_pressed.contains(KeyCode.A)) {
                ball.hurl();
                setServe(false);
            }
        }
        else {
            if (keys_pressed.contains(KeyCode.O)) {
                moveUp();
            }
            if (keys_pressed.contains(KeyCode.M)) {
                moveDown();
            }
            if (serve2 && keys_pressed.contains(KeyCode.K)) {
                ball.hurl();
                setServe2(false);
            }
        }
    }

    /**
     * Handles key released events.
     * Removes the released key from the keysPressed set.
     * @param keysPressed the set of keys that are currently pressed
     * @param event the key event that was released
     */
    public void handleKeyReleased(Set<KeyCode> keysPressed, KeyEvent event){
        keysPressed.remove(event.getCode());
        setDirectBallCenter();
    }


    public void Move(Set<KeyCode> keysPressed, Ball ball) {

        player_animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleKeyPressed(keysPressed, ball);
            }
        };
        player_animation.start();
    }


    public void stopPlayer(){
        if(player_animation != null) {
            player_animation.stop();
        }
    }


}