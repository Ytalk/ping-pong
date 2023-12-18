package com.pong.pingpong;

public class RacquetPlayer extends Racquet{

    private int px;

    public RacquetPlayer(String name, int positionX){
        super(name);
        px = positionX;
        racquet.setX(px);
    }


}