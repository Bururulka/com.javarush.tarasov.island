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
    public boolean isRemove = false;
    public boolean isNew = false;
    Settings settings = new Settings();
    Random rand = new Random();
    public Animal(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
        boolean isRemove = false;
        boolean isNew = false;
    }

    public void eat(){
        Creature c;
        try {
            double needFood = getNeedFood();

            if (this.getClass().getSuperclass() == Herbivore.class) {
                while (needFood > 1) {
                c = this.creatureLocation.creatureMap.get(Plant.class).getFirst();
                needFood = getNeedFood();
                double foodWeight = c.getWeight();
                double delta = Math.min(foodWeight, needFood);
                System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                setWeight(getWeight() + delta, this.creatureLocation);
                c.setWeight(foodWeight - delta, c.creatureLocation);
                }
            }

            if (this.getClass().getSuperclass() == Predator.class) {
                c = MyRandom.getRandomCreature(this.creatureLocation.creatureMap, true);
                int probability = settings.getProbabilityEat(this, c);
                int ints = rand.nextInt(0, 100);
                if (ints <= probability) {
                    Animal animal = (Animal) c;
                    if (!(animal.isRemove)) {
                        needFood = getNeedFood();
                        double foodWeight = c.getWeight();
                        double delta = Math.min(foodWeight, needFood);
                        setWeight(getWeight() + delta, this.creatureLocation);
                        c.setWeight(foodWeight - delta, this.creatureLocation);
                        System.out.println(this.getClass().getSimpleName() + ": eating" + c.getClass().getSimpleName());
                    }
                }
            }
        } catch (Exception e) {
            return;
        }

    }

    public void move(Direction dir){
        Location nextLocation = null;
        int currentSpeed;
        if(this.creatureMaxSpeed !=0) {
            currentSpeed = MyRandom.random(0, this.creatureMaxSpeed);
        } else {
            currentSpeed = 0;
        }

        Location currentLocation = this.creatureLocation;
        nextLocation = currentLocation.getNextLocation(dir, currentSpeed);

        if (nextLocation != null){
            Map<Class, List<Creature>> creatureMapNext = nextLocation.creatureMap;
            List<Creature> creaturesNext = creatureMapNext.get(this.getClass());

            if (creaturesNext.size() < this.creatureMaxCountInCell){
                creaturesNext.add(this);
                this.creatureLocation = nextLocation;
                this.isRemove = true;
            }
        } else {
            System.out.println(this.getClass().getSimpleName() + " не удалось никуда сходить");
        }
    }


    public void reproduce(){
        Map<Class, List<Creature>> creatureMap = this.creatureLocation.creatureMap;
        Map<Class, List<Creature>> newCreatureMap = this.creatureLocation.newCreatureMap;
        List<Creature> newCreatures = new ArrayList<>();
        Class<? extends Creature> s = this.getClass();
        List<Creature> list = creatureMap.get(s);
        List<Creature> newList = newCreatureMap.get(s);
        if (list.size() > 1){
            switch (s.getSimpleName()){
                case "Horse" ->{
                    int creatureCount = 0;
                    if (this.creatureLocation.newCreatureMap.get(Horse.class) != null){
                        creatureCount = (this.creatureLocation.creatureMap.get(Horse.class).size() + this.creatureLocation.newCreatureMap.get(Horse.class).size());
                    } else {
                        creatureCount = this.creatureLocation.creatureMap.get(Horse.class).size();
                    }
                    if  ( creatureCount < this.creatureMaxCountInCell) {
                        Horse horse = new Horse(this.creatureLocation);
                        horse.isNew = true;
                        newCreatures.add(horse);
                        if (this.creatureLocation.newCreatureMap.get(Horse.class) == null) {
                            this.creatureLocation.newCreatureMap.put(Horse.class, newCreatures);
                        } else {
                            this.creatureLocation.newCreatureMap.get(Horse.class).addAll(newCreatures);
                        }
                        System.out.println("Родился новый " + s);
                    }
                }
                case "Wolf" ->{
                    int creatureCount = 0;
                    if (this.creatureLocation.newCreatureMap.get(Wolf.class) != null){
                        creatureCount = (this.creatureLocation.creatureMap.get(Wolf.class).size() + this.creatureLocation.newCreatureMap.get(Wolf.class).size());
                    } else {
                        creatureCount = this.creatureLocation.creatureMap.get(Wolf.class).size();
                    }
                    if  ( creatureCount < this.creatureMaxCountInCell) {
                        Wolf wolf = new Wolf(this.creatureLocation);
                        wolf.isNew = true;
                        newCreatures.add(wolf);
                        if (this.creatureLocation.newCreatureMap.get(Wolf.class) == null) {
                            this.creatureLocation.newCreatureMap.put(Wolf.class, newCreatures);
                        } else {
                            this.creatureLocation.newCreatureMap.get(Wolf.class).addAll(newCreatures);
                        }
                        System.out.println("Родился новый " + s);
                    }
                }
                default -> {}
            }
        }
    }


    private double getNeedFood() {
        return Math.min(
                creatureMaxFood,
                this.creatureMaxWeight - this.creatureWeight);
    }
}
