package nl.tudelft.tud15a.snake.model;

public enum Direction {
    LEFT(0), RIGHT(1), UP(2), DOWN(3);

    int index;

    Direction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
