package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;

public abstract class Decorator extends Fruit {
	protected Fruit fruit;

	Decorator(Fruit fruit) {
		this.fruit =fruit;
	}

	public abstract int getPoints();
}
