package nl.tudelft.tud15a.snake.model;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.tud15a.snake.model.decorator.Fruit;

public class Snake {
    public List<Position> position = new ArrayList<>(Settings.ALL_CELLS);
    private int points;
    private int size;
    private int speed;

    public Snake() {
    	speed = 130;
    	points = 0;
        size = 3;
        for (int z = 0; z < size; z++) {
            position.add(new Position(50 - z * 10, 50));
        }
    }

    public int getSize() {
        return size;
    }

    public void eatApple(Fruit fruit) {
        size++;
        points += fruit.getPoints();
        position.add(new Position(0, 0));
    }

    public List<Position> getPosition() {
        return position;
    }

    public Position getHead() {
        return position.get(0);
    }

    private void moveBody() {
        for (int z = size - 1; z > 0; z--) {
            position.get(z).setX(position.get(z - 1).getX());
            position.get(z).setY(position.get(z - 1).getY());
        }
    }

    public void moveLeft() {
    	moveBody();
    	getHead().setX(getHead().getX() - Settings.CELL_SIZE);
    }

    public void moveRight() {
    	moveBody();
    	getHead().setX(getHead().getX() + Settings.CELL_SIZE);
    }

    public void moveUp() {
    	moveBody();
    	getHead().setY(getHead().getY() - Settings.CELL_SIZE);
    }

    public void moveDown() {
    	moveBody();
    	getHead().setY(getHead().getY() + Settings.CELL_SIZE);
    }

    public boolean isEatingYourself() {
        for (int z = size - 1; z > 0; z--) {
            if (getHead().getX() == position.get(z).getX() && (getHead().getY() == position.get(z).getY())) {
                return true;
            }
        }
        return false;
    }

    public int getPoint() {
        return points;
    }

    public int getSpeed() {
    	return this.speed;
    }

    public void setSpeed(int speed) {
    	this.speed = speed;
    }
}
