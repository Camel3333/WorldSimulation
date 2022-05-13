package agh.cs.lab;

public class Grass extends AbstractWorldMapElement {

    public Grass(Vector2d position) {
        this.position = position;
    }

    @Override
    protected String stringRepresentation() {
        return "*";
    }
}
