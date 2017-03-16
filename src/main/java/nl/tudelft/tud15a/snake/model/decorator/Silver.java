package nl.tudelft.tud15a.snake.model.decorator;

public class Silver extends Metal {

	public Silver(Fruit fruit, int snakePoint) {
		super(fruit,snakePoint);
		
		// TODO Auto-generated constructor stub
		super.colorMat = new float[][]{ 
    		{ 1f,  0f, 0f, 0f }, 
    		{ 1f,  1f, 0f, 0f }, 
    		{ 1f,  0f, 1f, 0f},
    		{ 0f,  0f, 0f, 1f} };
	
	super.combine(fruit, colorMat);
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return super.snakePoint;
	}

}
