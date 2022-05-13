package agh.cs.lab;

import java.util.Arrays;

public class OptionsParser {

    public MoveDirection[] parse(String[] directions) {
        MoveDirection[] parsedDirections = new MoveDirection[directions.length];
        int validDirections = 0;

        for (String direction : directions) {
            switch (direction) {
                case "f":
                case "forward":
                    parsedDirections[validDirections++] = MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    parsedDirections[validDirections++] = MoveDirection.BACKWARD;
                    break;
                case "r":
                case "right":
                    parsedDirections[validDirections++] = MoveDirection.RIGHT;
                    break;
                case "l":
                case "left":
                    parsedDirections[validDirections++] = MoveDirection.LEFT;
                    break;
                default:
                    throw new IllegalArgumentException(direction + " is not legal move specification");
            }
        }

        if (validDirections == directions.length) return parsedDirections;
        else return Arrays.copyOf(parsedDirections, validDirections);
    }

}
