package entity.creature.animal.herbivore;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.plant.Plant;
import util.MyRandom;
import util.Settings;

public class Herbivore extends Animal {
    public Herbivore(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
    }

//    @Override
//    public void eat() {
//        Creature c;
//        try {
//            double needFood = this.getNeedFood();
//            while (needFood > 1) {
//                c = this.creatureLocation.creatureMap.get(Plant.class).getFirst();
//                needFood = getNeedFood();
//                double foodWeight = c.getWeight();
//                double delta = Math.min(foodWeight, needFood);
//                System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
//                setWeight(getWeight() + delta, this.creatureLocation);
//                c.setWeight(foodWeight - delta, c.creatureLocation);
//                System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
//            }
//        } catch (Exception e) {
//            return;
//        }
//    }
}
