package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public class TurnLeft implements Command {
    Snake snake;

    public TurnLeft(Snake snake) {
        this.snake = snake;
    }

    public void execute() {
        snake.moveLeft();
    }
}
