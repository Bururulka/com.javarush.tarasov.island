package entity.creature.animal.predator;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import util.MyRandom;
import util.Settings;

import java.util.Random;

public class Predator extends Animal {
    public Predator(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
    }

//    @Override
//    public void eat(){
//        Creature c = null;
//        double needFood = this.getNeedFood();
//        while (needFood >0) {
//            c = MyRandom.getRandomCreature(this.creatureLocation.creatureMap, this, true);
//
//            int probability = Settings.getProbabilityEat(this.getClass(), c.getClass());
//
//            int ints = MyRandom.random(0, 100);
//            if (ints <= probability) {
//                Animal animal = (Animal) c;
//                if (!(animal.isRemove)) {
//                    needFood = getNeedFood();
//                    double foodWeight = c.getWeight();
//                    double delta = Math.min(foodWeight, needFood);
//                    setWeight(getWeight() + delta, this.creatureLocation);
//                    c.setWeight(foodWeight - delta, this.creatureLocation);
//                    System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
//                }
//            } else {
//                System.out.println(this.getClass().getSimpleName() + " никого не удалось скушать");
//            }
//        }
//    }
}
