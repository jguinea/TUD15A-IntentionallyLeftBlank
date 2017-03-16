package nl.tudelft.tud15a.snake.model.command;

import nl.tudelft.tud15a.snake.model.command.commands.NoCommand;

public class MovementControl {
	Command snakeCommands[];
	Command noCommand = new NoCommand();

	public MovementControl(int size) {
		snakeCommands = new Command[size];
		for(int i=0; i<size; i++) {
			snakeCommands[i] = noCommand;
		}
	}

	public void setCommand(int i, Command c) {
		snakeCommands[i] = c;
	}

	public void pressOnButton(int buttonNum) {
		snakeCommands[buttonNum].execute(); 
	}
}
