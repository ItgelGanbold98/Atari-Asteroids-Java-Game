module com.example.group12_asteroids_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.group12_asteroids_game to javafx.fxml;
    exports com.example.group12_asteroids_game;
}