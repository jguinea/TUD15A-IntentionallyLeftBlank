package nl.tudelft.tud15a.snake.model.command;

import java.util.ArrayList;

import nl.tudelft.tud15a.snake.model.command.commands.NoCommand;

public class EffectController {
	ArrayList<Command> snakeCommands = new ArrayList<Command>();
	Command noCommand = new NoCommand();
	
	public void addCommand(Command command){
		snakeCommands.add(command);
	}
	public void executeAllCommand(){
		for (int i = 0; i < snakeCommands.size(); i++) {
			snakeCommands.get(i).execute();
		}
	}
	public void clear(){
		snakeCommands = new ArrayList<Command>();
	}
}
