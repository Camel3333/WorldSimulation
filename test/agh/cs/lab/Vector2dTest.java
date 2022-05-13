package agh.cs.lab;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {

    @Test
    public void equalsTests(){
        Vector2d test1 = new Vector2d(1, 2);
        Vector2d test2 = new Vector2d(1, 4);
        Vector2d test3 = new Vector2d(1, 4);
        Object test4 = new Object();
        Assertions.assertFalse(test1.equals(test2));
        Assertions.assertTrue(test2.equals(test3));
        Assertions.assertFalse(test1.equals(test4));
    }

    @Test
    public void toStringTests(){
        Vector2d test1 = new Vector2d(-3, 4);
        Vector2d test2 = new Vector2d(0, -1);
        Assertions.assertTrue(test1.toString().equals("(-3,4)"));
        Assertions.assertTrue(test2.toString().equals("(0,-1)"));
        Assertions.assertFalse(test1.toString().equals("(3,4)"));
        Assertions.assertFalse(test2.toString().equals("(0, 1)"));
    }

    @Test
    public void precedesTests(){
        Vector2d test1 = new Vector2d(3, 1);
        Vector2d test2 = new Vector2d(3, 5);
        Vector2d test3 = new Vector2d(3, 5);
        Vector2d test4 = new Vector2d(1, -1);
        Assertions.assertTrue(test1.precedes(test2));
        Assertions.assertTrue(test2.precedes(test3));
        Assertions.assertFalse(test2.precedes(test1));
        Assertions.assertFalse(test1.precedes(test4));
    }

    @Test
    public void followsTests(){
        Vector2d test1 = new Vector2d(100, -8);
        Vector2d test2 = new Vector2d(4, -8);
        Vector2d test3 = new Vector2d(4, -8);
        Vector2d test4 = new Vector2d(225, 5);
        Assertions.assertTrue(test1.follows(test2));
        Assertions.assertTrue(test2.follows(test3));
        Assertions.assertFalse(test2.follows(test1));
        Assertions.assertFalse(test1.follows(test4));
    }

    @Test
    public void upperRightTests(){
        Vector2d test1 = new Vector2d(1, 4);
        Vector2d test2 = new Vector2d(7, -8);
        Vector2d test3 = new Vector2d(7, 4);
        Vector2d test4 = new Vector2d(4, 7);
        Assertions.assertTrue(test1.upperRight(test2).equals(test3));
        Assertions.assertFalse(test2.upperRight(test3).equals(test4));
    }

    @Test
    public void lowerLeftTests(){
        Vector2d test1 = new Vector2d(1, -2);
        Vector2d test2 = new Vector2d(4, 6);
        Vector2d test3 = new Vector2d(1, 9);
        Assertions.assertTrue(test1.lowerLeft(test2).equals(test1));
        Assertions.assertFalse(test2.lowerLeft(test3).equals(test2));
    }

    @Test
    public void addTests(){
        Vector2d test1 = new Vector2d(1, -2);
        Vector2d test2 = new Vector2d(-1, 2);
        Vector2d test3 = new Vector2d(-2, 1);
        Vector2d test4 = new Vector2d(0, 0);
        Assertions.assertTrue(test1.add(test2).equals(test4));
        Assertions.assertFalse(test1.add(test3).equals(test4));
    }

    @Test
    public void subtractTests(){
        Vector2d test1 = new Vector2d(-3, 7);
        Vector2d test2 = new Vector2d(3, -7);
        Vector2d test3 = new Vector2d(-3, 7);
        Vector2d test4 = new Vector2d(0, 0);
        Assertions.assertFalse(test1.subtract(test2).equals(test4));
        Assertions.assertTrue(test1.subtract(test3).equals(test4));
    }

    @Test
    public void oppositeTests(){
        Vector2d test1 = new Vector2d(2, 8);
        Vector2d test2 = new Vector2d(-2, -8);
        Vector2d test3 = new Vector2d(2, -8);
        Vector2d test4 = new Vector2d(0, 0);
        Assertions.assertTrue(test1.opposite().equals(test2));
        Assertions.assertTrue(test4.opposite().equals(test4));
        Assertions.assertFalse(test1.opposite().equals(test3));
        Assertions.assertFalse(test1.opposite().equals(test1));
    }
}
