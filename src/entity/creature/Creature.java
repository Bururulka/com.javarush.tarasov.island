package entity.creature;

import entity.Location;

public abstract class Creature {
//    private final String name;
//    private final String icon;
    public double weight;
//    private final Limit limit;
    public Creature() {
//        this.name = name;
//        this.icon = icon;
//        this.limit = limit;
//        weight = Rnd.random(limit.getMaxWeight() / 2, limit.getMaxWeight());
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight, Location location) {
        this.weight = weight;
        if (this.weight <= 0) {
            this.die(location);
        }
    }
    public void die(Location location){
        location.creatureSet.remove(this);
        System.out.println(this.getClass().getSimpleName() + " is die");
    };
}
