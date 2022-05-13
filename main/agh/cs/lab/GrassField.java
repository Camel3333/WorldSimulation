package agh.cs.lab;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassList = new LinkedHashMap<>();
    private final MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int amountOfGrass) {
        Random generator = new Random();
        for (int i = 0; i < amountOfGrass; i++) {
            Vector2d grassRandomPosition;
            do {
                grassRandomPosition = new Vector2d(generator.nextInt((int) (Math.sqrt(amountOfGrass * 10) + 1)), generator.nextInt((int) (Math.sqrt(amountOfGrass * 10) + 1)));
            } while (this.grassList.containsKey(grassRandomPosition));
            this.grassList.put(grassRandomPosition, new Grass(grassRandomPosition));
            this.mapBoundary.addMapElement(grassRandomPosition, Grass.class);
        }
    }

    @Override
    protected void updateCorners() {
        this.lowerLeft = this.mapBoundary.lowerLeftCorner();
        this.upperRight = this.mapBoundary.upperRightCorner();
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (super.isOccupied(position)) return true;
        return this.grassList.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object != null) return object;
        return this.grassList.get(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(super.place(animal)){
            animal.addObserver(this.mapBoundary);
            this.mapBoundary.addMapElement(animal.getPosition(), Animal.class);
        }
        return true;
    }
}