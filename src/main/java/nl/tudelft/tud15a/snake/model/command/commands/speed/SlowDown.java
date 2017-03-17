package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.SpeedController;
import nl.tudelft.tud15a.snake.model.command.Command;

public class SlowDown implements Command {
    private SpeedController speedController;

    public SlowDown(SpeedController speedController) {
        this.speedController = speedController;
    }

    public void execute() {
        speedController.setSpeed(speedController.getSpeed() + 30);
    }
}
