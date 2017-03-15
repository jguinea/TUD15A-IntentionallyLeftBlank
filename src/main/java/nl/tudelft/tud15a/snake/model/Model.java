package nl.tudelft.tud15a.snake.model;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Model {
    Snake snake;
    private Apple apple;
    private State inGame = State.START_SCREEN;
    private int borderThickness=5;
    private ImageIcon ic;

    public Model() {

        snake = new Snake();
        apple = new Apple();
        ic = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg");
        borderThickness = ic.getIconHeight();
    }

    public void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple(apple);

            apple.locate();
        }
    }

    public void checkCollision() {

        if(snake.isEatingYourself()) {
        	inGame = State.GAME_OVER;;
        }


        if (snake.getHead().getY() >= Settings.HEIGHT-this.borderThickness) {
            inGame = State.GAME_OVER;

        }

        if (snake.getHead().getY() < this.borderThickness) {
            inGame = State.GAME_OVER;

        }


        if (snake.getHead().getX() >= Settings.WIDTH-this.borderThickness) {
            inGame = State.GAME_OVER;


        }

        if (snake.getHead().getX() < this.borderThickness) {
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
