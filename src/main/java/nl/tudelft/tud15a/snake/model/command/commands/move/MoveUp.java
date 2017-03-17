package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public class GoUp implements Command {
    private Snake snake;

    public GoUp(Snake snake) {
        this.snake = snake;
    }

    public void execute() {
        snake.moveUp();
    }
}
