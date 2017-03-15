package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public class Apple extends Fruit {
    private int points;

    public Apple() {
    	super();
    	points = 10;
    }

    public int getPoints() {
    	return points;
    }
}
