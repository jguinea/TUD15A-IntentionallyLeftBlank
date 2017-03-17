package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;

import java.awt.image.BufferedImage;

public interface Fruit {

    Position getPosition();

    void locate();

    void setImage(BufferedImage fruitImage);

    BufferedImage getImage();

    int getPoints();


}
