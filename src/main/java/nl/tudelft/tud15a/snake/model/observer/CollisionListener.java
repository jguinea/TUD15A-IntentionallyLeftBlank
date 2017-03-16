package nl.tudelft.tud15a.snake.model.observer;

public interface CollisionListener {
    void onCollision(CollisionReason reason);
}
