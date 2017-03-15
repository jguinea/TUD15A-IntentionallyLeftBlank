package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public abstract class Decorator implements Fruit {
	protected Fruit fruit;

	Decorator(Fruit fruit) {
		this.fruit =fruit;
	}

    public Position getPosition() {
        return fruit.getPosition();
    }

    public void locate() {
        fruit.locate();
    }

	public abstract int getPoints();
}
