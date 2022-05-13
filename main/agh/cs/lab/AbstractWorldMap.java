package agh.cs.lab;

import java.util.LinkedHashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected final Map<Vector2d, Animal> animals = new LinkedHashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    protected abstract void updateCorners();

    @Override
    public String toString() {
        updateCorners();
        return mapVisualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !this.animals.containsKey(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (!this.canMoveTo(animalPosition)) throw new IllegalArgumentException(animalPosition + " is not legal animal position");
        animal.addObserver(this);
        this.animals.put(animalPosition, animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.animals.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return this.animals.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }
}
