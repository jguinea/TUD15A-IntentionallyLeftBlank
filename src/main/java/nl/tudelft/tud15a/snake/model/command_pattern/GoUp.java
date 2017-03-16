package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.model.Snake;

public class GoUp implements Command {
	Snake snake;

	public GoUp(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.moveUp();
	}
}
