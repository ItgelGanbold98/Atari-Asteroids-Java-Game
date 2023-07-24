package com.example.group12_asteroids_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class AlienShip extends Polygons {

    Double[] alienShipPoints = {0.0,0.0,
            16.0, 10.0,
            20.0, 10.0,
            26.0, 18.0,
            44.0, 18.0,
            50.0, 10.0,
            54.0, 10.5,
            70.0, 0.0,
            50.0, 0.0,
            44.0, -4.0,
            26.0, -4.0,
            20.0, 0.0};


    public Polygons alienship;

    public boolean alive = true;

    public AlienShip(PolygonType polygonType,double x, double y, double s){
        this.alienship = new Polygons(polygonType, this.alienShipPoints, Color.ORANGE.brighter(), Color.TRANSPARENT, s, x, y);
        this.polygonType=polygonType;

    }
    public Polygon getPolygon(){
        return this.alienship.getPolygon();
    }

    public void applyAcceleration(double acc){
        this.alienship.applyAcceleration(acc);
    }

    public void rotLeft(){
        this.alienship.rotLeft();
    }

    public void rotRight(){
        this.alienship.rotRight();
    }


    public void applyMove(int ScreenWidth, int ScreenHeight){
        this.alienship.applyMove(ScreenWidth, ScreenHeight);
    }

    public boolean getAlive(){
        return this.alive;
    }

    public boolean collision(com.example.group12_asteroids_game.Polygons other){
        //getBoundsInLocal gives the bounds of a node in its own coordinate system
        Shape collisionArea = intersect(this.alienship.getPolygon(), other.getPolygon());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }


}
