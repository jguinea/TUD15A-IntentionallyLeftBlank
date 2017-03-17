package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public class TurnRight implements Command {
    private Snake snake;

    public TurnRight(Snake snake) {
        this.snake = snake;
    }

    public void execute() {
        snake.moveRight();
    }
}
