package com.example.group12_asteroids_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends Polygons{

    Double[] bulletPoints = {2.0,0.0,0.0,2.0,-2.0,0.0,0.0,-2.0};

    public Polygons bullet;

    public Bullet(PolygonType polygonType, double x, double y){
        this.bullet = new Polygons(polygonType, this.bulletPoints, Color.WHITE, Color.CYAN, 1, x, y);
        this.bullet.setRadius(2);


    }

    public Polygon getPolygon(){
        return this.bullet.getPolygon();
    }

    public void applyAcceleration(double acc){
        this.bullet.applyAcceleration(acc);
    }

    public void rotLeft(){
        this.bullet.rotLeft();
    }

    public void rotRight(){
        this.bullet.rotRight();
    }

    public void applyMove(int ScreenWidth, int ScreenHeight){
        this.bullet.applyMove(ScreenWidth, ScreenHeight);
    }

}
