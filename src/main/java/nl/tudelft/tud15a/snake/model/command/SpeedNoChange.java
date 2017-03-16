package nl.tudelft.tud15a.snake.model.command;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.SpeedController;

public class SpeedNoChange implements Command {
	SpeedController speedController;

	public SpeedNoChange(SpeedController speedController) {
		this.speedController = speedController;
	}

	public void execute() {
	}
}
