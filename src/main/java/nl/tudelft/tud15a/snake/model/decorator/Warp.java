package nl.tudelft.tud15a.snake.model.decorator;

import java.util.Random;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;
import nl.tudelft.tud15a.snake.model.Snake;

public class Warp extends Decorator {
	
	public Warp(Fruit fruit, Snake snake) {
		super(fruit);
		// TODO Auto-generated constructor stub
		Random random = new Random();
		int x = 3 +random.nextInt(Settings.WIDTH/Settings.CELL_SIZE - 5);
		int y = 3 +random.nextInt(Settings.HEIGHT/Settings.CELL_SIZE - 5);
		Position newPos = new Position(x*Settings.CELL_SIZE,y*Settings.CELL_SIZE);
		snake.setPosition(newPos);
		
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return fruit.getPoints();
	}

}
