package nl.tudelft.tud15a.snake.model;

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

public class Board extends JPanel implements ActionListener {
    Snake snake;
    private Apple apple;

    private Direction previousDirection;
    private Direction direction = Direction.RIGHT;
    private State inGame = State.START_SCREEN;

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
    	
    	if(inGame==State.START_SCREEN){
    		startScreen(g);
    	}
        
    	else if (inGame==State.PLAYING) {
        	////
        	String scores="Score: "+snake.getPoint();
        	Font small = new Font("Helvetica", Font.BOLD, 14);
        	g.setColor(Color.white);
            g.setFont(small);
        	g.drawString(scores, Constants.WIDTH - 80, Constants.HEIGHT-10);

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

        g.setColor(Color.white);
        g.setFont(small);
       
        g.drawString(msg, (Constants.WIDTH - metr.stringWidth(msg)) / 2, (Constants.HEIGHT / 2)-15);
        g.drawString(msg2, (Constants.WIDTH - metr.stringWidth(msg2)) / 2, (Constants.HEIGHT / 2));
        g.drawString(msg3, (Constants.WIDTH - metr.stringWidth(msg3)) / 2, (Constants.HEIGHT / 2)+25);
    }

    private void startScreen(Graphics g) {
        
        String msg = "WELCOME TO THE BEST SNAKE";
        String msg3="Click Spacebar to start your game";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
       
        g.drawString(msg, (Constants.WIDTH - metr.stringWidth(msg)) / 2, (Constants.HEIGHT / 2)-25);
        g.drawString(msg3, (Constants.WIDTH - metr.stringWidth(msg3)) / 2, (Constants.HEIGHT / 2)+15);
    }
    
    private void checkApple() {

        if ((snake.getHead().getX() == apple.getPosition().getX()) && (snake.getHead().getY() == apple.getPosition().getY())) {

            snake.eatApple();

            apple.locate();
        }
    }

    private void checkCollision() {

        if(snake.isEatingYourself()) {
        	inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() >= Constants.HEIGHT) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() < 0) {
            inGame = State.GAME_OVER;
            }

        if (snake.getHead().getX() >= Constants.WIDTH) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getX() < 0) {
            inGame = State.GAME_OVER;
        }
        
        if(inGame == State.GAME_OVER) {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame == State.PLAYING) {

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

            if(inGame == State.GAME_OVER || inGame==State.START_SCREEN){
            	if((key==KeyEvent.VK_SPACE)){
            		initGame();
            		inGame = State.PLAYING;
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
