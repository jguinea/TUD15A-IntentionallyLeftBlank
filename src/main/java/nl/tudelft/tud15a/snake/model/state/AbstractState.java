package nl.tudelft.tud15a.snake.model.state;

import nl.tudelft.tud15a.snake.Board;

/**
 * @author Ondrej Musil
 */
public class AbstractState implements StateInterface {

    protected Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void handleUp() {

    }

    @Override
    public void handleDown() {

    }

    @Override
    public void handleLeft() {

    }

    @Override
    public void handleRight() {

    }

    @Override
    public void handleSpace() {

    }
}
