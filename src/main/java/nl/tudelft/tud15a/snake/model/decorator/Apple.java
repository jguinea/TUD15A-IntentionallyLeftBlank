package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public class Apple extends Fruit {
    private int points;
    

    public Apple() {
    	super();
    	try {
    		super.fruitImage =  ImageIO.read(new File("src/main/java/nl/tudelft/tud15a/snake/view/images/apple.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	points = 10;
    }

    public int getPoints() {
    	return points;
    }
}
