package agh.cs.lab;


import java.util.Objects;

abstract class AbstractWorldMapElement {
    protected Vector2d position;

    protected abstract String stringRepresentation();

    @Override
    public String toString() {
        return stringRepresentation();
    }

    public Vector2d getPosition() {
        return position;
    }
}
