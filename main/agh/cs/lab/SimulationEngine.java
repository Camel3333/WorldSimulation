package agh.cs.lab;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private MoveDirection[] directions;
    private IWorldMap map;
    private final List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        for (Vector2d position : positions) {
            Animal animal = new Animal(this.map, position);
            if (this.map.place(animal)) this.animals.add(animal);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < this.directions.length; i++) {
            this.animals.get(i % this.animals.size()).move(this.directions[i]);
        }
    }

}
