package agh.cs.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void canMoveToTest() {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(3, 2)));
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(0, 0)));


        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(1, 5)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(-1, 3)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(-2, 6)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(3, 2)));
        Assertions.assertFalse(rectangularMap.canMoveTo(new Vector2d(0, 0)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(4, 4)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(0, 4)));
        Assertions.assertTrue(rectangularMap.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void placeTest() {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        Animal animal1 = new Animal(rectangularMap, new Vector2d(2, 2));
        Animal animal2 = new Animal(rectangularMap, new Vector2d(0, 0));
        Animal animal3 = new Animal(rectangularMap, new Vector2d(5, -1));
        Animal animal4 = new Animal(rectangularMap, new Vector2d(2, 2));


        Assertions.assertTrue(rectangularMap.place(animal1));
        Assertions.assertTrue(rectangularMap.place(animal2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rectangularMap.place(animal3);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            rectangularMap.place(animal4);
        });
        Assertions.assertEquals(animal1, rectangularMap.objectAt(new Vector2d(2, 2)));
        Assertions.assertEquals(animal2, rectangularMap.objectAt(new Vector2d(0, 0)));
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(1, 4)));
        rectangularMap.place(new Animal(rectangularMap, new Vector2d(0, 2)));

        Assertions.assertTrue(rectangularMap.isOccupied(new Vector2d(1, 4)));
        Assertions.assertTrue(rectangularMap.isOccupied(new Vector2d(0, 2)));
        Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(1, 2)));
        Assertions.assertFalse(rectangularMap.isOccupied(new Vector2d(-1, 5)));
    }

    @Test
    public void objectAtTest() {
        IWorldMap rectangularMap = new RectangularMap(5, 5);
        Animal animal1 = new Animal(rectangularMap, new Vector2d(3, 3));
        Animal animal2 = new Animal(rectangularMap, new Vector2d(4, 2));
        Animal animal3 = new Animal(rectangularMap, new Vector2d(3, 3));
        Animal animal4 = new Animal(rectangularMap, new Vector2d(4, 2));

        rectangularMap.place(animal1);
        rectangularMap.place(animal2);

        Assertions.assertEquals(animal1, rectangularMap.objectAt(new Vector2d(3, 3)));
        Assertions.assertEquals(animal2, rectangularMap.objectAt(new Vector2d(4, 2)));
        Assertions.assertEquals(null, rectangularMap.objectAt(new Vector2d(1, 2)));
        Assertions.assertEquals(null, rectangularMap.objectAt(new Vector2d(5, -1)));
        Assertions.assertNotEquals(animal3, rectangularMap.objectAt(new Vector2d(3, 3)));
        Assertions.assertNotEquals(animal4, rectangularMap.objectAt(new Vector2d(4, 2)));
        Assertions.assertNotEquals(animal2, rectangularMap.objectAt(new Vector2d(3, 3)));
    }
}
