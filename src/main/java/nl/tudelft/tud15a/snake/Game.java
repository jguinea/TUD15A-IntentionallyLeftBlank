package nl.tudelft.tud15a.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;

import nl.tudelft.tud15a.snake.view.Board;


public class Game extends JFrame {

    public Game() {

        add(new Board());
        
        setResizable(false);
        pack();
        
        setTitle("Super Snake!");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {                
                JFrame ex = new Game();
                ex.setVisible(true);                
            }
        });
    }
}
