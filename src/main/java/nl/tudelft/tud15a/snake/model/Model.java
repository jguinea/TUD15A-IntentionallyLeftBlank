package nl.tudelft.tud15a.snake.model;

import javax.swing.Timer;

public class Model {
    Snake snake;
    private Apple apple;
    private State inGame = State.START_SCREEN;

    public Model() {

        snake = new Snake();
        apple = new Apple();
    }

    public void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple();

            apple.locate();
        }
    }

    public void checkCollision() {

        if(snake.isEatingYourself()) {
        	inGame = State.GAME_OVER;;
        }

        if (snake.getHead().getY() >= Constants.HEIGHT) {
            inGame = State.GAME_OVER;;
        }

        if (snake.getHead().getY() < 0) {
            inGame = State.GAME_OVER;;
        }

        if (snake.getHead().getX() >= Constants.WIDTH) {
            inGame = State.GAME_OVER;;
        }

        if (snake.getHead().getX() < 0) {
            inGame = State.GAME_OVER;;
        }

    }

    public State getState() {
    	return inGame;
    }

    public void setState(State state) {
    	inGame = state;
    }

    public Snake getSnake() {
    	return snake;
    }

    public Apple getApple() {
    	return apple;
    }
}
