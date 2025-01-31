package entity.creature;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import util.MyRandom;

import java.util.Arrays;
import java.util.List;

public abstract class Creature {
    public double creatureMaxWeight;
    public int creatureMaxSpeed;
    public double creatureMaxFood;
    public double creatureWeight;
    public int creatureMaxCountInCell;
    public Location creatureLocation;
    public Creature(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
            creatureMaxCountInCell = maxCountInCell;
            creatureMaxFood = maxFood;
            creatureMaxWeight = maxWeight;
            creatureMaxSpeed = maxSpeed;
            creatureLocation = location;
        if (this instanceof Animal) {
            creatureWeight = MyRandom.random(maxWeight-maxFood, maxWeight);
        } else if (this instanceof Plant) {
            creatureWeight = maxWeight;
        }

    }

    public double getWeight() {
        return creatureWeight;
    }
    public void setWeight(double weight, Location location) {
        this.creatureWeight = weight;
        if (this.creatureWeight < (this.creatureMaxWeight - this.creatureMaxFood)) {
            this.die(location);
        }
    }
    public void die(Location location){
        List<? extends Creature> creatures = location.creatureMap.get(this.getClass().getSimpleName());
        creatures.remove(this);
        System.out.println(this.getClass().getSimpleName() + " is die");
        System.out.println(this.getClass().getSimpleName() + " count" + creatures.size());
    };
}
