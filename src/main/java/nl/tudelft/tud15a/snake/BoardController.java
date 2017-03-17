package nl.tudelft.tud15a.snake;

import nl.tudelft.tud15a.snake.model.Direction;
import nl.tudelft.tud15a.snake.model.Model;
import nl.tudelft.tud15a.snake.model.Speed;
import nl.tudelft.tud15a.snake.model.State;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;
import nl.tudelft.tud15a.snake.view.BoardView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BoardController implements ActionListener, CollisionListener {

    private Model model = new Model(this);
    private BoardView view = new BoardView(new TAdapter());
    private Timer timer;

    private Direction direction;
    private Direction prevDirection;
    private Speed speed;

    public BoardController() {
        reset();
    }

    private void reset() {
        model = new Model(this);
        view.setModel(model);
        direction = Direction.RIGHT;
        prevDirection = Direction.RIGHT;
        speed = Speed.NOCHANGE;
        timer = new Timer(model.getSpeedController().getSpeed(), this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.getState() == State.PLAYING) {
            model.checkCollision();
            timer.setDelay(model.getSpeedController().getSpeed());

            if (model.getState() == State.GAME_OVER) {
                timer.stop();
            }
            model.getMovementControl().pressOnButton(direction.getIndex());
            model.getMovementControl().pressOnButton(speed.getIndex());
            prevDirection = direction;
            speed = Speed.NOCHANGE;
        }

        view.repaint();
    }

    @Override
    public void onCollision(CollisionReason reason) {
        if (reason == CollisionReason.GAME_OVER) {
            timer.stop();
        }
    }

    public BoardView getView() {
        return view;
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (model.getState() == State.GAME_OVER || model.getState() == State.START_SCREEN) {
                if ((key == KeyEvent.VK_SPACE)) {
                    reset();
                    model.setState(State.PLAYING);
                }
            }
            if ((key == KeyEvent.VK_LEFT) && (prevDirection != Direction.RIGHT)) {
                direction = Direction.LEFT;
            }

            if ((key == KeyEvent.VK_RIGHT) && (prevDirection != Direction.LEFT)) {
                direction = Direction.RIGHT;
            }

            if ((key == KeyEvent.VK_UP) && (prevDirection != Direction.DOWN)) {
                direction = Direction.UP;
            }

            if ((key == KeyEvent.VK_DOWN) && (prevDirection != Direction.UP)) {
                direction = Direction.DOWN;
            }

            if (key == KeyEvent.VK_W) {
                speed = Speed.SPEEDUP;
            }

            if (key == KeyEvent.VK_S) {
                speed = Speed.SLOWDOWN;
            }
        }
    }
}
