package nl.tudelft.tud15a.snake.model.decorator;

public class Golden extends Decorator {
	public Golden(Fruit fruit) {
		super(fruit);
	}

	public int getPoints() {
		return fruit.getPoints() * 2;
	}
}
