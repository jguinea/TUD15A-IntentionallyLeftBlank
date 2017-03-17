package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.SpeedController;
import nl.tudelft.tud15a.snake.model.command.Command;

public class SpeedNoChange implements Command {
    private SpeedController speedController;

    public SpeedNoChange(SpeedController speedController) {
        this.speedController = speedController;
    }

    public void execute() {}
}
