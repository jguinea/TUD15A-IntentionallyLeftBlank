package nl.tudelft.tud15a.snake.model.observer;

import java.util.ArrayList;
import java.util.Collection;

public abstract class CollisionObservable {
    private Collection<CollisionListener> listeners = new ArrayList<>();

    public void addListener(CollisionListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CollisionListener listener) {
        listeners.remove(listener);
    }

    public void collision(CollisionReason reason) {
        listeners.forEach(listener -> listener.onCollision(reason));
    }
}
