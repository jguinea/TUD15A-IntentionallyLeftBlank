package nl.tudelft.tud15a.snake.model;

import java.util.ArrayList;
import java.util.List;

import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class Snake implements CollisionListener {
    public List<Position> position = new ArrayList<>(Settings.ALL_CELLS);
    private Direction direction;
    private int points;
    private int size;
    private Model model;

    public Snake(Model model) {
        this.model = model;
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

    public void move(Direction direction) {
        this.direction = direction;

        for (int z = size - 1; z > 0; z--) {
            position.get(z).setX(position.get(z - 1).getX());
            position.get(z).setY(position.get(z - 1).getY());
        }

        if (direction == direction.LEFT) {
            getHead().setX(getHead().getX() - Settings.CELL_SIZE);
        }

        if (direction == direction.RIGHT) {
            getHead().setX(getHead().getX() + Settings.CELL_SIZE);
        }

        if (direction == direction.UP) {
            getHead().setY(getHead().getY() - Settings.CELL_SIZE);
        }

        if (direction == direction.DOWN) {
            getHead().setY(getHead().getY() + Settings.CELL_SIZE);
        }
    }

    public boolean isEatingYourself() {
        for (int z = size - 1; z > 0; z--) {
            if (getHead().getX() == position.get(z).getX() && (getHead().getY() == position.get(z).getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCollision(CollisionReason reason) {
        if(reason == CollisionReason.EAT_FRUIT) {
            eatApple(model.getFruit());
        }
    }

    public int getPoint() {
        return points;
    }

    public Direction getDirection() {
        return direction;
    }
}
