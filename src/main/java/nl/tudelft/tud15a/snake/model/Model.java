package nl.tudelft.tud15a.snake.model;

import javax.swing.Timer;

public class Model {
    Snake snake;
    private Apple apple;
    private boolean inGame = true;

    public Model() {

        snake = new Snake();
        apple = new Apple();
    }

    private void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple();

            apple.locate();
        }
    }

    private void checkCollision() {

        if(snake.isEatingYourself()) {
        	inGame = false;
        }

        if (snake.getHead().getY() >= Constants.HEIGHT) {
            inGame = false;
        }

        if (snake.getHead().getY() < 0) {
            inGame = false;
        }

        if (snake.getHead().getX() >= Constants.WIDTH) {
            inGame = false;
        }

        if (snake.getHead().getX() < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            //timer.stop();
        }
    }

}
