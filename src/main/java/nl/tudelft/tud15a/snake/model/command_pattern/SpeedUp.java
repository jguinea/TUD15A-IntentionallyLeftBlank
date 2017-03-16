package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.model.Snake;

public class SpeedUp implements Command {
	Snake snake;

	public SpeedUp(Snake snake) {
		this.snake = snake;
	}

	public void execute() {
		snake.setSpeed(snake.getSpeed() + 30);
	}
}
