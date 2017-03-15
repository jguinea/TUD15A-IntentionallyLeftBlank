package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.decorator.Golden;

public class Model {
    Snake snake;
    private Fruit fruit;
    private State gameState = State.START_SCREEN;

    public Model() {
        snake = new Snake();
        fruit = new Apple();
    }

    public void checkApple() {

        if ((snake.getHead().getX() == fruit.getPosition().getX()) && (snake.getHead().getY() == fruit.getPosition().getY())) {

            snake.eatApple(fruit);
            if(Math.random() > 0.8) {
            	fruit = new Golden(new Apple());
            } else {
                fruit = new Apple();
            }
            fruit.locate();
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

    public Fruit getFruit() {
        return fruit;
    }
}
