package entity.creature.plant;

import entity.Location;
import entity.creature.Creature;

import java.util.List;

public class Plant extends Creature {
    public Plant(Location location) {
        super(1,200,0,0,location);
    }
}
