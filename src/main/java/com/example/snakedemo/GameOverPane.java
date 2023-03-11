package com.example.snakedemo;

import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

public class GameOverPane{
    double paneWidth;
    double paneHeight;
    double panePosX;
    double panePosY;

    public StackPane drawPane(){
        paneWidth = Constants.WINDOW_WIDTH*5/12;
        paneHeight = Constants.WINDOW_HEIGHT/4;
        panePosX = Constants.WINDOW_WIDTH/4;
        panePosY = Constants.WINDOW_HEIGHT/3;
        StackPane pane = new StackPane();
        pane.setPrefSize(paneWidth, paneHeight);
        pane.getChildren().add(drawBackground());
        pane.getChildren().add(writeText());
        pane.setAlignment(Pos.CENTER);
        return pane;
    }
    public Rectangle drawBackground(){
        Rectangle bg = new Rectangle(paneWidth, paneHeight, Color.BEIGE);
        bg.setX(panePosX);
        bg.setY(panePosY);
        bg.setArcWidth(35);
        bg.setArcHeight(35);
        return bg;
    }

    public VBox writeText(){
        Text loseText = formatText(24, "You Lose :)");
        Text prompt = formatText(18, "press space to restart");
        prompt.setFont(Font.font("Tahoma",  FontWeight.SEMI_BOLD, FontPosture.REGULAR, 20));
        VBox textGroup = new VBox( loseText, prompt);
        textGroup.setFillWidth(false);
        textGroup.setAlignment(Pos.CENTER);
        return textGroup;
    }
    public Text formatText(double fontSize, String textContent){
        Text formattedText = new Text();
        formattedText.setTextAlignment(TextAlignment.CENTER);
        formattedText.setTextOrigin(VPos.CENTER);
        formattedText.setFont(Font.font("Tahoma",  FontWeight.BOLD, FontPosture.REGULAR,fontSize));
        formattedText.setFill(Color.BLACK);
        formattedText.setText(textContent);

        return formattedText;
    }

}
