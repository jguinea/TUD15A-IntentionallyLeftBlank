package nl.tudelft.tud15a.snake.model.decorator;

public abstract class Metal extends Decorator {
    //abstract Class where the points are multiplied !!

    protected int pointsMultiplier;

    Metal(Fruit fruit) {
        super(fruit);
    }

    public int getPoints() {
        return 0;
    }

    public int getPointsMultiplier() {
        return this.pointsMultiplier;
    }


}
