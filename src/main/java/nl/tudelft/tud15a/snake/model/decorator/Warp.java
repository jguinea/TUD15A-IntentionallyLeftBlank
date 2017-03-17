package nl.tudelft.tud15a.snake.model.decorator;

public class Warp extends Decorator {

    public Warp(Fruit fruit) {
        super(fruit);
        combineBlur(fruit);
    }
}
