package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public abstract class Fruit {
    private Position position;
    private int boardSize = Settings.WIDTH;
    protected BufferedImage fruitImage;

    public Fruit() {
        position = new Position();
        locate();
    }
    
    public Position getPosition() {
        return position;
    }

    public void locate() {
        int r = 1 + (int) (Math.random() * (boardSize / Settings.CELL_SIZE - 3));
        position.setX(r * Settings.CELL_SIZE);


        r = 1 + (int) Math.round((Math.random()* (boardSize / Settings.CELL_SIZE - 3)));
        position.setY(r * Settings.CELL_SIZE);
    }

    abstract public int getPoints();
    public BufferedImage getImage(){
    	return this.fruitImage;
    }
    public void setImage(BufferedImage fruitImage){
    	this.fruitImage = fruitImage;
    }
    
    
    
}
