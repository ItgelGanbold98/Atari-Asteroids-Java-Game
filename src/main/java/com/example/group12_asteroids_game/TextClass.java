package com.example.group12_asteroids_game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextClass extends Text {
    int x;
    int y;
    int size;
    protected Text mytext = new Text();
    public TextClass(String textString, int x, int y, Color fillColor, int size){
        this.x = x;
        this.y = y;
        this.mytext.setText(textString);
        this.size = size;
        this.mytext.setFill(fillColor);
        this.mytext.setX(this.x);
        this.mytext.setY(this.y);
        this.mytext.setFont(new Font(size));
    }

    protected void SetText(String newTextString) {
        this.mytext.setText(newTextString);
    }
}
