package nl.tudelft.tud15a.snake;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game() {
        BoardController controller = new BoardController();
        add(controller.getView());

        setResizable(false);
        pack();

        setTitle("Intentionally left blank!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new Game().setVisible(true));
    }
}
