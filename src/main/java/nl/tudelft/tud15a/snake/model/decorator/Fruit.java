package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;


public interface Fruit {
    
	Position getPosition();


	void locate();


    public void setImage(BufferedImage fruitImage);
    
    public BufferedImage getImage();
    
    
    

	int getPoints();

}
