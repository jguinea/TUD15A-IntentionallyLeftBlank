package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.command_pattern.GoDown;
import nl.tudelft.tud15a.snake.model.command_pattern.GoUp;
import nl.tudelft.tud15a.snake.model.command_pattern.MovementControl;
import nl.tudelft.tud15a.snake.model.command_pattern.SlowDown;
import nl.tudelft.tud15a.snake.model.command_pattern.SpeedNoChange;
import nl.tudelft.tud15a.snake.model.command_pattern.SpeedUp;
import nl.tudelft.tud15a.snake.model.command_pattern.TurnLeft;
import nl.tudelft.tud15a.snake.model.command_pattern.TurnRight;
import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.decorator.Golden;

public class Model {
	MovementControl movementControl;
    Snake snake;
    private Fruit fruit;
    private State gameState = State.START_SCREEN;

    public Model() {
    	movementControl = new MovementControl(7);
        snake = new Snake();
        fruit = new Apple();
        movementControl.setCommand(Direction.valueOf("LEFT").getIndex(), new TurnLeft(snake));
        movementControl.setCommand(Direction.valueOf("RIGHT").getIndex(), new TurnRight(snake));
        movementControl.setCommand(Direction.valueOf("UP").getIndex(), new GoUp(snake));
    	movementControl.setCommand(Direction.valueOf("DOWN").getIndex(), new GoDown(snake));
    	movementControl.setCommand(Speed.valueOf("SPEEDUP").getIndex(), new SpeedUp(snake));
    	movementControl.setCommand(Speed.valueOf("SLOWDOWN").getIndex(), new SlowDown(snake));
    	movementControl.setCommand(Speed.valueOf("NOCHANGE").getIndex(), new SpeedNoChange(snake));
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

    public MovementControl getMovementControl() {
    	return movementControl;
    }
}
