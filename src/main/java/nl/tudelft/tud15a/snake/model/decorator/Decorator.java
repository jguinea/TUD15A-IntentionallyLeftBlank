package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import nl.tudelft.tud15a.snake.model.Position;

public abstract class Decorator implements Fruit {
    protected Fruit fruit;

    Decorator(Fruit fruit) {
        this.fruit = fruit;
    }

    public Position getPosition() {
        return fruit.getPosition();
    }

    public void locate() {
        fruit.locate();
    }

    public abstract int getPoints();

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
}
