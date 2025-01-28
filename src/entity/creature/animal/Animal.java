package entity.creature.animal;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.herbivore.Herbivore;
import entity.creature.animal.predator.Predator;
import entity.creature.plant.Plant;
import util.AnimalsType;
import util.Direction;
import entity.Island;
import util.Settings;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.stream.IntStream;

public abstract class Animal extends Creature {
    // ОБЩИЕ ХАРАКТЕРИСТИКИ
    // СЫТОСТЬ satiety = ? вес
    // ВЕС ЖИВОТНОГО
    // СКОРОСТЬ ПЕРЕМЕЩЕНИЯ
    double maxWeight;
    double minWeight;
    int maxCountInCell;
    int maxSpeed;
    double maxFood;

    public Location location;
    Settings settings = new Settings();
    private final String type = this
            .getClass()
            .getSimpleName();
    Random rand = new Random();
    public Animal(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood) {
        this.maxWeight = maxWeight;
        this.maxCountInCell = maxCountInCell;
        this.maxSpeed = maxSpeed;
        this.maxFood = maxFood;
        weight = maxWeight-5;

    }

    public void eat(Creature c, Location location){
        double needFood = getNeedFood();
        int probability = settings.getProbability(this, c);
        int ints = rand.nextInt(0,100);
        if (ints<=probability){
            if (!(needFood <= 0)) {
                if (this instanceof Herbivore && c instanceof Plant){
                    double foodWeight = c.getWeight();
                    double delta = Math.min(foodWeight, needFood);
                    System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                    setWeight(getWeight() + delta, location);
                    c.setWeight(foodWeight - delta, location);

                }
                if (this instanceof Predator && c instanceof Herbivore){
                    double foodWeight = c.getWeight();
                    double delta = Math.min(foodWeight, needFood);
                    setWeight(getWeight() + delta, location);
                    c.setWeight(foodWeight - delta, location);
                    System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                }
            }
        }
    }

    public void move(Direction dir){}


    public Creature reproduce(Animal animal){
        if (this.getClass().toString().equals(animal.getClass().toString())){
            return location.createAnimal(AnimalsType.valueOf(this.getClass().getName()), this.location);
        }
        return null;
    }

    private double getNeedFood() {
        return Math.min(
                this.maxFood,
                this.maxWeight - this.weight);
    }

        void decreaseWeight(){}
}
