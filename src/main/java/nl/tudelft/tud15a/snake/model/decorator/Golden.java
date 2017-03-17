package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.image.BufferedImage;

public class Golden extends Metal {

    public Golden(Fruit fruit) {
        super(fruit);
        //yellow = red + green
        super.colorMat = new float[][]{
                {1f, 0f, 0f, 0f},
                {1f, 1f, 0f, 0f},
                {0f, 0f, 1f, 0f},
                {0f, 0f, 0f, 1f}};

        super.combine(fruit, colorMat);
        super.pointsMultiplier = 3;
    }

    @Override
    public void setImage(BufferedImage fruitImage) {
        super.fruit.setImage(fruitImage);
    }

    @Override
    public BufferedImage getImage() {
        return super.fruit.getImage();
    }
}
