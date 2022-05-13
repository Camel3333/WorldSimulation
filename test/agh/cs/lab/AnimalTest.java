package agh.cs.lab;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnimalTest {

    @Test
    public void animalTest1() {
        String[] data = { "f", "right", "backward", "b", "r", "f", "f", "f", "forward", "b", "l"};
        Animal animal = new Animal(new RectangularMap(5, 5));
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(data);

        for(MoveDirection direction : directions) animal.move(direction);
        Assertions.assertEquals(new Vector2d(0, 1), animal.getPosition());
        Assertions.assertEquals(MapDirection.EAST, animal.getOrientation());
    }

    @Test
    public void animalTest2() {
        String[] data = {"r", "f", "forward", "f", "left", "backward", "b", "b", "b", "forward", "f", "f", "f", "f", "left", "l", "b"};
        Animal animal = new Animal(new RectangularMap(5, 5));
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(data);

        for(MoveDirection direction : directions) animal.move(direction);
        Assertions.assertEquals(new Vector2d(4, 4), animal.getPosition());
        Assertions.assertEquals(MapDirection.SOUTH, animal.getOrientation());
    }

    @Test
    public void animalTest3() {
        String[] data = {};
        Animal animal = new Animal(new RectangularMap(5, 5));
        OptionsParser parser = new OptionsParser();
        MoveDirection[] directions = parser.parse(data);

        for(MoveDirection direction : directions) animal.move(direction);
        Assertions.assertEquals(new Vector2d(2, 2), animal.getPosition());
        Assertions.assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    public void animalTest4() {
        String[] data = {"f", "l", "backwardf", "f", "r", "a"};
        Animal animal = new Animal(new RectangularMap(5, 5));
        OptionsParser parser = new OptionsParser();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(data);
        });
    }
}
