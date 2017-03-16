package nl.tudelft.tud15a.snake.model.command;

import nl.tudelft.tud15a.snake.model.Snake;

public class TurnLeft implements Command {
	Snake snake;

	public TurnLeft(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.moveLeft();
	}
}
