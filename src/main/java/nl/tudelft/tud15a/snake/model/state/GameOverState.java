package nl.tudelft.tud15a.snake.model.state;

/**
 * @author Ondrej Musil
 */
public class GameOverState extends AbstractState implements StateInterface {

    @Override
    public void handleSpace() {
        board.initGame();
        AbstractState newState = new RunningState();
        newState.setBoard(board);
        board.setGameState(newState);
    }
}
