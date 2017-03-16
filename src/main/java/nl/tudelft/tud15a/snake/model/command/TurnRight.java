package nl.tudelft.tud15a.snake.model.command;

import nl.tudelft.tud15a.snake.model.Snake;

public class TurnRight implements Command {
	Snake snake;

	public TurnRight(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.moveRight();
	}
}
