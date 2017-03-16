package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import nl.tudelft.tud15a.snake.model.Position;
import nl.tudelft.tud15a.snake.model.Settings;

public class Apple implements Fruit {
	private Position position;
	private int boardSize = Settings.WIDTH;
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
		int r = 1 + (int) (Math.random() * (boardSize / Settings.CELL_SIZE - 3));
		position.setX(r * Settings.CELL_SIZE);

		r = 1 + (int) Math.round((Math.random() * (boardSize / Settings.CELL_SIZE - 3)));
		position.setY(r * Settings.CELL_SIZE);

	}

	public int getPoints() {
		return points;
	}

	@Override
	public void setImage(BufferedImage fruitImage) {
		// TODO Auto-generated method stub
		this.fruitImage = fruitImage;

	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return fruitImage;
	};
}
