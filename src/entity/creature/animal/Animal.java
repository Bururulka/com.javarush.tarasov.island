package entity.creature.animal;

import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.herbivore.Herbivore;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.predator.Predator;
import entity.creature.animal.predator.Wolf;
import entity.creature.plant.Plant;
import util.Direction;
import util.MyRandom;
import util.Settings;

import java.util.*;

public abstract class Animal extends Creature {
    // ОБЩИЕ ХАРАКТЕРИСТИКИ
    // СЫТОСТЬ satiety = ? вес
    // ВЕС ЖИВОТНОГО
    // СКОРОСТЬ ПЕРЕМЕЩЕНИЯ
    boolean isMove = false;
    boolean isNew = false;
    Settings settings = new Settings();
    Random rand = new Random();
    public Animal(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
    }

    public void eat(){
        double needFood = getNeedFood();
        while (needFood > 1){
            try {
                Creature c = MyRandom.getRandomCreature(this.creatureLocation.creatureMap);
                int probability = settings.getProbabilityEat(this, c);
                int ints = rand.nextInt(0, 100);
                if (ints <= probability) {
                    if (this instanceof Herbivore && c instanceof Plant) {
                        needFood = getNeedFood();
                        double foodWeight = c.getWeight();
                        double delta = Math.min(foodWeight, needFood);
                        System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                        setWeight(getWeight() + delta, this.creatureLocation);
                        c.setWeight(foodWeight - delta, c.creatureLocation);
                    }


                    if (this instanceof Predator && c instanceof Herbivore) {
                        needFood = getNeedFood();
                        double foodWeight = c.getWeight();
                        double delta = Math.min(foodWeight, needFood);
                        setWeight(getWeight() + delta, this.creatureLocation);
                        c.setWeight(foodWeight - delta, this.creatureLocation);
                        System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                    }
                }
            } catch (IllegalArgumentException e){
                return;
            }
        }
    }

    public Creature move(Direction dir){
        Location nextLocation = null;
        int currentSpeed = MyRandom.random(0,this.creatureMaxSpeed);

        Location currentLocation = this.creatureLocation;
        nextLocation = currentLocation.getNextLocation(dir, currentSpeed);

        if (nextLocation != null){
            Map<String, List<Creature>> creatureMapNext = nextLocation.creatureMap;
//            List<Creature> creaturesCurrent = creatureLocation.creatureMap.get(this.getClass().getSimpleName());
            List<Creature> creaturesNext = creatureMapNext.get(this.getClass().getSimpleName());

            if (creaturesNext.size() < this.creatureMaxCountInCell){
                creaturesNext.add(this);
//                creaturesCurrent.remove(this);
                return this;
            }
        } else {
            System.out.println(this.getClass().getSimpleName() + " не удалось никуда сходить");
        }
        return null;
    }


    public Creature reproduce(){
        Map<String, List<Creature>> creatureMap = this.creatureLocation.creatureMap;
        String s = this.getClass().getSimpleName();
        List<Creature> list = creatureMap.get(s);
        ListIterator<Creature> iterator = list.listIterator();
        if (list.size() > 2){
//            try{
//                Class c = Class.forName(s);
//                Object object = c.newInstance();
//                Creature newCreature = (Creature) object;
//                List.add(newCreature);
//                this.creatureLocation.creatureMap.put(s,List);
//                System.out.println("Родился новый " + s);
//            } catch (Exception e){
//                System.out.println(s + " не смог размножиться");
//            }
            switch (s){
                case "Horse" ->{
                    Horse horse = new Horse(this.creatureLocation);
//                    iterator.add(new Horse(this.creatureLocation));
                    System.out.println("Родился новый " + s);
                    return horse;
                }
                case "Wolf" ->{
                    Wolf wolf = new Wolf(this.creatureLocation);
//                    iterator.add(new Wolf(this.creatureLocation));
                    System.out.println("Родился новый " + s);
                    return wolf;
                }
                default -> {}
            }
        }
        return null;
    }


    private double getNeedFood() {
        return Math.min(
                creatureMaxFood,
                this.creatureMaxWeight - this.creatureWeight);
    }
}
