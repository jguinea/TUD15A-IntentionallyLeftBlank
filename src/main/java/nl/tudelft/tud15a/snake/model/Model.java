package nl.tudelft.tud15a.snake.model;

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

            snake.eatApple(apple);

            apple.locate();
        }
    }

    public void checkCollision() {
        if (snake.isEatingYourself()) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() >= Settings.HEIGHT - Settings.BORDER_THICKNESS) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() < Settings.BORDER_THICKNESS) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getX() >= Settings.WIDTH - Settings.BORDER_THICKNESS) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getX() < Settings.BORDER_THICKNESS) {
            inGame = State.GAME_OVER;
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
