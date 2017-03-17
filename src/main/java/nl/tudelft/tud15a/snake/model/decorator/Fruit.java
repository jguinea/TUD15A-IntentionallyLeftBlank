package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.image.BufferedImage;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.command.Command;
import nl.tudelft.tud15a.snake.model.command.EffectController;

public interface Fruit {

    Position getPosition();

    void locate();

    void setImage(BufferedImage fruitImage);

    BufferedImage getImage();

    int getPoints();
    
    
}
