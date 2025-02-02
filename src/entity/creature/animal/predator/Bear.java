package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import util.MyRandom;
import util.Settings;

public class Bear extends Predator{
    public Bear(Location location) {
        super(500, 5, 2, 80, location);
    }
}
