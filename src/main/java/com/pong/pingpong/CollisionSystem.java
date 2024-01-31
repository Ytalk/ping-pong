package com.pong.pingpong;

import javafx.scene.Node;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


/**
 * The `CollisionSystem` class is responsible for handling collisions between different objects in a game.
 * It calculates the movement of a ball and checks for collisions with walls and racquets.
 * It also handles the animation of the collision and updates the game scoreboard.
 *
 * Example Usage:
 * 
 * // Create instances of walls, racquets, scoreboard, and ball
 * Wall lowerWall = new Wall();
 * Wall upperWall = new Wall();
 * Racquet player1 = new Racquet();
 * Racquet player2 = new Racquet();
 * Scoreboard scoreboard = new Scoreboard();
 * Ball ball = new Ball();
 * 
 * // Create an instance of CollisionSystem
 * CollisionSystem collisionSystem = new CollisionSystem(lowerWall, upperWall, player1, player2);
 * 
 * // Start the collision animation and update the scoreboard
 * collisionSystem.inertia(scoreboard, ball);
 * 
 * // Stop the collision animation
 * collisionSystem.stopInertia();
 *
 * Methods:
 * - boundsOverlapping(Bounds bounds1, Bounds bounds2): Checks if two bounding boxes are overlapping
 * - shapesOverlapping(Rectangle rectangle, Circle circle): Checks if a rectangle and a circle are overlapping
 * - checkCollision(Node nodeA, Node nodeB): Checks if two nodes are colliding
 * - inertia(Scoreboard scoreboard, Ball ball): Starts the collision animation and updates the scoreboard
 * - stopInertia(): Stops the collision animation
 *
 * Fields:
 * - deltaX: The horizontal movement of the ball
 * - deltaY: The vertical movement of the ball
 * - lower_wall: The lower wall object
 * - upper_wall: The upper wall object
 * - player: The first player's racquet object
 * - player2: The second player's racquet object
 * - count: A counter variable used in the serve function
 * - collision_animation: The animation timer for the collision animation
 */
public class CollisionSystem{

    private double deltaX = -1;  //start
    private double deltaY = 0;

    private Wall lower_wall;
    private Wall upper_wall;
    private Racquet player;
    private Racquet player2;

    private int count = 0;
    private AnimationTimer collision_animation;


    public CollisionSystem(Wall lower_wall, Wall upper_wall, Racquet player, Racquet player2){
        this.lower_wall = lower_wall;
        this.upper_wall = upper_wall;
        this.player = player;
        this.player2 = player2;
    }


    public boolean boundsOverlapping(Bounds bounds1, Bounds bounds2) {
        return bounds1.intersects(bounds2);
    }


    public boolean shapesOverlapping(Rectangle rectangle, Circle circle) {
        Bounds rectangleBounds = rectangle.getBoundsInParent();
        Bounds circleBounds = circle.getBoundsInParent();

        return boundsOverlapping(rectangleBounds, circleBounds);
    }


    public boolean checkCollision(Node nodeA, Node nodeB){
        return nodeA.getBoundsInParent().intersects( nodeB.getBoundsInParent() );
    }


    public void inertia(Scoreboard scoreboard, Ball ball){

        collision_animation = new AnimationTimer(){

            @Override
            public void handle(long now){

                if( checkCollision(player.getRacquet(), ball.getBall()) ){
                    deltaX = deltaX * -1;
                    deltaY = player.directedDeltaY(deltaY);
                    ball.speedUp();
                }

                if( checkCollision(player2.getRacquet(), ball.getBall()) ){
                    deltaX = deltaX * -1;
                    deltaY = player2.directedDeltaY(deltaY);
                    ball.speedUp();
                }

                if(  checkCollision( lower_wall.getWall(), ball.getBall() )  ||  checkCollision( upper_wall.getWall(), ball.getBall() )  ){
                    deltaY = deltaY * -1;
                }


                //SERVE FUNCTION (SHOULD BE IMPROVED)
                if( player.getServe() ){
                    while(count < 10000) {
                        //System.out.println("p1");
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

        };
        collision_animation.start();

    }


    public void stopInertia(){
        if(collision_animation != null) {
            collision_animation.stop();
        }
    }

}