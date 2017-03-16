package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.model.Snake;

public class GoDown implements Command {
	Snake snake;

	public GoDown(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.moveDown();
	}
}
