package nl.tudelft.tud15a.snake.model.command;

import nl.tudelft.tud15a.snake.model.SpeedController;

public class SlowDown implements Command {
	SpeedController speedController;

	public SlowDown(SpeedController speedController) {
		this.speedController = speedController;
	}

	public void execute() {
		speedController.setSpeed(speedController.getSpeed() + 30);
	}
}
