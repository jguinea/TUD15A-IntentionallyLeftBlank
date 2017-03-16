package nl.tudelft.tud15a.snake;

import nl.tudelft.tud15a.snake.model.Direction;
import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;
import nl.tudelft.tud15a.snake.model.Snake;
import nl.tudelft.tud15a.snake.model.decorator.Apple;
import nl.tudelft.tud15a.snake.model.decorator.Fruit;
import nl.tudelft.tud15a.snake.model.decorator.Golden;
import nl.tudelft.tud15a.snake.model.state.AbstractState;
import nl.tudelft.tud15a.snake.model.state.GameOverState;
import nl.tudelft.tud15a.snake.model.state.RunningState;
import nl.tudelft.tud15a.snake.model.state.StartState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
//    private Model model = new Model();

    private Snake snake;
    private Fruit fruit;
    private AbstractState gameState;

    private Direction direction;

    private Timer timer;
    private Image bodyImage;
    private Image appleImage;
    private Image headImage;
    public Board() {

        gameState = new StartState();

        addKeyListener(new TAdapter());
        setBackground(Color.BLUE);
        setFocusable(true);

        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        loadImages();
        initGame();
    }


    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setGameState(AbstractState gameState) {
        this.gameState = gameState;
    }

    public Snake getSnake() {
        return snake;
    }

    private void loadImages() {
        bodyImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg").getImage();
        headImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/head.jpg").getImage();
        appleImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/apple.png").getImage();
    }

    public void initGame() {
        snake = new Snake();
        fruit = new Apple();
        direction = Direction.RIGHT;
        gameState = new RunningState();
        gameState.setBoard(this);

        timer = new Timer(Settings.DELAY, this);
        timer.start();
    }

    public void checkApple() {

        if ((snake.getHead().getX() == fruit.getPosition().getX()) && (snake.getHead().getY() == fruit.getPosition().getY())) {

            snake.eatApple(fruit);
            if(Math.random() > 0.8) {
                fruit = new Golden(new Apple());
            } else {
                fruit = new Apple();
            }
            fruit.locate();
        }
    }

    public void checkCollision() {
        if (snake.isEatingYourself()) {
            gameOver();
        }

        if (snake.getHead().getY() >= Settings.HEIGHT - Settings.BORDER_THICKNESS) {
            gameOver();
        }

        if (snake.getHead().getY() < Settings.BORDER_THICKNESS) {
            gameOver();
        }

        if (snake.getHead().getX() >= Settings.WIDTH - Settings.BORDER_THICKNESS) {
            gameOver();
        }

        if (snake.getHead().getX() < Settings.BORDER_THICKNESS) {
            gameOver();
        }
    }

    private void gameOver() {
        gameState = new GameOverState();
        gameState.setBoard(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if (gameState instanceof StartState) {
            drawStartScreen(g);
        } else if (gameState instanceof RunningState) {
            drawPlayingScreen(g);
        } else if (gameState instanceof GameOverState) {
            drawGameOverScreen(g);
        }
    }

    public void drawGameOverScreen(Graphics g) {
        String msg = "Game Over";
        String msg2 = "\nScore: " + snake.getPoint();
        String msg3 = "Click Spacebar to Restart the Game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        g.drawString(msg, (Settings.WIDTH - metr.stringWidth(msg)) / 2, (Settings.HEIGHT / 2) - 15);
        g.drawString(msg2, (Settings.WIDTH - metr.stringWidth(msg2)) / 2, (Settings.HEIGHT / 2));
        g.drawString(msg3, (Settings.WIDTH - metr.stringWidth(msg3)) / 2, (Settings.HEIGHT / 2) + 25);
    }


    private void drawStartScreen(Graphics g) {
        String msg = "WELCOME TO THE BEST SNAKE";
        String msg3 = "Click Spacebar to start your game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        g.drawString(msg, (Settings.WIDTH - metr.stringWidth(msg)) / 2, (Settings.HEIGHT / 2) - 25);
        g.drawString(msg3, (Settings.WIDTH - metr.stringWidth(msg3)) / 2, (Settings.HEIGHT / 2) + 15);
    }

    public void drawPlayingScreen(Graphics g) {
        String scores = "Score: " + snake.getPoint();
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

        g.drawImage(appleImage, fruit.getPosition().getX(),
                fruit.getPosition().getY(), this);

        for (Position pos : snake.getPosition()) {
            g.drawImage(bodyImage, pos.getX(), pos.getY(), this);
        }
        g.drawImage(headImage, snake.getHead().getX(), snake.getHead().getY(), this);

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameState instanceof RunningState) {

            checkApple();
            checkCollision();
            if (gameState instanceof GameOverState) {
                timer.stop();
            }
            snake.move(direction);
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_UP: case KeyEvent.VK_W:
                    gameState.handleUp();
                    break;
                case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                    gameState.handleLeft();
                    break;
                case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                    gameState.handleDown();
                    break;
                case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                    gameState.handleRight();
                    break;
                case KeyEvent.VK_SPACE:
                    gameState.handleSpace();
                    break;
                default:
                    break;
            }
        }
    }
}
