package nl.tudelft.tud15a.snake.model;

public class Apple {
    private Position position;

    private int boardSize = Settings.WIDTH;

    public Apple() {
        position = new Position();
        locate();
    }

    public Position getPosition() {
        return position;
    }

    public void locate() {
        int r = 1 + (int) (1 * (boardSize / Settings.CELL_SIZE - 3));
        position.setX(r * Settings.CELL_SIZE);


        r = 1 + (int) Math.round((0.999999999* (boardSize / Settings.CELL_SIZE - 2)));
        position.setY(r * Settings.CELL_SIZE);
    }
}
