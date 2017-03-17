package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.command.EffectController;

public class Silver extends Metal {

	public Silver(Fruit fruit) {
		super(fruit);
		
		// TODO Auto-generated constructor stub
		super.colorMat = new float[][]{ 
    		{ 1f,  0f, 0f, 0f }, 
    		{ 1f,  1f, 0f, 0f }, 
    		{ 1f,  0f, 1f, 0f},
    		{ 0f,  0f, 0f, 1f} };
	
	super.combine(fruit, colorMat);
	super.pointsMultiplier = 2;
	}
	
	
	

	

}
