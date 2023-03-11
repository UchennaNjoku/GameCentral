package com.example.snakedemo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window extends Stage {
    private double height;
    private double width;
    private final Canvas canvas;
    private final GraphicsContext context;
    private final StackPane pane;
    private final Scene surface;



    public Window(){
        this.width = Constants.WINDOW_WIDTH;
        this.height = Constants.WINDOW_HEIGHT;
        this.canvas = new Canvas(width, height);
        this.context = canvas.getGraphicsContext2D();
        this.pane = new StackPane(canvas);
        this.surface = new Scene(pane, width, height, Color.valueOf("262626"));
        this.setScene(surface);
        this.setResizable(false);
    }

    public Window(double width, double height, String title){
        this();
        this.canvas.setHeight(height);
        this.canvas.setWidth(width);
        this.width = width;
        this.height = height;
        this.setTitle(title);
    }

    public void draw(Drawable... objects){
        for (var object : objects){
            object.draw(context);
        }
    }

    public StackPane getPane() {
        return pane;
    }

    public Canvas getCanvas(){
        return this.canvas;
    }
    public void clear(){
        this.context.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
}
