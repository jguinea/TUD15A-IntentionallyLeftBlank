package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.command.Command;

public abstract class Metal extends Decorator {
	//abstract Class where the points are multiplied !!
	
	protected int pointsMultiplier;
	Metal(Fruit fruit) {
		super(fruit);
		// TODO Auto-generated constructor stub
		
		
	}

	public int getPoints(){
		return 0;
	}
	
	public int getPointsMultiplier(){
		return this.pointsMultiplier;
	}
	
	

}
