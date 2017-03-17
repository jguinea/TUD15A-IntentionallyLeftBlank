package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public class GoDown implements Command {
    private Snake snake;

    public GoDown(Snake snake) {
        this.snake = snake;
    }

    public void execute() {
        snake.moveDown();
    }
}
