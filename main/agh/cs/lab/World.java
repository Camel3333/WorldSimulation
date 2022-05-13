package agh.cs.lab;

public class World {
    public static void main(String[] args) {
        try{
            String[] data = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "b", "b"};
            MoveDirection[] directions = new OptionsParser().parse(data);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }
    }
}