package com.example.snakedemo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.geometry.Point2D;

import java.util.List;

public class MainGame {
    // variables
    private Timeline gameLoop;
    private Window window;
    private Fruit fruit;
    private Snake snake;
    private GameSurface gameSurface;
    private StackPane gameOverPane;
    private Score score;

    private boolean gameOver;

    public MainGame(){
        startup();
    }

    private void startup(){
        window = new Window(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, "Snek?");
        gameSurface = new GameSurface();
        this.gameOver = false;
        fruit = new Fruit();
        snake = new Snake();
        score = new Score();
        this.eventListener();
    }

    private void checkLoss(){
        List<Point2D> body = this.snake.getBody();
        Point2D head = this.snake.getHead();

        for (int i = 1; i < body.size(); i++){
            if ((head.getX() == body.get(i).getX()) && (head.getY() == body.get(i).getY())){
                gameOver = true;
                break;
            }
        }
    }

    private void eventListener(){
        window.getCanvas().addEventFilter(KeyEvent.KEY_PRESSED, (event)->{
            var direction = this.snake.getVelocity();

            switch (event.getCode()){
                case LEFT -> {
                    if (direction.getX() != 1)
                        snake.setVelocity(new Point2D(-1 , 0));
                }
                case RIGHT -> {
                    if (direction.getX() != -1)
                        snake.setVelocity(new Point2D(1 , 0));
                }
                case UP -> {
                    if (direction.getY() != 1)
                        snake.setVelocity(new Point2D(0 , -1));
                }
                case DOWN -> {
                    if (direction.getY() != -1)
                        snake.setVelocity(new Point2D(0 , 1));
                }
                case SPACE -> {
                    if (this.gameOver){
                        startOver();
                    }
                }
            }
        });
        window.getCanvas().setFocusTraversable(true);
    }

    private void startOver(){
        this.snake.reset();
        this.score.resetCurrentScore();
        this.fruit.moveFruit();
        this.gameOver = false;
        this.gameLoop.playFromStart();
        window.getPane().getChildren().remove(gameOverPane);
    }

    private void checkFoodEaten(){
        Point2D fruitPos = fruit.getPosition();
        if (snake.getHead().getX() == fruitPos.getX() && snake.getHead().getY() == fruitPos.getY()){
            snake.setAddBlockToTrue();
            this.fruit.moveFruit();
            this.score.incrementScore();
        }
    }

    private void clear(){
        window.clear();
    }
    private void update(){
        this.snake.move();
        this.checkFoodEaten();
        this.checkLoss();
        if (gameOver) {
            gameLost();
            gameLoop.pause();
        }
    }
    private void render(){
        // draw the gameSurface and score
        window.draw(gameSurface);
        // draw snake and the random fruit
        window.draw(fruit);
        window.draw(snake);
        window.draw(score);
    }
    public void run(){
        gameLoop = new Timeline();
        gameLoop.getKeyFrames().add(new KeyFrame(Duration.millis(60.0), (event)->{
            clear();
            render();
            update();
        }));
        window.show();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    public void gameLost(){
        GameOverPane paneClass = new GameOverPane();
        gameOverPane = paneClass.drawPane();
        window.getPane().getChildren().add(gameOverPane);
    }
}

