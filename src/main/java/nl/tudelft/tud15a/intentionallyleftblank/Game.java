package nl.tudelft.tud15a.intentionallyleftblank;

import javax.swing.*;
import java.awt.*;

// adapted from http://stackoverflow.com/questions/36380516/drawing-a-grid-in-a-jframe
public class Game extends JPanel {

    class Square extends JPanel {
        Rectangle rectangle;

        Square(Rectangle rectangle) {
            this.rectangle = rectangle;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.draw(rectangle);
        }
    }

    private static final int GAP = 1;
    private static final Color BG = Color.BLACK;
    private static final Dimension DIMENSION = new Dimension(80, 80);
    private Square[][] rectangles = new Square[20][20];

    public Game() {
        setBackground(BG);
        setLayout(new GridLayout(20, 20, GAP, GAP));
        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));

        for (int i = 0; i < rectangles.length; i++) {
            for (int j = 0; j < rectangles[i].length; j++) {
                rectangles[i][j] = new Square(new Rectangle(DIMENSION));
                rectangles[i][j].setSize(DIMENSION);
                this.add(rectangles[i][j]);
            }
        }
    }

    private static void drawGame(Game mainPanel) {
        JFrame frame = new JFrame("JPanelGrid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        SwingUtilities.invokeLater(() -> {
            drawGame(game);
        });

        Thread.sleep(1000);

        game.rectangles[2][2].setBackground(Color.blue);
    }
}

