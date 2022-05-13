package agh.cs.lab;


public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    protected void updateCorners() {
        // corners never have to be updated
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if (!(position.follows(this.lowerLeft) && position.precedes(this.upperRight))) return false;
        return super.canMoveTo(position);
    }
}
