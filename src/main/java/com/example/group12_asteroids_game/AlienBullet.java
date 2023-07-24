package com.example.group12_asteroids_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AlienBullet extends Polygons{

    Double[] alienBulletPoints = {2.0,0.0,0.0,2.0,-2.0,0.0,0.0,-2.0};

    public Polygons alienBullet;

    public AlienBullet(PolygonType polygonType,double x, double y){
        this.alienBullet = new Polygons(polygonType,this.alienBulletPoints, Color.YELLOW, Color.RED, 1, x, y);
        this.alienBullet.setRadius(2);
    }

    public Polygon getPolygon(){
        return this.alienBullet.getPolygon();
    }

    public void applyAcceleration(double acc){
        this.alienBullet.applyAcceleration(acc);
    }

    public void rotLeft(){ this.alienBullet.rotLeft();
    }

    public void rotRight(){ this.alienBullet.rotRight();
    }

    public void applyMove(int ScreenWidth, int ScreenHeight){
        this.alienBullet.applyMove(ScreenWidth, ScreenHeight);
    }


}