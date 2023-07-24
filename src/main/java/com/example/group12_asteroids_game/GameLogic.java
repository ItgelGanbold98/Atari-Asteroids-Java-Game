package com.example.group12_asteroids_game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameLogic
{
    public Scene mainScene;
    public PlayerShip ship;
    public PlayerShip life;
    public AlienShip alien;

    public Group root;

    public AsteroidClass asteroids;

    public boolean alienremoved = false;

    public int alienAppearFlag = 0;
    public long AlienBullettime = 0;
    public Map<KeyCode, Boolean> pressedKeys;

    public List<AlienShip> alienShipList;

    public List<AlienBullet> alienBulletList;

    public List<PlayerShip> noOfLives;

    public List<AsteroidClass> asteroidList;

    public List<Bullet> bulletList;

    public AsteroidClass createLargeAsteroid ()
    {
        AsteroidClass LargeAsteroid;
        double positionx;
        double positiony;
        //Generate initial random asteroid
        Random randomBorder = new Random();
        Random randomx = new Random();
        Random randomy = new Random();
        int spawnSide = randomBorder.nextInt(4) + 1;
        if(spawnSide == 1){
            positionx = randomx.nextInt(60) - 60 ;
            positiony =  randomy.nextInt(832);
            LargeAsteroid  = (AsteroidClass) PolygonsFactory.createEntity(Polygons.PolygonType.LARGE_ASTEROID,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            LargeAsteroid.asteroid.setRotation(asteroidAngle);
            LargeAsteroid.applyAcceleration(1);
        } else if(spawnSide == 2){
            positionx = randomx.nextInt(1280);
            positiony = randomy.nextInt(60) + 832;
            LargeAsteroid  = (AsteroidClass) PolygonsFactory.createEntity(Polygons.PolygonType.LARGE_ASTEROID,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            LargeAsteroid.asteroid.setRotation(asteroidAngle);
            LargeAsteroid.applyAcceleration(1);
        } else if(spawnSide == 3){
            positionx = randomx.nextInt(60) + 1280;
            positiony = randomy.nextInt(832);
            LargeAsteroid  = (AsteroidClass) PolygonsFactory.createEntity(Polygons.PolygonType.LARGE_ASTEROID,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            LargeAsteroid.asteroid.setRotation(asteroidAngle);
            LargeAsteroid.applyAcceleration(1);
        } else {
            positionx = randomx.nextInt(1280);
            positiony = randomy.nextInt(60) - 60;
            LargeAsteroid  = (AsteroidClass) PolygonsFactory.createEntity(Polygons.PolygonType.LARGE_ASTEROID,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            LargeAsteroid.asteroid.setRotation(asteroidAngle);
            LargeAsteroid.applyAcceleration(1);

        }
        return LargeAsteroid;
    }

    public AlienShip createAlienShip ()
    {
        AlienShip alienShip;
        double positionx;
        double positiony;
        //Generate initial random asteroid
        Random randomBorder = new Random();
        Random randomx = new Random();
        Random randomy = new Random();
        int spawnSide = randomBorder.nextInt(4) + 1;
        if(spawnSide == 1){
            positionx = randomx.nextInt(60);
            positiony =  randomy.nextInt(832);
            alienShip  = (AlienShip) PolygonsFactory.createEntity(Polygons.PolygonType.ALIEN_SHIP,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            alienShip.alienship.setRotation(asteroidAngle);
            alienShip.applyAcceleration(1);
            alienShip.applyMove(1280, 832);
        } else if(spawnSide == 2){
            positionx = randomx.nextInt(1280);
            positiony = 832;
            alienShip  = (AlienShip) PolygonsFactory.createEntity(Polygons.PolygonType.ALIEN_SHIP,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            alienShip.alienship.setRotation(asteroidAngle);
            alienShip.applyAcceleration(1);
            alienShip.applyMove(1280, 832);
        } else if(spawnSide == 3){
            positionx = 1280;
            positiony = randomy.nextInt(832);
            alienShip  = (AlienShip) PolygonsFactory.createEntity(Polygons.PolygonType.ALIEN_SHIP,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            alienShip.alienship.setRotation(asteroidAngle);
            alienShip.applyAcceleration(1);
            alienShip.applyMove(1280, 832);
        } else {
            positionx = randomx.nextInt(1280);
            positiony = randomy.nextInt(60);
            alienShip  = (AlienShip) PolygonsFactory.createEntity(Polygons.PolygonType.ALIEN_SHIP,positionx, positiony);
            double asteroidAngle = randomx.nextDouble() * 360;
            alienShip.alienship.setRotation(asteroidAngle);
            alienShip.applyAcceleration(1);
            alienShip.applyMove(1280, 832);

        }
        return alienShip;
    }

    private AnimationTimer timer;

    public void start(Stage mainStage)
    {
        root = new Group();
        this.mainScene = new Scene(root, 1280, 832, Color.web("#070020")); //a drawing surface
        mainStage.setScene(mainScene);
        mainStage.setTitle("Asteroids");

        //Make player ship that is controllable by the player
        ship = (PlayerShip) PolygonsFactory.createEntity(Polygons.PolygonType.Player_SHIP, 600, 400);

        asteroidList = new ArrayList<>();
        asteroids = createLargeAsteroid();
        asteroidList.add(asteroids);


        alienShipList = new ArrayList<>();

        alienBulletList = new ArrayList<>();

        bulletList = new ArrayList<>();

        noOfLives = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            life = new PlayerShip(Polygons.PolygonType.Player_SHIP, 0.8, 950 + 30 * i, 40);
            life.ship.setRotation(-90);
            noOfLives.add(life);
            root.getChildren().add(life.getPolygon());
        }

        // Hashmaps store items in key-value pairs.
        // here we keep track of when a key is pressed and when it is released
        pressedKeys = new HashMap<>();

        AtomicBoolean actionToBePerformed = new AtomicBoolean(false);
        AtomicBoolean didHyperJump = new AtomicBoolean(false);

        mainScene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });
        mainScene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        TextClass noOfAsteroids = new TextClass("No. of asteroids:", 30, 750, Color.WHITE, 30);
        root.getChildren().add(noOfAsteroids.mytext);

        TextClass youDiedText = new TextClass("YOU DIED", 575, 380, Color.WHITE, 40);
        root.getChildren().add(youDiedText.mytext);

        AtomicInteger score = new AtomicInteger(0);

        TextClass scoreText = new TextClass("Score: " + score, 30, 50, Color.WHITE, 40);
        root.getChildren().add(scoreText.mytext);

        TextClass level = new TextClass("Level 1", 1100, 50, Color.WHITE, 40);

        AtomicInteger gameLevel = new AtomicInteger(1);
        root.getChildren().add(level.mytext);

        TextClass gameOver = new TextClass("Game Over", 505, 380, Color.RED, 60);
        gameOver.mytext.setOpacity(0);
        root.getChildren().add(gameOver.mytext);

        TextClass noOfHyperjumps = new TextClass("Hyper jump available!", 1050, 750, Color.WHITE, 20);
        noOfHyperjumps.mytext.setOpacity(0);
        root.getChildren().add(noOfHyperjumps.mytext);

        AtomicBoolean replenishedLife = new AtomicBoolean(false);

        AtomicBoolean immunity = new AtomicBoolean(false);

        AtomicBoolean alienSpawned = new AtomicBoolean(false);
        AtomicBoolean gamePaused = new AtomicBoolean(false);


        timer = new AnimationTimer() {

            @Override
            public void handle(long l) {
                level.SetText("Level " + gameLevel.get());
                noOfAsteroids.SetText("No. of asteroids: " + asteroidList.size());
                if (noOfLives.size() == 0){
                    gameOver.mytext.setOpacity(1);
                }

                if (!didHyperJump.get()) {
                    noOfHyperjumps.mytext.setOpacity(1);
                } else {noOfHyperjumps.mytext.setOpacity(0);}



                if (asteroidList.isEmpty() && alienShipList.isEmpty())
                {
                    gameLevel.set(gameLevel.get() + 1);
                    alienSpawned.set(false);
                    didHyperJump.set(false);
                    replenishedLife.set(false);

                    for (int j = 0; j < gameLevel.get(); j++)
                    {   asteroids = createLargeAsteroid();
                        asteroidList.add(asteroids);
                    }
                    asteroidList.forEach(asteroid -> {
                        asteroid.applyMove(1280, 832);
                        root.getChildren().add(asteroid.getPolygon());
                    });
                }

                if (asteroidList.size() == 1){
                    for (AsteroidClass asteroid : asteroidList) {
                        if (asteroid.asteroid.scale == 0.5 && !alienSpawned.get()) {

                            alien = createAlienShip();
                            alien.applyAcceleration(gameLevel.get());
                            alienShipList.add(alien);
                            alienremoved = false;
                            alienAppearFlag = 0;
                            alienSpawned.set(true);
                            root.getChildren().add(alien.getPolygon());
                    }
                }}

                if (alienShipList.size() != 0 && System.currentTimeMillis() - AlienBullettime > 2000 ){
                    AlienBullet alienbullet = (AlienBullet) PolygonsFactory.createEntity(Polygons.PolygonType.ALIEN_BULLET, alien.getPolygon().getTranslateX(), alien.getPolygon().getTranslateY());
                    if (ship.getPolygon().getTranslateX() < alien.getPolygon().getTranslateX())
                    {   double angle = Math.toDegrees(Math.atan((ship.getPolygon().getTranslateY() - alien.getPolygon().getTranslateY())/
                            (ship.getPolygon().getTranslateX() - alien.getPolygon().getTranslateX())));
                        alienbullet.getPolygon().setRotate(angle - 180);} else {
                        double angle = Math.toDegrees(Math.atan((ship.getPolygon().getTranslateY() - alien.getPolygon().getTranslateY())/
                                (ship.getPolygon().getTranslateX() - alien.getPolygon().getTranslateX())));
                        alienbullet.getPolygon().setRotate(angle);
                    }



                    alienbullet.applyAcceleration(7.0);
                    alienBulletList.add(alienbullet);
                    root.getChildren().add(alienbullet.getPolygon());
                    AlienBullettime = System.currentTimeMillis();

                    Timeline alienBulletRemove = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        alienBulletList.remove(alienbullet);
                        root.getChildren().remove(alienbullet.getPolygon());
                    }));
                    alienBulletRemove.play();
                }
                if(!ship.alive && noOfLives.size() != 0)
                {
                    youDiedText.mytext.setOpacity(1);
                } else {youDiedText.mytext.setOpacity(0);}

                if (immunity.get()){
                    ship.getPolygon().setOpacity(0.5);
                } else {ship.getPolygon().setOpacity(1);}

                if (pressedKeys.getOrDefault(KeyCode.ENTER, false) && !ship.isAlive() && noOfLives.size() == 0){
                    timer.stop();
                    AsteroidsGame game = AsteroidsGame.getInstance();
                    game.showHighScores();
                }

                if (pressedKeys.getOrDefault(KeyCode.ENTER, false) && !ship.isAlive() && noOfLives.size() != 0) {
                    ship.ship.getPolygon().setTranslateX(600);
                    ship.ship.getPolygon().setTranslateY(400);
                    ship.ship.halt();
                    ship.ship.setRotation(0);
                    ship.alive = true;
                    root.getChildren().add(ship.getPolygon());
                    root.getChildren().remove(youDiedText);
                    immunity.set(true);
                    ship.setImmune(true);
                    //player is immune for the first 2 seconds, so that if an asteroid
                    //spawns close to the player, they can move away quickly
                    Timeline immune = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        immunity.set(false);
                        ship.setImmune(false);
                    }));
                    immune.play();

                }

                if (!replenishedLife.get() && pressedKeys.getOrDefault(KeyCode.L, false) && ship.isAlive() && noOfLives.size() < 3 && score.get() >= 10000) {
                    if (noOfLives.size() == 1){
                        PlayerShip life = new PlayerShip(Polygons.PolygonType.Player_SHIP, 0.8, 950 + 30, 40);
                        life.ship.setRotation(-90);
                        noOfLives.add(life);
                        root.getChildren().add(life.getPolygon());
                        replenishedLife.set(true);}
                    else {
                        PlayerShip life = new PlayerShip(Polygons.PolygonType.Player_SHIP, 0.8, 950 + 30 * 2, 40);
                        life.ship.setRotation(-90);
                        noOfLives.add(life);
                        root.getChildren().add(life.getPolygon());
                        replenishedLife.set(true);
                    }
                    score.set(score.get() - 10000);
                    scoreText.SetText("Score: " + score.get());
                }

                if (pressedKeys.getOrDefault(KeyCode.LEFT, false) && ship.isAlive()) {
                    ship.rotLeft();
                }
                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false) && ship.isAlive()) {
                    ship.rotRight();
                }
                if (pressedKeys.getOrDefault(KeyCode.C, false) && ship.isAlive() && !didHyperJump.get()) {
                    ship.hyperjump();
                    didHyperJump.set(true);
                    immunity.set(true);
                    ship.setImmune(true);
                    //player is immune for the first 2 seconds, so that if an asteroid
                    //spawns close to the player, they can move away quickly
                    Timeline immune = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                        immunity.set(false);
                        ship.setImmune(false);
                    }));
                    immune.play();
                }


                if (pressedKeys.getOrDefault(KeyCode.UP, false) && ship.isAlive()) {
                    ship.applyAcceleration(0.06);
                }


                mainScene.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
                    if (!actionToBePerformed.get()  && keyEvent.getCode() == KeyCode.SPACE && bulletList.size() < 7 &&ship.isAlive())
                    {
                        Bullet bullet = new Bullet(Polygons.PolygonType.BULLET, ship.getPolygon().getTranslateX(), ship.getPolygon().getTranslateY());
                        bullet.bullet.setRotation(ship.getPolygon().getRotate());
                        bullet.applyAcceleration(10.0);
                        bulletList.add(bullet);
                        root.getChildren().add(bullet.getPolygon());

                        Timeline timeToLive = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
                            bulletList.remove(bullet);
                            root.getChildren().remove(bullet.getPolygon());
                        }));
                        timeToLive.play();
                        actionToBePerformed.set(true);
                    }
                });

                mainScene.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
                    if ( actionToBePerformed.get() && keyEvent.getCode() == KeyCode.SPACE) {
                        actionToBePerformed.set(false);
                    }
                });

                ship.applyMove(1280,832);

                alienShipList.forEach(alienShip -> {
                    if(alienShip!=null){
                        alienShip.applyMove(1280, 832);
                    }
                    if (!immunity.get()){
                        if(ship.isAlive() && ship.collision(alienShip)){
                            root.getChildren().remove(noOfLives.get(noOfLives.size()-1).getPolygon());
                            noOfLives.remove(noOfLives.get(noOfLives.size()-1));
                            root.getChildren().remove(ship.getPolygon());
                            ship.alive = false;
                        }
                    }
                });

                asteroidList.forEach(asteroid -> {
                    asteroid.applyMove(1280, 832);
                    alienBulletList.forEach(alienbullet -> {
                        if (!immunity.get() && !(noOfLives.size() == 1) && ship.isAlive() && ship.collision(alienbullet)) {
                            root.getChildren().remove(noOfLives.get(noOfLives.size()-1).getPolygon());
                            noOfLives.remove(noOfLives.get(noOfLives.size()-1));
                            root.getChildren().remove(ship.getPolygon());
                            ship.alive = false;
                            root.getChildren().remove(ship.getPolygon());
                        } else if (!immunity.get() && (noOfLives.size() == 1) && ship.isAlive() && ship.collision(alienbullet)) {
                            root.getChildren().remove(noOfLives.get(noOfLives.size() - 1).getPolygon());
                            noOfLives.remove(noOfLives.get(noOfLives.size() - 1));
                            ship.alive = false;
                            root.getChildren().remove(life.getPolygon());
                            root.getChildren().remove(ship.getPolygon());

                        }});


                    if (!immunity.get()){
                        if (ship.isAlive() && ship.collision(asteroid)) {
                            root.getChildren().remove(noOfLives.get(noOfLives.size()-1).getPolygon());
                            noOfLives.remove(noOfLives.get(noOfLives.size()-1));
                            root.getChildren().remove(ship.getPolygon());
                            ship.alive = false;
                        }
                    }});
                alienBulletList.forEach(alienbullet -> {
                    alienbullet.applyMove(1280, 832);
                });

                bulletList.forEach(bullet -> {
                    bullet.applyMove(1280, 832);
                });
                List<Bullet> bulletsToRemove = bulletList.stream().filter(bullet -> {
                    List<AsteroidClass> collides = asteroidList.stream().filter(asteroid -> asteroid.collision(bullet))
                            .toList();

                    if (collides.isEmpty()) {
                        return false;
                    }

                    collides.forEach(collided -> {
                        asteroidList.remove(collided);
                        root.getChildren().remove(collided.getPolygon());

                        if (collided.polygonType == Polygons.PolygonType.LARGE_ASTEROID) {
                            score.set(score.get() + 500);
                            scoreText.SetText("Score: " + score);
                            for (int i = 0; i < 2; i++) {
                                AsteroidClass mediumAsteroid = new AsteroidClass(Polygons.PolygonType.MEDIUM_ASTEROID, collided.getPolygon().getTranslateX(), collided.getPolygon().getTranslateY(), 1);
                                double asteroidAngle = collided.asteroid.getAngle() + (-90 * i + 45);
                                mediumAsteroid.asteroid.setRotation(asteroidAngle);
                                mediumAsteroid.applyAcceleration(2);
                                asteroidList.add(mediumAsteroid);
                                root.getChildren().add(mediumAsteroid.getPolygon());
                            }}

                        else if (collided.polygonType == Polygons.PolygonType.MEDIUM_ASTEROID) {
                            score.set(score.get() + 750);
                            scoreText.SetText("Score: " + score);
                            for (int i = 0; i < 2; i++) {
                                AsteroidClass smallAsteroid = new AsteroidClass(Polygons.PolygonType.SMALL_ASTEROID, collided.getPolygon().getTranslateX(), collided.getPolygon().getTranslateY(), 0.5);
                                double asteroidAngle = collided.asteroid.getAngle() + (-90 * i + 45);
                                smallAsteroid.asteroid.setRotation(asteroidAngle);
                                smallAsteroid.applyAcceleration(2);
                                asteroidList.add(smallAsteroid);
                                root.getChildren().add(smallAsteroid.getPolygon());
                            }
                        } else {
                            score.set(score.get() + 1000);
                            scoreText.SetText("Score: " + score);
                        }
                    });
                    return true;
                }).toList();
                bulletsToRemove.forEach(bullet -> {
                    bulletList.remove(bullet);
                    root.getChildren().remove(bullet.getPolygon());
                });



                List<Bullet> bulletsToRemoveDueToAlienShip = bulletList.stream().filter(bullet -> {
                    List<AlienShip> collidesWithAlien = alienShipList.stream().filter(alien -> alien.collision(bullet))
                            .toList();

                    if (collidesWithAlien.isEmpty()) {
                        return false;
                    }

                    collidesWithAlien.forEach(collided -> {
                        alienShipList.remove(collided);
                        collided.alive = false;
                        root.getChildren().remove(collided.getPolygon());
                    });
                    return true;
                }).toList();
                bulletsToRemoveDueToAlienShip.forEach(bullet -> {
                    bulletList.remove(bullet);
                    root.getChildren().remove(bullet.getPolygon());
                });
            }
        };

        root.getChildren().add(ship.getPolygon());
        asteroidList.forEach(asteroid -> root.getChildren().add(asteroid.getPolygon()));
        timer.start();
    }

}