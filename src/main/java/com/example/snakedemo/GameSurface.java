package com.example.snakedemo;

import javafx.print.PageLayout;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameSurface implements Drawable {


    public static int numRows;
    public static int numCols;
    public static double cellWidth;
    public GameSurface(){
        numRows = 14;
        numCols = 14;
        cellWidth = Constants.WINDOW_WIDTH / numRows;
    }

    public GameSurface(int numRows, int numCols){
        GameSurface.numRows = numRows;
        GameSurface.numCols = numCols;
        cellWidth = Constants.WINDOW_WIDTH / numRows;
    }

    @Override
    public void draw(GraphicsContext context) {

        for (int r = 0; r < numRows; r++){
            for (int c = 0; c < numCols; c++){
                if ((r + c) % 2 == 0){
                    context.setFill(Color.valueOf("F19953"));
                }else{
                    context.setFill(Color.valueOf("C47335"));
                }
                context.fillRect(c * cellWidth ,r * cellWidth, cellWidth, cellWidth);
            }
        }


    }
}
