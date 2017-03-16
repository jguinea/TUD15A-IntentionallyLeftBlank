package nl.tudelft.tud15a.snake.model.command_pattern;

import nl.tudelft.tud15a.snake.SpeedController;
import nl.tudelft.tud15a.snake.model.Snake;

public class SpeedUp implements Command {
	SpeedController speedController;

	public SpeedUp(SpeedController speedController) {
		this.speedController = speedController;
	}

	public void execute() {
		speedController.setSpeed(speedController.getSpeed() - 30);
	}
}
