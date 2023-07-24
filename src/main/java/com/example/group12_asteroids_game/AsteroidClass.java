package com.example.group12_asteroids_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.util.Random;

public class AsteroidClass extends Polygons{
    Double[] asteroidPoints = {0.0, -30.0, //for code re-usability it's better to define the points outside
            -20.0, -20.0,
            -30.0, 10.0,
            0.0, 30.0,
            30.0, 20.0,
            30.0, 10.0,
            35.0, 0.0,
            20.0, -20.0};

    public Polygons asteroid;

    public PolygonType polygonType;

    public AsteroidClass(PolygonType polygonType, double x, double y, double s){

        this.polygonType = polygonType;
        Random rand = new Random();
        int error;
        for (int i = 0; i < asteroidPoints.length; i++) {
            if(rand.nextBoolean()) {
                error = -1;
            }
            else{
                error = 1;
            }
            double replacement = asteroidPoints[i]+ 5 * error;
            asteroidPoints[i]=replacement;

        }
        this.asteroid = new Polygons(this.polygonType, this.asteroidPoints, Color.GRAY, Color.WHITE, s, x, y);
    }

    public Polygon getPolygon(){
        return this.asteroid.getPolygon();
    }

    public void applyAcceleration(double acc){
        this.asteroid.applyAcceleration(acc);
    }

    public void rotLeft(){
        this.asteroid.rotLeft();
    }

    public void rotRight(){
        this.asteroid.rotRight();
    }

    public void applyMove(int ScreenWidth, int ScreenHeight){
        this.asteroid.applyMove(ScreenWidth, ScreenHeight);
    }

    public boolean collision(Polygons other){
        //getBoundsInLocal gives the bounds of a node in its own coordinate system
        Shape collisionArea = Shape.intersect(this.asteroid.getPolygon(), other.getPolygon());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

}
