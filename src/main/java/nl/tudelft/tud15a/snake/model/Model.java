package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionObservable;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class Model extends CollisionObservable {
    private Snake snake;
    private Fruit fruit;
    private State gameState = State.START_SCREEN;

    public Model(CollisionListener timerListener) {
        snake = new Snake(this);
        fruit = new Apple();

        this.addListener(snake);
        this.addListener(new FruitRNG(this));
        this.addListener(timerListener);
    }

    public void checkCollisions() {
        checkApple();
        checkGameOver();
    }

    private void checkApple() {
        if ((snake.getHead().getX() == fruit.getPosition().getX()) && (snake.getHead().getY() == fruit.getPosition().getY())) {
            collision(CollisionReason.EAT_FRUIT);
        }
    }

    private void checkGameOver() {
        if (snake.isEatingYourself()
                || snake.getHead().getY() >= Settings.HEIGHT - Settings.BORDER_THICKNESS
                || snake.getHead().getY() < Settings.BORDER_THICKNESS
                || snake.getHead().getX() >= Settings.WIDTH - Settings.BORDER_THICKNESS
                || snake.getHead().getX() < Settings.BORDER_THICKNESS) {
            gameState = State.GAME_OVER;
            collision(CollisionReason.GAME_OVER);
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

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }
}
