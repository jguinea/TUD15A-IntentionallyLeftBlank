package nl.tudelft.tud15a.snake.model.decorator;

import java.awt.Color;
import java.awt.Image;

import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;



import nl.tudelft.tud15a.snake.model.Position;

public abstract class Decorator extends Fruit {
	protected Fruit fruit;
	protected Color color;

	Decorator(Fruit fruit) {
		this.fruit =fruit;
		this.setImage(this.fruit.getImage());
	}

	public abstract int getPoints();
	 public BufferedImage processImage(BufferedImage image, float[][] colorMatrix) {
		    
		    BandCombineOp changeColors = new BandCombineOp(colorMatrix, null);
		    Raster sourceRaster = image.getRaster();
		    WritableRaster displayRaster = sourceRaster.createCompatibleWritableRaster();
		    changeColors.filter(sourceRaster, displayRaster);
		    return new BufferedImage(image.getColorModel(), displayRaster, true, null);

		  }
	public void combine(Fruit fruit, float[][] colorMat){
		BufferedImage fruitIm = fruit.getImage();
		BufferedImage newIm = processImage(fruitIm, colorMat);
		this.setImage(newIm);
	}
}
