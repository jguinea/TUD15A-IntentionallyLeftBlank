package nl.tudelft.tud15a.snake.model.decorator;

import nl.tudelft.tud15a.snake.model.Position;

import java.awt.image.*;

public abstract class Decorator implements Fruit {
    protected Fruit fruit;
    protected float[][] colorMat;


    Decorator(Fruit fruit) {
        this.fruit = fruit;

    }


    public Position getPosition() {
        return fruit.getPosition();
    }

    public void locate() {
        fruit.locate();
    }

    public int getPoints() {
        return fruit.getPoints();
    }


    public BufferedImage processImage(BufferedImage image, float[][] colorMatrix) {

        BandCombineOp changeColors = new BandCombineOp(colorMatrix, null);
        Raster sourceRaster = image.getRaster();
        WritableRaster displayRaster = sourceRaster.createCompatibleWritableRaster();
        changeColors.filter(sourceRaster, displayRaster);
        return new BufferedImage(image.getColorModel(), displayRaster, true, null);

    }

    //apply a filter to change the color of an Image
    public void combine(Fruit fruit, float[][] colorMat) {
        BufferedImage fruitIm = fruit.getImage();
        BufferedImage newIm = processImage(fruitIm, colorMat);
        this.setImage(newIm);
    }

    public BufferedImage getImage() {
        return fruit.getImage();
    }

    public void setImage(BufferedImage fruitImage) {
        this.fruit.setImage(fruitImage);
    }

    //Blur the image with a linear filter
    public BufferedImage blurImage(BufferedImage image) {
        int radius = 4;
        int size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];

        for (int i = 0; i < size * size; i++) {
            data[i] = weight;
        }

        Kernel kernel = new Kernel(size, size, data);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        //tbi is BufferedImage
        return op.filter(image, null);
    }

    public void combineBlur(Fruit fruit) {
        BufferedImage newIm = blurImage(fruit.getImage());
        this.setImage(newIm);

    }


}
