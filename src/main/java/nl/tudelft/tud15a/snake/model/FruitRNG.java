package nl.tudelft.tud15a.snake.model;

import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.decorator.Golden;
import nl.tudelft.tud15a.snake.model.decorator.Warp;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class FruitRNG implements CollisionListener {
    private Model model;

    public FruitRNG(Model model) {
        this.model = model;
    }

    @Override
    public void onCollision(CollisionReason reason) {
        Fruit fruit;
        if(Math.random() > 0) {
            fruit = new Warp(new Apple(), model.getSnake());
        } else {
            fruit = new Apple();
        }
        model.setFruit(fruit);
        fruit.locate();
    }
}
