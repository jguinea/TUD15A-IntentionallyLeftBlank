package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.decorator.Apple;

public class Model {
    Snake snake;
    private Apple apple;
    private State gameState = State.START_SCREEN;

    public Model() {
        snake = new Snake();
        apple = new Apple();
    }

    public void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple(apple);

            apple.locate();
        }
    }

    public void checkCollision() {
        if (snake.isEatingYourself()) {
        	gameState = State.GAME_OVER;
        }

        if (snake.getHead().getY() >= Settings.HEIGHT - Settings.BORDER_THICKNESS) {
        	gameState = State.GAME_OVER;
        }

        if (snake.getHead().getY() < Settings.BORDER_THICKNESS) {
        	gameState = State.GAME_OVER;
        }

        if (snake.getHead().getX() >= Settings.WIDTH - Settings.BORDER_THICKNESS) {
        	gameState = State.GAME_OVER;
        }

        if (snake.getHead().getX() < Settings.BORDER_THICKNESS) {
        	gameState = State.GAME_OVER;
        }
    }

    public State getState() {
        return gameState;
    }

    public void setState(State state) {
    	gameState = state;
    }

    public Snake getSnake() {
        return snake;
    }

    public Apple getApple() {
        return apple;
    }
}
