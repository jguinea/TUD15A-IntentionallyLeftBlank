package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.model.Snake;

public class SlowDown implements Command {
	Snake snake;

	public SlowDown(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.setSpeed(snake.getSpeed() - 30);
	}
}
