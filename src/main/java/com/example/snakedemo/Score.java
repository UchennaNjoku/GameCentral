package com.example.snakedemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Score implements Drawable{
    private int currentScore;
    public static int highScore = 0;

    public Score(){
        this.currentScore = 0;
    }

    public void incrementScore(){
        this.currentScore += 1;
        if (this.currentScore > highScore){
            highScore = this.currentScore;
        }
    }
    public void resetCurrentScore(){
        this.currentScore = 0;
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.BLACK);
        context.setFont(Font.font("Tahoma", FontWeight.BOLD, FontPosture.ITALIC, 18));
        context.fillText("Current Score: "+ currentScore, 30, 45);
        context.fillText("High Score: "+ highScore, 30, 65);
    }
}
