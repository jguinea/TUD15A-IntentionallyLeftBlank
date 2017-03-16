package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.command.GoDown;
import nl.tudelft.tud15a.snake.model.command.GoUp;
import nl.tudelft.tud15a.snake.model.command.MovementControl;
import nl.tudelft.tud15a.snake.model.command.SlowDown;
import nl.tudelft.tud15a.snake.model.command.SpeedNoChange;
import nl.tudelft.tud15a.snake.model.command.SpeedUp;
import nl.tudelft.tud15a.snake.model.command.TurnLeft;
import nl.tudelft.tud15a.snake.model.command.TurnRight;
import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionObservable;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class Model extends CollisionObservable {
	private MovementControl movementControl;
    private Snake snake;
    private Fruit fruit;
    private SpeedController speedController;
    
    private State gameState = State.START_SCREEN;

    public Model(CollisionListener timerListener) {
    	movementControl = new MovementControl(7);
        snake = new Snake(this);
        fruit = new Apple();
        speedController = new SpeedController();
        
        movementControl.setCommand(Direction.valueOf("LEFT").getIndex(), new TurnLeft(snake));
        movementControl.setCommand(Direction.valueOf("RIGHT").getIndex(), new TurnRight(snake));
        movementControl.setCommand(Direction.valueOf("UP").getIndex(), new GoUp(snake));
    	movementControl.setCommand(Direction.valueOf("DOWN").getIndex(), new GoDown(snake));
    	movementControl.setCommand(Speed.valueOf("SPEEDUP").getIndex(), new SpeedUp(speedController));
    	movementControl.setCommand(Speed.valueOf("SLOWDOWN").getIndex(), new SlowDown(speedController));
    	movementControl.setCommand(Speed.valueOf("NOCHANGE").getIndex(), new SpeedNoChange(speedController));

        this.addListener(snake);
        this.addListener(new FruitRNG(this));
        this.addListener(timerListener);

    }

    public void checkCollision() {
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

    public MovementControl getMovementControl() {
    	return movementControl;
    }

    public SpeedController getSpeedController() {
    	return speedController;
    }
}
