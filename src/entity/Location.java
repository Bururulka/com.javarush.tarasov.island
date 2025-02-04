package entity;

import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.creature.plant.Plant;
import util.Direction;
import util.MyRandom;
import util.Settings;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

// ЛОКАЦИЯ ДОЛЖНА ЗНАТЬ ТЕКУЩЕЕ КОЛ-ВО ЖИВОТНЫХ КОНКРЕТНОГО ВИДА
// НА СЕБЕ
// МАССИВ?

// ЛОКАЦИЮ ТОЖЕ НУЖНО ПРАВИЛЬНО СОЗДАТЬ -
// ИНИЦИАЛИЗИРОВАВ ЕЕ НА СТАРТЕ КАКИМ-ТО КОЛ-ВОМ ЖИВОТНЫХ И РАСТЕНИЙ

public class Location {

    public Map<Class, CopyOnWriteArrayList<Creature>>creatureMap = new HashMap<>();
    public Map<Class, CopyOnWriteArrayList<Creature>>newCreatureMap = new HashMap<>();
    public Island island;
    public int x;
    public int y;
    public ReentrantLock lock;

    public Location(Island island, int x, int y){
        this.island = island;
        this.x = x;
        this.y = y;
        Location location = this;
        lock = new ReentrantLock();

        CopyOnWriteArrayList<Creature>wolfList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountWolfOnLocation); i++){
            wolfList.add(new Wolf(location));
        }
        creatureMap.put(Wolf.class, wolfList);

        CopyOnWriteArrayList<Creature>boaList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountBoaOnLocation); i++){
            boaList.add(new Boa(location));
        }
        creatureMap.put(Boa.class, boaList);

        CopyOnWriteArrayList<Creature>foxList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountFoxOnLocation); i++){
            foxList.add(new Fox(location));
        }
        creatureMap.put(Fox.class, foxList);

        CopyOnWriteArrayList<Creature>bearList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountBearOnLocation); i++){
            bearList.add(new Bear(location));
        }
        creatureMap.put(Bear.class, bearList);

        CopyOnWriteArrayList<Creature>eagleList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountEagleOnLocation); i++){
            eagleList.add(new Eagle(location));
        }
        creatureMap.put(Eagle.class, eagleList);


        CopyOnWriteArrayList<Creature>plantList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < Settings.maxCountPlantOnLocation; i++){
            plantList.add(new Plant(location));
        }
        creatureMap.put(Plant.class, plantList);


        CopyOnWriteArrayList<Creature>horseList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountHorseOnLocation); i++){
            horseList.add(new Horse(location));
        }
        creatureMap.put(Horse.class, horseList);

        CopyOnWriteArrayList<Creature>deerList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountDeerOnLocation); i++){
            deerList.add(new Horse(location));
        }
        creatureMap.put(Deer.class, deerList);

        CopyOnWriteArrayList<Creature>rabbitList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountRabbitOnLocation); i++){
            rabbitList.add(new Rabbit(location));
        }
        creatureMap.put(Rabbit.class, rabbitList);

        CopyOnWriteArrayList<Creature>mouseList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountMouseOnLocation); i++){
            mouseList.add(new Mouse(location));
        }
        creatureMap.put(Mouse.class, mouseList);

        CopyOnWriteArrayList<Creature>goatList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountGoatOnLocation); i++){
            goatList.add(new Goat(location));
        }
        creatureMap.put(Goat.class, goatList);

        CopyOnWriteArrayList<Creature>sheepList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountSheepOnLocation); i++){
            sheepList.add(new Sheep(location));
        }
        creatureMap.put(Sheep.class, sheepList);

        CopyOnWriteArrayList<Creature>boarList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountBoarOnLocation); i++){
            boarList.add(new Boar(location));
        }
        creatureMap.put(Boar.class, boarList);

        CopyOnWriteArrayList<Creature>buffaloList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountBuffaloOnLocation); i++){
            buffaloList.add(new Buffalo(location));
        }
        creatureMap.put(Buffalo.class, buffaloList);
        CopyOnWriteArrayList<Creature>duckList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountDuckOnLocation); i++){
            duckList.add(new Duck(location));
        }
        creatureMap.put(Duck.class, duckList);

        CopyOnWriteArrayList<Creature>caterpillarList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountCaterpillarOnLocation); i++){
            caterpillarList.add(new Caterpillar(location));
        }
        creatureMap.put(Caterpillar.class, caterpillarList);
    }

    public Location getNextLocation(Direction direction, int speed){

        Location[][] locations = this.island.getLocations();

        for(int i = 0; i < speed; i++){
            switch (direction){

                case UP -> {
                    if (y < Settings.rowsCount-1){
                        return locations[x][y+1];
                    } else {
                        return null;
                    }
                }

                case DOWN -> {
                    if (y > 1){
                        return locations[x][y-1];
                    } else {
                        return null;
                    }
                }

                case LEFT -> {
                    if (x > 1){
                        return locations[x-1][y];
                    } else {
                        return null;
                    }
                }

                case RIGHT ->  {

                    if (x < Settings.columnsCount-1){
                        return locations[x+1][y];
                    } else {
                        return null;
                    }
                }

                default -> {
                    return null;
                }
            }
        }
        return null;
    }

    public boolean addCreature(Creature creature){
        lock.lock();
        try {
            Class clazz = creature.getClass();
            if (creature.creatureMaxCountInCell > this.creatureMap.get(clazz).size()) {
                this.creatureMap.get(clazz).add(creature);
                return true;
            }
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return false;
    }

    public void removeCreature(Creature creature){
        lock.lock();
        try {
            Class clazz = creature.getClass();
            if (creatureMap.get(clazz).contains(creature)) {
                creatureMap.get(clazz).remove(creature);
            }
        }catch (Exception e){
        e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }
}



