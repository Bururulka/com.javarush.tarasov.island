package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.animal.Animal;

public class Herbivore extends Animal {
    public Herbivore(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
    }
}
