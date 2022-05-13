package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement {
    private MapDirection orientation;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();


    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    @Override
    protected String stringRepresentation() {
        switch (this.orientation) {
            case NORTH:
                return "^";
            case SOUTH:
                return "v";
            case WEST:
                return "<";
            case EAST:
                return ">";
        }
        throw new IllegalArgumentException();
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case FORWARD:
                Vector2d forward = this.position.add(this.orientation.toUnitVector());
                if (this.map.canMoveTo(forward)) {
                    Vector2d oldPosition = this.position;
                    this.position = forward;
                    this.positionChanged(oldPosition, this.position);
                }
                break;
            case BACKWARD:
                Vector2d backward = this.position.subtract(this.orientation.toUnitVector());
                if (this.map.canMoveTo(backward)) {
                    Vector2d oldPosition = this.position;
                    this.position = backward;
                    this.positionChanged(oldPosition, this.position);
                }
                break;
        }
    }

    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : this.observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}