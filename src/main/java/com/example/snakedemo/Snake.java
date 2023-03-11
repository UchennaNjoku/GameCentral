package com.example.snakedemo;

import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class Snake implements Drawable{

    private final List<Point2D> body;
    private Point2D velocity;
    private final double cellWidth = GameSurface.cellWidth;

    private boolean addBlock = false;

    public Color snakeColor = Color.valueOf("758E4F");

    public Snake(){
        this.body = new LinkedList<>();
        this.setUp();
    }

    public void setUp(){
        double xMidOfBoard = (double) GameSurface.numCols/2;
        double yMidOfBoard = (double) GameSurface.numRows/2;

        this.body.add(new Point2D(xMidOfBoard, yMidOfBoard));
        this.body.add(new Point2D(xMidOfBoard - 1, yMidOfBoard));
        this.velocity = new Point2D(1, 0);
    }
    public synchronized void move(){
        Point2D newHead = new Point2D(this.body.get(0).getX() + this.velocity.getX(), this.body.get(0).getY() + this.velocity.getY());

        if (newHead.getX() == GameSurface.numRows){
            newHead = newHead.subtract(GameSurface.numRows, 0);
        }else if (newHead.getX() < 0){
            newHead = newHead.add(GameSurface.numRows, 0);
        }
        // need to validate if this is correct
        // check if directions are valid or inverted
        if (newHead.getY() == GameSurface.numCols){
            newHead = newHead.subtract(0, GameSurface.numCols);
        }else if (newHead.getY() < 0){
            newHead = newHead.add(0, GameSurface.numCols);
        }

        if (!addBlock){
            this.body.remove(body.size()-1);
        }
        this.body.add(0, newHead);
        this.addBlock = false;
    }

    public void setVelocity(Point2D velocity){
        if (this.velocity.equals(velocity)){
            return;
        }
        this.velocity = null;
        this.velocity = velocity;
    }

    public Point2D getVelocity(){
        return this.velocity;
    }
    public List<Point2D> getBody(){
        return this.body;
    }
    public Point2D getHead(){
        return this.getBody().get(0);
    }
    public void setAddBlockToTrue(){
        this.addBlock = true;
    };
    public void reset(){
        this.body.clear();
        this.setUp();
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(snakeColor);
        for (Point2D point2D : this.body) {
            double x = point2D.getX();
            double y = point2D.getY();
            context.fillRect(x * cellWidth, y * cellWidth, cellWidth, cellWidth);
        }
    }
}
