package nl.tudelft.tud15a.snake.model.command;

import java.util.ArrayList;

public class EffectController {
    private ArrayList<Command> snakeCommands = new ArrayList<>();

    public void addCommand(Command command) {
        snakeCommands.add(command);
    }

    public void executeAllCommand() {
        snakeCommands.forEach(Command::execute);
    }

    public void clear() {
        snakeCommands.clear();
    }
}
