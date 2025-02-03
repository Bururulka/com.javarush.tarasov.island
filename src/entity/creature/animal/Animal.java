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

import java.lang.reflect.*;
import java.util.*;

public abstract class Animal extends Creature {
    // ОБЩИЕ ХАРАКТЕРИСТИКИ
    // СЫТОСТЬ satiety = ? вес
    // ВЕС ЖИВОТНОГО
    // СКОРОСТЬ ПЕРЕМЕЩЕНИЯ
    public boolean isNew = false;
    Random rand = new Random();
    public Animal(double maxWeight, int maxCountInCell, int maxSpeed, double maxFood, Location location) {
        super(maxWeight, maxCountInCell, maxSpeed, maxFood, location);
        boolean isRemove = false;
        boolean isNew = false;
    }

    public  Animal createAnimal()  {

        Class clazz = null;
        Animal animal = null;

        try {
            clazz = Class.forName(this.getClass().getName());
            Class[] animalClassParams = {Location.class};
            animal = (Animal) clazz.getConstructor(animalClassParams).newInstance(this.creatureLocation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return animal;
    }

    public void eat(){

        double needFood = this.getNeedFood();

        try {
            if (this instanceof Herbivore){
                while(needFood > 0){
                    toEat(needFood);
                    needFood = this.getNeedFood();
                }
            } else if (this instanceof Predator){
                if(needFood > 0){
                   toEat(needFood);
                   needFood = this.getNeedFood();
                }
            }

        } catch (Exception e) {
            return;
        }
    }

    private void toEat(Double needFood){
        Creature c = null;
        Creature creature;
        c = MyRandom.getRandomCreature(this.creatureLocation.creatureMap, this, true);
        if (!(c.isRemove)) {
            int probability = Settings.getProbabilityEat(this.getClass(), c.getClass());
            int ints = MyRandom.random(0, 100);
            if (ints <= probability) {
                double foodWeight = c.getWeight();
                double delta = Math.min(foodWeight, needFood);
                setWeight(getWeight() + delta, this.creatureLocation);
                c.setWeight(foodWeight - delta, this.creatureLocation);
            }
        }
    }

    public void move(Direction dir, ListIterator<Creature> listIterator){
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
            nextLocation.getLock().lock();
            Map<Class, List<Creature>> creatureMapNext = nextLocation.creatureMap;
            List<Creature> creaturesNext = creatureMapNext.get(this.getClass());

            if (creaturesNext.size() < this.creatureMaxCountInCell){
                creaturesNext.add(this);
                this.creatureLocation = nextLocation;
                this.decreaseHealth(currentSpeed);
                listIterator.remove();
            }
            nextLocation.getLock().unlock();
        }
    }


    public void reproduce(){
        Map<Class, List<Creature>> creatureMap = this.creatureLocation.creatureMap;
        List<Creature> newCreatures = new ArrayList<>();
        Class<? extends Creature> s = this.getClass();
        List<Creature> list = creatureMap.get(s);
        Animal animal = null;
        if (list.size() > 1){
            int creatureCount = 0;

            if (this.creatureLocation.newCreatureMap.get(this.getClass()) != null){
                creatureCount = (this.creatureLocation.creatureMap.get(this.getClass()).size() + this.creatureLocation.newCreatureMap.get(this.getClass()).size());
            } else {
                creatureCount = this.creatureLocation.creatureMap.get(this.getClass()).size();
            }
            if  ( creatureCount < this.creatureMaxCountInCell) {
                try {
                    animal = createAnimal();
                } catch (Exception e){
                    animal = null;
                }
                animal.isNew = true;
                newCreatures.add(animal);
                if (this.creatureLocation.newCreatureMap.get(this.getClass()) == null) {
                    this.creatureLocation.newCreatureMap.put(this.getClass(), newCreatures);
                } else {
                    this.creatureLocation.newCreatureMap.get(this.getClass()).addAll(newCreatures);
                }
            }
        }
    }


    protected double getNeedFood() {
        if (creatureMaxFood == 0){
            return 0;
        }else {
            return Math.min(
                    creatureMaxFood,
                    this.creatureMaxWeight - this.creatureWeight);
        }
    }

    private void decreaseHealth(int currentSpeed){
        this.creatureWeight = this.creatureWeight - currentSpeed;
    }
}
