package nl.tudelft.tud15a.snake.model.command.commands;

import java.util.Random;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;
import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public class WarpCommand implements Command {
	private Snake snake;
	public WarpCommand(Snake snake){
		this.snake = snake;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int x = 3 +random.nextInt(Settings.WIDTH/Settings.CELL_SIZE - 5);
		int y = 3 +random.nextInt(Settings.HEIGHT/Settings.CELL_SIZE - 5);
		Position newPos = new Position(x*Settings.CELL_SIZE,y*Settings.CELL_SIZE);
		snake.setPosition(newPos);

	}

}
