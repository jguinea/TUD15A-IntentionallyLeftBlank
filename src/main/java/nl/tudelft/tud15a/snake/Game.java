package nl.tudelft.tud15a.snake;

import java.awt.EventQueue;

import javax.swing.*;

import nl.tudelft.tud15a.snake.view.Board;


public class Game extends JFrame {

    public Game() {
        add(new Board());

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
