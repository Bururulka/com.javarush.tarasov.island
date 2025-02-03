package entity.creature;

import entity.Location;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Caterpillar;
import entity.creature.plant.Plant;
import util.MyRandom;

import java.util.Arrays;
import java.util.List;

public abstract class Creature {
    public boolean isRemove = false;
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
            if(this instanceof Caterpillar) {
                creatureWeight = maxWeight;
            } else {
                creatureWeight = MyRandom.random(maxWeight - maxFood, maxWeight);
            }
        } else if (this instanceof Plant) {
            creatureWeight = maxWeight;
        }

    }

    public double getWeight() {
        return creatureWeight;
    }
    public void setWeight(double weight, Location location) {
        this.creatureWeight = weight;
        if (this.creatureWeight <= (this.creatureMaxWeight - this.creatureMaxFood)) {
            this.die(location);
        }
        if (this.creatureMaxFood == 0){
            this.creatureWeight = weight;
            if (this.creatureWeight == 0){
                this.die(location);
            }
        }
    }
    public void die(Location location){
        List<? extends Creature> creatures = creatureLocation.creatureMap.get(this.getClass());
        if (this instanceof Plant || this instanceof Caterpillar) {
            creatures.remove(this);
        } else if (this instanceof Animal && !(this instanceof Caterpillar)) {
            Animal animal = (Animal) this;
            animal.isRemove = true;
        }
    }
}
