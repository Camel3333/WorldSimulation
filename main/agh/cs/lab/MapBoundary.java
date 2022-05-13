package agh.cs.lab;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedSet<Map.Entry<Vector2d, Class>> boundaryX = new TreeSet<Map.Entry<Vector2d, Class>>(
            new Comparator<Map.Entry<Vector2d, Class>>() {
                @Override
                public int compare(Map.Entry<Vector2d, Class> firstMapElement, Map.Entry<Vector2d, Class> secondMapElement) {
                    return compareMapElements(firstMapElement, secondMapElement, true);
                }
            });

    private final SortedSet<Map.Entry<Vector2d, Class>> boundaryY = new TreeSet<Map.Entry<Vector2d, Class>>(
            new Comparator<Map.Entry<Vector2d, Class>>() {
                @Override
                public int compare(Map.Entry<Vector2d, Class> firstMapElement, Map.Entry<Vector2d, Class> secondMapElement) {
                    return compareMapElements(firstMapElement, secondMapElement, false);
                }
            });

    private int compareMapElements(Map.Entry<Vector2d, Class> firstMapElement, Map.Entry<Vector2d, Class> secondMapElement, boolean compareByXFirst) {
        Class firstMapElementClass = firstMapElement.getValue();
        Class secondMapElementClass = secondMapElement.getValue();
        Vector2d firstMapElementPosition = firstMapElement.getKey();
        Vector2d secondMapElementPosition = secondMapElement.getKey();
        int xPositionComparison = Integer.compare(firstMapElementPosition.x, secondMapElementPosition.x);
        int yPositionComparison = Integer.compare(firstMapElementPosition.y, secondMapElementPosition.y);

        if (compareByXFirst) {
            if (xPositionComparison != 0) return xPositionComparison;
            if (yPositionComparison != 0) return yPositionComparison;
        }
        else {
            if (yPositionComparison != 0) return yPositionComparison;
            if (xPositionComparison != 0) return xPositionComparison;
        }
        if (firstMapElementClass == Animal.class && secondMapElementClass == Grass.class) return -1;
        if (firstMapElementClass == Grass.class && secondMapElementClass == Animal.class) return 1;
        return 0;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.removeMapElement(oldPosition, Animal.class);
        this.addMapElement(newPosition, Animal.class);
    }

    public void addMapElement(Vector2d mapElementPosition, Class mapElementClass) {
        Map.Entry<Vector2d, Class> mapElementEntry = new AbstractMap.SimpleEntry<>(mapElementPosition, mapElementClass);
        this.boundaryX.add(mapElementEntry);
        this.boundaryY.add(mapElementEntry);
    }

    private void removeMapElement(Vector2d mapElementPosition, Class mapElementClass) {
        Map.Entry<Vector2d, Class> mapElementEntry = new AbstractMap.SimpleEntry<>(mapElementPosition, mapElementClass);
        this.boundaryX.remove(mapElementEntry);
        this.boundaryY.remove(mapElementEntry);
    }

    public Vector2d lowerLeftCorner() {
        return new Vector2d(this.boundaryX.first().getKey().x, this.boundaryY.first().getKey().y);
    }

    public Vector2d upperRightCorner() {
        return new Vector2d(this.boundaryX.last().getKey().x, this.boundaryY.last().getKey().y);
    }
}
