package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Golden extends Metal {
	private int pointsMultiplier;
	
	public Golden(Fruit fruit, int snakePoint) {
		super(fruit, snakePoint);
		//yellow = red + green
		super.colorMat = new float[][]{ 
	    		{ 1f,  0f, 0f, 0f }, 
	    		{ 1f,  1f, 0f, 0f }, 
	    		{ 0f,  0f, 1f, 0f},
	    		{ 0f,  0f, 0f, 1f} };
		
		super.combine(fruit, colorMat);
		pointsMultiplier = 2;
	}

	public int getPoints() {
		return pointsMultiplier * super.snakePoint;
	}

	@Override
	public void setImage(BufferedImage fruitImage) {
		// TODO Auto-generated method stub
		super.fruit.setImage(fruitImage);
	}

	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return super.fruit.getImage();
	}
}
