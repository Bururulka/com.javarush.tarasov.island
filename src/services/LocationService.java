package services;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import util.MyRandom;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocationService implements Runnable{
    private  Island island;
    private  Location location;
    public LocationService(Island island, Location location){
        this.island = island;
        this.location = location;
    }

    @Override
    public void run() {
        try {
            life();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void life() throws Exception {

        Set<Class> classes = location.creatureMap.keySet();
        for(Class clazz: classes){
            for(Creature currentCreature: location.creatureMap.get(clazz)){
                if (currentCreature instanceof Animal) {
                    Animal animal = (Animal) currentCreature;
                    animal.eat();
                    animal.reproduce();
                    animal.move(MyRandom.getRandomDirection() );
                    animal.decreaseHealth();
                }
            }
        }


    }
}
