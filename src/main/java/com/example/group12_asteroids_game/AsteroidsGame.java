package com.example.group12_asteroids_game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


public class AsteroidsGame extends Application {
    static Stage primaryStage;

    private static final AsteroidsGame instance = new AsteroidsGame();

    protected static AsteroidsGame getInstance() {return instance;}
    final Scene menuScene = new Scene(new VBox(), 1280, 832);
    final Scene highScoresScene = new Scene(new VBox(), 1280, 832);
    final Scene enterNameScene = new Scene(new VBox(), 1280, 832);
    private TextField playerNameField;

    private final GameLogic gameLoop = new GameLogic();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AsteroidsGame.primaryStage = primaryStage;
        primaryStage.setTitle("Asteroids");
        showMainMenu();
    }

    private void showMainMenu() {
        Label title = new Label("Asteroids");
        title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 90));
        title.setTextFill(Color.YELLOW);

        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        startButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-border-color: yellow;");

        Button highScoresButton = new Button("High Scores");
        highScoresButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        highScoresButton.setStyle("-fx-background-color: black; -fx-text-fill: white;-fx-border-color: yellow;");

        Button exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        exitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;-fx-border-color: yellow;");

        Button controlsButton = new Button("Controls");
        controlsButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        controlsButton.setStyle("-fx-background-color: black; -fx-text-fill: white;-fx-border-color: yellow;");

        startButton.setOnAction(event -> {
            showEnterNameScreen();
        });

        controlsButton.setOnAction(event->{
            showControlsScreen();
        });

        highScoresButton.setOnAction(event -> {
            showHighScores();
        });

        exitButton.setOnAction(event -> {
            primaryStage.close();
        });

        VBox menuLayout = new VBox(40);
        menuLayout.getChildren().addAll(title, startButton, controlsButton, highScoresButton, exitButton);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setStyle("-fx-background-color: #070020;");

        menuScene.setRoot(menuLayout);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Asteroids");
        primaryStage.show();
    }

    private void showControlsScreen() {
        Label controlsLabel = new Label("Controls\n");
        controlsLabel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 48));
        controlsLabel.setTextFill(Color.WHITE);

        Label controlsText = new Label("""
                The aim of the game is to avoid Asteroids and survive as long as possible in a series of increasingly difficult levels!

                LEFT ARROW KEY: Press the left arrow key to rotate the ship anti-clockwise.

                RIGHT ARROW KEY: Press the right arrow key to rotate the ship clockwise.

                UP ARROW KEY: Press the up arrow key to apply thrust and move forward.

                SPACE: Press the space bar to shoot at enemies.

                C KEY: Press the C key to perform a hyperjump, which randomly teleports you and provides brief immunity.

                L KEY: Press the L key to spend 10,000 points and replenish a life.



                """);
        controlsText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        controlsText.setTextFill(Color.WHITE);

        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        startButton.setStyle("-fx-background-color: black; -fx-text-fill: yellow;");
        startButton.setOnAction(event -> {
            showEnterNameScreen();
        });

        Button backButton = new Button("Back to Main Menu");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: yellow;");
        backButton.setOnAction(event -> {
            showMainMenu();
        });

        VBox buttonLayout = new VBox(30);
        buttonLayout.getChildren().addAll(startButton, backButton);
        buttonLayout.setAlignment(Pos.CENTER);

        VBox controlsLayout = new VBox(20);
        controlsLayout.getChildren().addAll(controlsLabel, controlsText, buttonLayout);
        controlsLayout.setAlignment(Pos.CENTER);
        controlsLayout.setStyle("-fx-background-color: #070020;");

        Scene controlsScene = new Scene(controlsLayout, 1280, 832);
        primaryStage.setScene(controlsScene);
        primaryStage.setTitle("Controls");
    }

    void showHighScores() {

        List<String> highScores = readHighScores();

        VBox highScoresLayout = new VBox(30);
        highScoresLayout.setAlignment(Pos.CENTER);
        highScoresLayout.setStyle("-fx-background-color: #070020;");


        Label highScoresTitle = new Label("High Scores");
        highScoresTitle.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        highScoresTitle.setTextFill(Color.WHITE);

        highScoresLayout.getChildren().add(highScoresTitle);

        for (String score : highScores) {
            Label scoreLabel = new Label(score);
            scoreLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 30));
            scoreLabel.setTextFill(Color.WHITE);
            highScoresLayout.getChildren().add(scoreLabel);
        }

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: black; -fx-text-fill: white;-fx-border-color: yellow;");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        backButton.setOnAction(event -> showMainMenu());

        highScoresLayout.getChildren().add(backButton);

        highScoresScene.setRoot(highScoresLayout);
        primaryStage.setScene(highScoresScene);
        primaryStage.setTitle("High Scores");
    }
    private List<String> readHighScores() {
        List<String> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/high_scores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }
    private void showEnterNameScreen() {
        Label enterNameLabel = new Label("Asteroids");
        enterNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        enterNameLabel.setTextFill(Color.web("#ffffff"));

        playerNameField = new TextField();
        playerNameField.setPromptText("Player Name");
        playerNameField.setMaxWidth(240);

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: black; -fx-text-fill: white;-fx-border-color: yellow;");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 38));
        submitButton.setOnAction(event -> {
            // Save player's name
            String playerName = playerNameField.getText().trim();
            if (!playerName.isEmpty()) {
                showGame();
            }
        });

        VBox enterNameLayout = new VBox(30);
        enterNameLayout.getChildren().addAll(enterNameLabel, playerNameField, submitButton);
        enterNameLayout.setAlignment(Pos.CENTER);
        enterNameLayout.setStyle("-fx-background-color: #070020;");

        enterNameScene.setRoot (enterNameLayout);
        primaryStage.setScene(enterNameScene);
        primaryStage.setTitle("Enter Your Name");
    }
    private void showGame() {
            gameLoop.start(primaryStage);
    }
}
