package nl.tudelft.tud15a.snake.model;

import javax.swing.ImageIcon;

public class Apple {
    private Position position;

    private final int CELL_SIZE = 10;
    private int boardSize = Settings.WIDTH;

    public Apple() {
    	position = new Position();
    	locate();
    	boardSize = Settings.WIDTH;
    	
    	
    }

    public Position getPosition() {
    	return position;
    }

    public void locate() {
        int r = 1 + (int)(Math.random()*(boardSize/CELL_SIZE-2));
        position.setX(r*CELL_SIZE);
        
        

        r = 1+(int)Math.round((Math.random()*(boardSize/CELL_SIZE-2)));
        position.setY(r*CELL_SIZE);
    }
}
