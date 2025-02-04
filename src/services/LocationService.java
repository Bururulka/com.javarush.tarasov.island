package services;

import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import util.MyRandom;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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
//        location.getLock().lock();
        Iterator<Map.Entry<Class, CopyOnWriteArrayList<Creature>>> creatureIterator = this.location.creatureMap.entrySet().iterator();

        while (creatureIterator.hasNext()){
            Map.Entry<Class, CopyOnWriteArrayList<Creature>> creature = creatureIterator.next();
            List<Creature> creatures = creature.getValue();
            ListIterator<Creature> listIterator = creatures.listIterator();
            while (listIterator.hasNext()) {
                Creature curentCreature = listIterator.next();
                if (curentCreature instanceof Animal) {
                    Animal animal = (Animal) curentCreature;
                    if (animal.isRemove) {
                        animal.isRemove = false;
                    }
                }
            }
            listIterator = creatures.listIterator();
            while (listIterator.hasNext()) {
                Creature curentCreature = listIterator.next();
                if (curentCreature instanceof Animal) {
                    Animal animal = (Animal) curentCreature;
                    if(animal.isRemove){
                        animal.isRemove = false;
                    }
                    animal.isNew = false;
                    animal.eat();
                    animal.reproduce();
                    animal.move(MyRandom.getRandomDirection(), listIterator);
                    animal.decreaseHealth();
                }
            }
        }

//        this.location.creatureMap.putAll(this.location.newCreatureMap);
//        location.getLock().unlock();
    }
}
