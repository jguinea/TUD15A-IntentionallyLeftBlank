package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Apple implements Fruit {

    private Position position;
    private int points = 10;
    private BufferedImage fruitImage;

    public Apple() {
        position = new Position();
        locate();
        try {
            this.fruitImage = ImageIO.read(new File("src/main/java/nl/tudelft/tud15a/snake/view/images/apple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Position getPosition() {
        return position;
    }

    public void locate() {
        int r = 1 + (int) (Math.random() * (Settings.WIDTH / Settings.CELL_SIZE - 3));
        position.setX(r * Settings.CELL_SIZE);

        r = 1 + (int) Math.round((Math.random() * (Settings.HEIGHT / Settings.CELL_SIZE - 3)));
        position.setY(r * Settings.CELL_SIZE);

    }

    public int getPoints() {
        return points;
    }

    @Override
    public void setImage(BufferedImage fruitImage) {
        this.fruitImage = fruitImage;
    }

    @Override
    public BufferedImage getImage() {
        return fruitImage;
    }

    public void setSnakePoint(int snakePoint) {

    }
    //No command to add
}
