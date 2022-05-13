package agh.cs.lab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationEngineTest {

    @Test
    public void SimulationEngineTest1() {
        String[] data = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        String expectedResult = " y\\x  0 1 2 3 4 5 6 7 8 9" + System.lineSeparator() +
                                "  5: ---------------------" + System.lineSeparator() +
                                "  4: | | | |^| | | | | | |" + System.lineSeparator() +
                                "  3: | | | | | | | | | | |" + System.lineSeparator() +
                                "  2: | | | | | | | | | | |" + System.lineSeparator() +
                                "  1: | | | | | | | | | | |" + System.lineSeparator() +
                                "  0: | | |v| | | | | | | |" + System.lineSeparator() +
                                " -1: ---------------------" + System.lineSeparator();
        MoveDirection[] directions = new OptionsParser().parse(data);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Assertions.assertEquals(expectedResult, map.toString());
    }

    @Test
    public void SimulationEngineTest2() {
        String[] data = {"f", "l", "l", "f", "f", "b", "f", "f", "r", "f", "r", "f", "f", "f", "f", "b", "b", "b"};
        String expectedResult = " y\\x  0 1 2 3 4" + System.lineSeparator() +
                "  5: -----------" + System.lineSeparator() +
                "  4: | | | | | |" + System.lineSeparator() +
                "  3: | | | | | |" + System.lineSeparator() +
                "  2: |>| | |<| |" + System.lineSeparator() +
                "  1: | | | | | |" + System.lineSeparator() +
                "  0: | | | | | |" + System.lineSeparator() +
                " -1: -----------" + System.lineSeparator();
        MoveDirection[] directions = new OptionsParser().parse(data);
        IWorldMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Assertions.assertEquals(expectedResult, map.toString());
    }

    @Test
    public void SimulationEngineTest3() {
        String[] data = {"l", "f", "r", "f", "b"};
        String expectedResult = " y\\x  0 1 2 3 4 5 6 7 8 910" + System.lineSeparator() +
                "  5: -----------------------" + System.lineSeparator() +
                "  4: | | | | | | | | | | | |" + System.lineSeparator() +
                "  3: |>|<|^| | | | | | | | |" + System.lineSeparator() +
                "  2: | | | | | | | | | | | |" + System.lineSeparator() +
                "  1: | | | | | | | | | | | |" + System.lineSeparator() +
                "  0: | | | | | | | | | | | |" + System.lineSeparator() +
                " -1: -----------------------" + System.lineSeparator();
        MoveDirection[] directions = new OptionsParser().parse(data);
        IWorldMap map = new RectangularMap(11, 5);
        Vector2d[] positions = {new Vector2d(1, 3), new Vector2d(2, 4), new Vector2d(0, 3)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        Assertions.assertEquals(expectedResult, map.toString());
    }

    @Test
    public void SimulationEngineTest4() {
        String[] data = {"l", "b", "l", "f", "f", "f", "r"};
        MoveDirection[] directions = new OptionsParser().parse(data);
        IWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = {new Vector2d(2, 3), new Vector2d(4, 5), new Vector2d(2, 3)};

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SimulationEngine(directions, map, positions);
        });
    }
}
