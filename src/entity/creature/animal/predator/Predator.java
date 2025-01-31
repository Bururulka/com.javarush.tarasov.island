package entity.creature.animal.predator;

import entity.Location;
import entity.creature.animal.Animal;

public class Predator extends Animal {
    public Predator(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
    }
}
