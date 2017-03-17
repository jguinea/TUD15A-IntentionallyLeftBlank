package nl.tudelft.tud15a.snake.model.command.commands;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;
import nl.tudelft.tud15a.snake.model.decorator.Metal;

public class MultiplyCommand implements Command {

	private Snake snake;
	// Bonus
	private Metal metal;
	public MultiplyCommand(Snake snake, Metal metal) {
		// TODO Auto-generated constructor stub
		this.snake = snake;
		this.metal = metal;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		snake.setPoint(snake.getPoint()*metal.getPointsMultiplier());
		
	}

}
