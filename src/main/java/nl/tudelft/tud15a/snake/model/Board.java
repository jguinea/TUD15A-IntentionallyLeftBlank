package nl.tudelft.tud15a.snake.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {
    Snake snake;
    private Apple apple;

    private Color textColor = Color.decode("#800000");

    private Direction previousDirection;
    private Direction direction = Direction.RIGHT;
    private boolean inGame = true;

    private int level = 0;

    private Timer timer;
    private Image bodyImage;
    private Image appleImage;
    private Image headImage;

    public Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.GREEN);
        setFocusable(true);

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        bodyImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg").getImage();
        headImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/head.jpg").getImage();
        appleImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/apple.png").getImage();
    }

    private void initGame() {

        snake = new Snake();
        apple = new Apple();

        timer = new Timer(Constants.DELAY, this);
        timer.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {
        	////
            String levels = "Level:" + level;
        	String scores="Score: "+snake.getPoint();
        	Font small = new Font("Helvetica", Font.BOLD, 14);
        	g.setColor(textColor);
            g.setFont(small);
            g.drawString(levels, Constants.WIDTH - 110, Constants.HEIGHT - 25);
        	g.drawString(scores, Constants.WIDTH - 110, Constants.HEIGHT-10);

        	//Borders of the Fields
        	g.setColor(Color.ORANGE);
        	g.drawRect(0,0, Constants.WIDTH, 5);
        	g.drawRect(0,Constants.HEIGHT, Constants.WIDTH, 5);
        	g.drawRect(0,0, 5, Constants.HEIGHT);
        	g.drawRect(Constants.WIDTH,0, 5, Constants.HEIGHT);
        	


        	//Borders of the Fields
        	g.setColor(Color.GREEN);
        	g.drawRect(0,0, Constants.WIDTH, 5);
        	g.drawRect(0,Constants.HEIGHT, Constants.WIDTH, 5);
        	g.drawRect(0,0, 5, Constants.HEIGHT);
        	g.drawRect(Constants.WIDTH,0, 5, Constants.HEIGHT);
        	



            g.drawImage(appleImage, apple.getPosition().getX(),
            									apple.getPosition().getY(), this);

            for(Position pos : snake.getPosition()) {
            	g.drawImage(bodyImage, pos.getX(), pos.getY(), this);
            }
            g.drawImage(headImage, snake.getHead().getX(), snake.getHead().getY(), this);

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        
        String msg = "Game Over";
        String msg2 = "\nScore: "+snake.getPoint();
        String msg3="Click Spacebar to Restart the Game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(textColor);
        g.setFont(small);
       
        g.drawString(msg, (Constants.WIDTH - metr.stringWidth(msg)) / 2, (Constants.HEIGHT / 2)-15);
        g.drawString(msg2, (Constants.WIDTH - metr.stringWidth(msg2)) / 2, (Constants.HEIGHT / 2));
        g.drawString(msg3, (Constants.WIDTH - metr.stringWidth(msg3)) / 2, (Constants.HEIGHT / 2)+25);
    }

    private void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple();

            apple.locate();
        }
    }

    private void checkCollision() {

        if(snake.isEatingYourself()) {
        	inGame = false;
        }

        if (snake.getHead().getY() >= Constants.HEIGHT) {
            inGame = false;
        }

        if (snake.getHead().getY() < 0) {
            inGame = false;
        }

        if (snake.getHead().getX() >= Constants.WIDTH) {
            inGame = false;
        }

        if (snake.getHead().getX() < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            previousDirection = direction;
            snake.move(direction);
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	
            int key = e.getKeyCode();
            if(inGame==false){
            	if((key==KeyEvent.VK_SPACE)){
            		initGame();
            		inGame=true;
            	}
            }
            if ((key == KeyEvent.VK_LEFT) && (previousDirection != Direction.RIGHT)) {
                direction = Direction.LEFT;
            }

            if ((key == KeyEvent.VK_RIGHT) && (previousDirection != Direction.LEFT)) {
            	direction = Direction.RIGHT;
            }

            if ((key == KeyEvent.VK_UP) && (previousDirection != Direction.DOWN)) {
            	direction = Direction.UP;
            }

            if ((key == KeyEvent.VK_DOWN) && (previousDirection != Direction.UP)) {
            	direction = Direction.DOWN;
            }
            
        }
    }
}
