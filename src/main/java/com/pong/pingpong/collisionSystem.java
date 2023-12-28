package com.pong.pingpong;

import javafx.scene.Node;
import javafx.animation.AnimationTimer;

import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class CollisionSystem{

    double deltaX = 1;  //start final
    double deltaY = 0.5;

    Wall lower_wall;
    Wall upper_wall;
    Racquet player;
    Racquet player2;

    int count = 0;


    public CollisionSystem(Wall lower_wall, Wall upper_wall, Racquet player, Racquet player2){
        this.lower_wall = lower_wall;
        this.upper_wall = upper_wall;
        this.player = player;
        this.player2 = player2;
    }


    public boolean areBoundsOverlapping(Bounds bounds1, Bounds bounds2) {
        return bounds1.intersects(bounds2);
    }

    public boolean areShapesOverlapping(Rectangle rectangle, Circle circle) {
        Bounds rectangleBounds = rectangle.getBoundsInParent();
        Bounds circleBounds = circle.getBoundsInParent();

        return areBoundsOverlapping(rectangleBounds, circleBounds);
    }


    public boolean checkCollision(Node nodeA, Node nodeB){
        return nodeA.getBoundsInParent().intersects( nodeB.getBoundsInParent() );
    }


    public void inertia(Scoreboard scoreboard, Ball ball){

        new AnimationTimer(){

            @Override
            public void handle(long now){
                if(  checkCollision( player.getRacquet(), ball.getBall() )  ||  checkCollision( player2.getRacquet(), ball.getBall() )  ){
                    deltaX = deltaX * -1;
                    ball.speedUp();
                }

                if(  checkCollision( lower_wall.getWall(), ball.getBall() )  ||  checkCollision( upper_wall.getWall(), ball.getBall() )  ){
                    deltaY = deltaY * -1;
                }


                //FUNÇÃO DE SAQUE (IN PROGRESS)
                if( player.getServe() ){
                    //System.out.println("p1 levou gol");
                    while(count < 10000) {
                        ball.draw(player);

                        if (!player.getServe()) {
                            deltaX = deltaX * -1;
                            break;
                        }
                        count += 1;
                    }
                    count = 0;
                }

                if( player2.getServe2() ){
                    //System.out.println("p2 levou gol");
                    while(count < 10000) {
                        ball.draw(player2);

                        if (!player2.getServe2()) {
                            deltaX = deltaX * -1;
                            break;
                        }
                        count += 1;
                    }
                    count = 0;
                }


                ball.move(deltaX, deltaY);

                scoreboard.setScore(ball, player, player2);
            }

        }.start();

    }


}