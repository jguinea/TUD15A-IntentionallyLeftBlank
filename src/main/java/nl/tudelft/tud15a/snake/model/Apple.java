package nl.tudelft.tud15a.snake.model;

public class Apple {
    private Position position;
    private int points;

    private int boardSize = Settings.WIDTH;

    public Apple() {
    	points = 10;
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

    public int getPoints() {
    	return points;
    }
}
