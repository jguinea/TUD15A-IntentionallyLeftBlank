package nl.tudelft.tud15a.snake.view;

import nl.tudelft.tud15a.snake.model.Model;
import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

public class BoardView extends JPanel {
    private Model model;

    private Image bodyImage;
    private Image headImage;

    public BoardView(KeyAdapter keyAdapter) {
        addKeyListener(keyAdapter);
        setBackground(Color.BLUE);
        setFocusable(true);

        setPreferredSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));
        loadImages();
    }

    private void loadImages() {
        bodyImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/body.jpg").getImage();
        headImage = new ImageIcon("src/main/java/nl/tudelft/tud15a/snake/view/images/head.jpg").getImage();
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

    public void setModel(Model model) {
        this.model = model;
    }
}
