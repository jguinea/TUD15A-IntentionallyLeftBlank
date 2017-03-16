package nl.tudelft.tud15a.snake.model.state;

/**
 * @author Ondrej Musil
 */
public interface StateInterface {

    public void handleUp();

    public void handleDown();

    public void handleLeft();

    public void handleRight();

    public void handleSpace();

}
