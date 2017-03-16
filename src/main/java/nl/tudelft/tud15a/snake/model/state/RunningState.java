package nl.tudelft.tud15a.snake.model.state;

import nl.tudelft.tud15a.snake.model.Direction;

/**
 * @author Ondrej Musil
 */
public class RunningState extends AbstractState {
    @Override
    public void handleUp() {
        if (board.getSnake().getDirection() != Direction.DOWN) {
            board.setDirection(Direction.UP);
        }
    }

    @Override
    public void handleDown() {
        if (board.getSnake().getDirection() != Direction.UP) {
            board.setDirection(Direction.DOWN);
        }
    }

    @Override
    public void handleLeft() {
        if (board.getSnake().getDirection() != Direction.RIGHT) {
            board.setDirection(Direction.LEFT);
        }
    }

    @Override
    public void handleRight() {
        if (board.getSnake().getDirection() != Direction.LEFT) {
            board.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void handleSpace() {

    }
}
