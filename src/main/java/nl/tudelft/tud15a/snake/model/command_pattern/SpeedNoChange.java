package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.SpeedController;
import nl.tudelft.tud15a.snake.model.Snake;

public class SpeedNoChange implements Command {
	SpeedController speedController;

	public SpeedNoChange(SpeedController speedController) {
		this.speedController = speedController;
	}

	public void execute() {
	}
}
