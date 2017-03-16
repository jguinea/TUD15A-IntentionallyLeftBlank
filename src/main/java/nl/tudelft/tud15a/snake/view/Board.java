package nl.tudelft.tud15a.snake.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import nl.tudelft.tud15a.snake.model.Direction;
import nl.tudelft.tud15a.snake.model.Model;
import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;
import nl.tudelft.tud15a.snake.model.Speed;
import nl.tudelft.tud15a.snake.model.State;
import nl.tudelft.tud15a.snake.model.observer.CollisionListener;
import nl.tudelft.tud15a.snake.model.observer.CollisionReason;

public class Board extends JPanel implements ActionListener, CollisionListener {
    private Model model = new Model(this);

    private Direction direction;
    private Direction prevDirection;
    private Speed speed;

    private Timer timer;
    private Image bodyImage;
    private Image headImage;

    public Board() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLUE);
        setFocusable(true);

        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        bodyImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg").getImage();
        headImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/head.jpg").getImage();
    }

    private void initGame() {
        model = new Model(this);
        direction = Direction.RIGHT;
        prevDirection = Direction.RIGHT;
        speed = Speed.NOCHANGE;
        timer = new Timer(model.getSpeedController().getSpeed(), this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        switch (model.getState()) {
            case START_SCREEN:
                startScreen(g);
                break;
            case PLAYING:
                play(g);
                break;
            case GAME_OVER:
                gameOver(g);
                break;
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        String msg2 = "\nScore: " + model.getSnake().getPoint();
        String msg3 = "Click Spacebar to Restart the Game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        g.drawString(msg, (Settings.WIDTH - metr.stringWidth(msg)) / 2, (Settings.HEIGHT / 2) - 15);
        g.drawString(msg2, (Settings.WIDTH - metr.stringWidth(msg2)) / 2, (Settings.HEIGHT / 2));
        g.drawString(msg3, (Settings.WIDTH - metr.stringWidth(msg3)) / 2, (Settings.HEIGHT / 2) + 25);
    }


    private void startScreen(Graphics g) {
        String msg = "WELCOME TO THE BEST SNAKE";
        String msg3 = "Click Spacebar to start your game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        g.drawString(msg, (Settings.WIDTH - metr.stringWidth(msg)) / 2, (Settings.HEIGHT / 2) - 25);
        g.drawString(msg3, (Settings.WIDTH - metr.stringWidth(msg3)) / 2, (Settings.HEIGHT / 2) + 15);
    }

    private void play(Graphics g) {
        String scores = "Score: " + model.getSnake().getPoint();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(scores, Settings.WIDTH - 80, Settings.HEIGHT - 10);

        //Borders of the Fields
        g.setColor(Color.GREEN);
        g.drawRect(0, 0, Settings.WIDTH, Settings.BORDER_THICKNESS);
        g.drawRect(0, Settings.HEIGHT - Settings.BORDER_THICKNESS, Settings.WIDTH, Settings.BORDER_THICKNESS);
        g.drawRect(0, 0, Settings.BORDER_THICKNESS, Settings.HEIGHT);
        g.drawRect(Settings.WIDTH - Settings.BORDER_THICKNESS, 0, Settings.BORDER_THICKNESS, Settings.HEIGHT);
        
        g.drawImage(model.getFruit().getImage(), model.getFruit().getPosition().getX(),
                model.getFruit().getPosition().getY(), this);

        for (Position pos : model.getSnake().getPosition()) {
            g.drawImage(bodyImage, pos.getX(), pos.getY(), this);
        }
        g.drawImage(headImage, model.getSnake().getHead().getX(), model.getSnake().getHead().getY(), this);

        Toolkit.getDefaultToolkit().sync();
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

        repaint();
    }

    @Override
    public void onCollision(CollisionReason reason) {
        if (reason == CollisionReason.GAME_OVER) {
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (model.getState() == State.GAME_OVER || model.getState() == State.START_SCREEN) {
                if ((key == KeyEvent.VK_SPACE)) {
                    initGame();
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
