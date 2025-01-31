package entity;

import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.predator.Wolf;
import entity.creature.plant.Plant;
import util.Direction;
import util.MyRandom;
import util.Settings;

import java.util.*;

// ЛОКАЦИЯ ДОЛЖНА ЗНАТЬ ТЕКУЩЕЕ КОЛ-ВО ЖИВОТНЫХ КОНКРЕТНОГО ВИДА
// НА СЕБЕ
// МАССИВ?

// ЛОКАЦИЮ ТОЖЕ НУЖНО ПРАВИЛЬНО СОЗДАТЬ -
// ИНИЦИАЛИЗИРОВАВ ЕЕ НА СТАРТЕ КАКИМ-ТО КОЛ-ВОМ ЖИВОТНЫХ И РАСТЕНИЙ

public class Location {
    public List wolfList;
    public List plantList;
    public List horseList;
    public Map<Class, List<Creature>>creatureMap = new HashMap<>();
    public Map<Class, List<Creature>>newCreatureMap = new HashMap<>();
    public Map<Class, List<Creature>>removeCreatureMap = new HashMap<>();
    public Island island;
    public int x;
    public int y;

    public Location(Island island, int x, int y){
        this.island = island;
        this.x = x;
        this.y = y;

        wolfList = new ArrayList();
        Location location = this;
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountWolfOnLocation); i++){
            wolfList.add(new Wolf(location));
        }
        creatureMap.put(Wolf.class, wolfList);

        plantList = new ArrayList();
        for (int i = 0; i < Settings.maxCountPlantOnLocation; i++){
            plantList.add(new Plant(location));
        }
        creatureMap.put(Plant.class, plantList);
        horseList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountHorseOnLocation); i++){
            horseList.add(new Horse(location));
        }
        creatureMap.put(Horse.class, horseList);
    }

    public void AnimalsDeals(){

        Iterator<Map.Entry<Class, List<Creature>>>creatureIterator = creatureMap.entrySet().iterator();

        while (creatureIterator.hasNext()){
            Map.Entry<Class, List<Creature>> creature = creatureIterator.next();
            List<Creature> creatures = creature.getValue();
            ListIterator<Creature> listIterator = creatures.listIterator();

            while (listIterator.hasNext()) {
                Creature curentCreature = listIterator.next();
                if (curentCreature instanceof Animal) {
                    Animal animal = (Animal) curentCreature;
                    animal.isRemove = false;
                    animal.isNew = false;
                    animal.eat();
                    animal.reproduce();
                    animal.move(MyRandom.getRandomDirection());
                }
            }

        }

        while (creatureIterator.hasNext()){
            Map.Entry<Class, List<Creature>> creature = creatureIterator.next();
            List<Creature> creatures = creature.getValue();
            ListIterator<Creature> listIterator = creatures.listIterator();

            while (listIterator.hasNext()) {
                Creature curentCreature = listIterator.next();
                if (curentCreature instanceof Animal) {
                    Animal animal = (Animal) curentCreature;
                    if(animal.isRemove){
                        listIterator.remove();
                    }
                }
            }

        }

        creatureMap.putAll(newCreatureMap);

    }
    public Location getNextLocation(Direction direction, int speed){

        Location currentLocation = this;
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
}



