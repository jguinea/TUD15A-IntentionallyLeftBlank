package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public abstract class Fruit {
    private Position position;
    private int boardSize = Settings.WIDTH;

    public Fruit() {
        position = new Position();
        locate();
    }
    
    public Position getPosition() {
        return position;
    }

    public void locate() {
        int r = 1 + (int) (Math.random() * (boardSize / Settings.CELL_SIZE - 3));
        position.setX(r * Settings.CELL_SIZE);


        r = 1 + (int) Math.round((Math.random()* (boardSize / Settings.CELL_SIZE - 3)));
        position.setY(r * Settings.CELL_SIZE);
    }

    abstract public int getPoints();
}
