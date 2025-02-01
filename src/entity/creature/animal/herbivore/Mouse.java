package entity.creature.animal.herbivore;

import entity.Location;

public class Mouse extends Herbivore{
    public Mouse(Location location) {
        super(0.05, 500, 1, 0.01, location);
    }
}
