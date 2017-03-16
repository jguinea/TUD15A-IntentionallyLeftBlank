package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Snake;

public abstract class Metal extends Decorator {
	//abstract Class where the points are multiplied !!
	protected int snakePoint;
	Metal(Fruit fruit, int snakePoint) {
		super(fruit);
		// TODO Auto-generated constructor stub
		this.snakePoint = snakePoint;
		
	}

	@Override
	public abstract int getPoints();
	public void setSnakePoint(int snakePoint){
		this.snakePoint = snakePoint;
	}
	

}
