package nl.tudelft.tud15a.snake.model;

public class Apple {
    private Position position;

    private final int CELL_SIZE = 10;

    public Apple() {
    	position = new Position();
    	locate();
    }

    public Position getPosition() {
    	return position;
    }

    public void locate() {
        int r = (int) (Math.random() * 29);
        position.setX((r * CELL_SIZE));

        r = (int) (Math.random() * 29);
        position.setY((r * CELL_SIZE));
    }
}
