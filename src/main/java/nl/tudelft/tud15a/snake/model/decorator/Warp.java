package nl.tudelft.tud15a.snake.model.decorator;

import java.util.Random;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;
import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.EffectController;

public class Warp extends Decorator {
	
	public Warp(Fruit fruit) {
		super(fruit);
		// TODO Auto-generated constructor stub
		combineBlur(fruit);
		
		
	}

	


	

}
