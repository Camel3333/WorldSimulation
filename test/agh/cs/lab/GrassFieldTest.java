package agh.cs.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {

    @Test
    public void canMoveToTest() {
        IWorldMap grassField = new GrassField(1);
        grassField.place(new Animal(grassField, new Vector2d(-1, 0)));
        grassField.place(new Animal(grassField, new Vector2d(5, 3)));


        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(0, 0)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(0, 1)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(0, 2)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(0, 3)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(1, 0)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(1, 1)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(1, 2)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(1, 3)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(2, 0)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(2, 1)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(2, 2)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(2, 3)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(3, 0)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(3, 1)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(3, 2)));
        Assertions.assertTrue(grassField.canMoveTo(new Vector2d(3, 3)));

        Assertions.assertFalse(grassField.canMoveTo(new Vector2d(-1, 0)));
        Assertions.assertFalse(grassField.canMoveTo(new Vector2d(5, 3)));

    }

    @Test
    public void placeTest() {
        IWorldMap grassField = new GrassField(1);
        Animal animal1 = new Animal(grassField, new Vector2d(4, 8));
        Animal animal2 = new Animal(grassField, new Vector2d(-5, 0));
        Animal animal4 = new Animal(grassField, new Vector2d(4, 8));


        Assertions.assertTrue(grassField.place(animal1));
        Assertions.assertTrue(grassField.place(animal2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            grassField.place(animal4);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            grassField.place(animal2);
        });
        Assertions.assertEquals(animal1, grassField.objectAt(new Vector2d(4, 8)));
        Assertions.assertEquals(animal2, grassField.objectAt(new Vector2d(-5, 0)));
    }

    @Test
    public void isOccupiedTest() {
        IWorldMap grassField = new GrassField(1);
        grassField.place(new Animal(grassField, new Vector2d(1, 4)));
        grassField.place(new Animal(grassField, new Vector2d(-2, 2)));
        Vector2d grassPosition = null;
        for(int i = 0 ; i < 4; i++){
            for(int j = 0 ; j < 4; j++){
                Vector2d position = new Vector2d(i, j);
                if(grassField.objectAt(position) != null){
                    grassPosition = position;
                    break;
                }
            }
        }

        Assertions.assertTrue(grassField.isOccupied(new Vector2d(1, 4)));
        Assertions.assertTrue(grassField.isOccupied(new Vector2d(-2, 2)));
        Assertions.assertTrue(grassField.isOccupied(grassPosition));
        Assertions.assertFalse(grassField.isOccupied(new Vector2d(2, -2)));
        Assertions.assertFalse(grassField.isOccupied(new Vector2d(13, 8)));
    }

    @Test
    public void objectAtTest() {
        IWorldMap grassField = new GrassField(1);
        Vector2d grassPosition = null;
        for(int i = 0 ; i < 4; i++){
            for(int j = 0 ; j < 4; j++){
                Vector2d position = new Vector2d(i, j);
                if(grassField.objectAt(position) != null){
                    grassPosition = position;
                    break;
                }
            }
        }
        Animal animal1 = new Animal(grassField, new Vector2d(-8, 7));
        Animal animal2 = new Animal(grassField, new Vector2d(4, 1));
        Animal animal3 = new Animal(grassField, new Vector2d(-8, 7));
        Animal animal4 = new Animal(grassField, new Vector2d(4, 1));
        Animal animal5 = new Animal(grassField, grassPosition);

        grassField.place(animal1);
        grassField.place(animal2);

        Assertions.assertEquals(animal1, grassField.objectAt(new Vector2d(-8, 7)));
        Assertions.assertEquals(animal2, grassField.objectAt(new Vector2d(4, 1)));
        Assertions.assertEquals(null, grassField.objectAt(new Vector2d(4, 7)));
        Assertions.assertEquals(null, grassField.objectAt(new Vector2d(5, -3)));
        Assertions.assertNotEquals(animal3, grassField.objectAt(new Vector2d(-8, 7)));
        Assertions.assertNotEquals(animal4, grassField.objectAt(new Vector2d(4, 1)));
        Assertions.assertNotEquals(animal2, grassField.objectAt(new Vector2d(5, 5)));

        Assertions.assertNotEquals(null, grassField.objectAt(grassPosition));
        grassField.place(animal5);
        Assertions.assertEquals(animal5, grassField.objectAt(grassPosition));
    }
}
