package com.example.group12_asteroids_game;

import javafx.scene.shape.Polygon;


public class PolygonsFactory {

    public static Polygon createEntity(Polygons.PolygonType polygonType ,double x , double y) {
        double original_x;
        double original_y;
        double large_asteroid_size = 2.0;
        double medium_asteroid_size = 1.0;
        double small_asteroid_size = 0.5;
        double alien_ship_size = 1.0;


        switch (polygonType) {
            case Player_SHIP -> {
                return new PlayerShip(polygonType, 1, x, y);
            }
            case LARGE_ASTEROID -> {
                original_x = x;
                original_y = y;
                return new AsteroidClass(polygonType, original_x, original_y, large_asteroid_size);
            }
            case MEDIUM_ASTEROID -> {
                original_x = x;
                original_y = y;
                return new AsteroidClass(polygonType, original_x, original_y, medium_asteroid_size);
            }
            case SMALL_ASTEROID -> {
                original_x = x;
                original_y = y;
                return new AsteroidClass(polygonType, original_x, original_y, small_asteroid_size);
            }
            case ALIEN_SHIP -> {
                original_x = x;
                original_y = y;
                return new AlienShip(polygonType, original_x, original_y, alien_ship_size);
            }
            case BULLET -> {
                return new Bullet(polygonType, x, y);
            }
            case ALIEN_BULLET -> {
                return new AlienBullet(polygonType, x, y);
            }
            default -> throw new IllegalArgumentException("Invalid entity type: " + polygonType);
        }
    }
}


