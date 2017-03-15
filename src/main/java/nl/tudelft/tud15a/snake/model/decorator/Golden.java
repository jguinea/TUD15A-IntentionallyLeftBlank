package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Color;

public class Golden extends Decorator {
	private int pointsMultiplier;
	private float[][] colorMat;
	public Golden(Fruit fruit) {
		super(fruit);
		//green = red + green
		this.colorMat = new float[][]{ 
	    		{ 1f,  0f, 0f, 0f }, 
	    		{ 1f,  1f, 0f, 0f }, 
	    		{ 0f,  0f, 1f, 0f},
	    		{ 0f,  0f, 0f, 1f} };
		
		super.combine(fruit, colorMat);
		pointsMultiplier = 2;
	}

	public int getPoints() {
		return fruit.getPoints() * pointsMultiplier;
	}
}
