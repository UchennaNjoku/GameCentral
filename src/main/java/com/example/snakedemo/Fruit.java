package com.example.snakedemo;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;

import java.util.Random;

public class Fruit implements Drawable{
    private Point2D position;
    public int numRows = GameSurface.numRows;
    public double cellWidth = GameSurface.cellWidth;

    public Fruit(){
        position = getRandomCoordinates();
    }

    public Point2D getRandomCoordinates(){
        Random random = new Random();

        int xCoordinate = random.nextInt(numRows);
        int yCoordinate = random.nextInt(numRows);

        return new Point2D(xCoordinate, yCoordinate);
    }

    public Point2D getPosition(){
        return this.position;
    }

    public void moveFruit(){
        position = this.getRandomCoordinates();
    }
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(Color.valueOf("F7F0F5"));
        context.fillRect(position.getX() * cellWidth, position.getY() * cellWidth, cellWidth, cellWidth);
    }
}
