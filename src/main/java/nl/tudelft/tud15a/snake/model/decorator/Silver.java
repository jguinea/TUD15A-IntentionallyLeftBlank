package nl.tudelft.tud15a.snake.model.decorator;

public class Silver extends Metal {

    public Silver(Fruit fruit) {
        super(fruit);

        super.colorMat = new float[][]{
                {1f, 0f, 0f, 0f},
                {1f, 1f, 0f, 0f},
                {1f, 0f, 1f, 0f},
                {0f, 0f, 0f, 1f}};

        super.combine(fruit, colorMat);
        super.pointsMultiplier = 2;
    }


}
