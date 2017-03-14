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
    Model model = new Model();

    private Direction direction;

    private Timer timer;
    private Image bodyImage;
    private Image appleImage;
    private Image headImage;
    private int borderThickness=5;
    private ImageIcon ic;

    public Board() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLUE);
        setFocusable(true);

        setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
    	ic = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg");
        bodyImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg").getImage();
        headImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/head.jpg").getImage();
        appleImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/apple.png").getImage();
    }

    private void initGame() {

        model = new Model();
        direction = Direction.RIGHT;

        timer = new Timer(Constants.DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
    	
    	if(model.getState()==State.START_SCREEN){
    		startScreen(g);
    	}
        
    	else if (model.getState()==State.PLAYING) {
        	////
        	String scores="Score: "+model.getSnake().getPoint();
        	Font small = new Font("Helvetica", Font.BOLD, 14);
        	g.setColor(Color.white);
            g.setFont(small);
        	g.drawString(scores, Constants.WIDTH - 80, Constants.HEIGHT-10);

        	//Borders of the Fields
        	borderThickness = ic.getIconHeight();
        	g.setColor(Color.GREEN);
        	g.drawRect(0,0, Constants.WIDTH, borderThickness);
        	g.drawRect(0,Constants.HEIGHT-borderThickness, Constants.WIDTH, borderThickness);
        	g.drawRect(0,0, borderThickness, Constants.HEIGHT);
        	g.drawRect(Constants.WIDTH-borderThickness,0,borderThickness, Constants.HEIGHT);
        	



            g.drawImage(appleImage, model.getApple().getPosition().getX(),
            		model.getApple().getPosition().getY(), this);

            for(Position pos : model.getSnake().getPosition()) {
            	g.drawImage(bodyImage, pos.getX(), pos.getY(), this);
            }
            g.drawImage(headImage, model.getSnake().getHead().getX(), model.getSnake().getHead().getY(), this);

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over";
        String msg2 = "\nScore: "+model.getSnake().getPoint();
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

    /*private void checkCollision() {
    	int bodySize = ic.getIconHeight();

        if(model.getSnake().isEatingYourself()) {
        	inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() >= Constants.HEIGHT-this.borderThickness-bodySize) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getY() < this.borderThickness + bodySize) {
            inGame = State.GAME_OVER;
        }
            

        if (snake.getHead().getX() >= Constants.WIDTH-this.borderThickness-bodySize) {
            inGame = State.GAME_OVER;
        }

        if (snake.getHead().getX() < this.borderThickness + bodySize) {
            inGame = State.GAME_OVER;
        }
        
        if(inGame == State.GAME_OVER) {
            timer.stop();
        }
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {

        if (model.getState() == State.PLAYING) {

            model.checkApple();
            model.checkCollision();
            model.getSnake().move(direction);
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	
            int key = e.getKeyCode();

            if(model.getState() == State.GAME_OVER || model.getState()==State.START_SCREEN){
            	if((key==KeyEvent.VK_SPACE)){
            		initGame();
            		model.setState(State.PLAYING);
            	}
            }
            if ((key == KeyEvent.VK_LEFT) && (model.getSnake().getDirection() != Direction.RIGHT)) {
                direction = Direction.LEFT;
            }

            if ((key == KeyEvent.VK_RIGHT) && (model.getSnake().getDirection() != Direction.LEFT)) {
            	direction = Direction.RIGHT;
            }

            if ((key == KeyEvent.VK_UP) && (model.getSnake().getDirection() != Direction.DOWN)) {
            	direction = Direction.UP;
            }

            if ((key == KeyEvent.VK_DOWN) && (model.getSnake().getDirection() != Direction.UP)) {
            	direction = Direction.DOWN;
            }
            
        }
    }
}
