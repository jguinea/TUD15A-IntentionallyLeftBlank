package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;

public interface Fruit {
	Position getPosition();

	void locate();

	int getPoints();
}
