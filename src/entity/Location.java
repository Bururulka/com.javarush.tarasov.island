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

// ЛОКАЦИЯ ДОЛЖНА ЗНАТЬ ТЕКУЩЕЕ КОЛ-ВО ЖИВОТНЫХ КОНКРЕТНОГО ВИДА
// НА СЕБЕ
// МАССИВ?

// ЛОКАЦИЮ ТОЖЕ НУЖНО ПРАВИЛЬНО СОЗДАТЬ -
// ИНИЦИАЛИЗИРОВАВ ЕЕ НА СТАРТЕ КАКИМ-ТО КОЛ-ВОМ ЖИВОТНЫХ И РАСТЕНИЙ

public class Location {
    public List wolfList;
    public List boaList;
    public List bearList;
    public List foxList;
    public List eagleList;
    public List plantList;
    public List horseList;
    public List boarList;
    public List buffaloList;
    public List caterpillarList;
    public List deerList;
    public List duckList;
    public List goatList;
    public List mouseList;
    public List rabbitList;
    public List sheepList;
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
        Location location = this;

        wolfList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountWolfOnLocation); i++){
            wolfList.add(new Wolf(location));
        }
        creatureMap.put(Wolf.class, wolfList);

        boaList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountBoaOnLocation); i++){
            boaList.add(new Boa(location));
        }
        creatureMap.put(Boa.class, boaList);

        foxList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountFoxOnLocation); i++){
            foxList.add(new Fox(location));
        }
        creatureMap.put(Fox.class, foxList);

        bearList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountBearOnLocation); i++){
            bearList.add(new Bear(location));
        }
        creatureMap.put(Bear.class, bearList);

        eagleList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0, Settings.maxCountEagleOnLocation); i++){
            eagleList.add(new Eagle(location));
        }
        creatureMap.put(Eagle.class, eagleList);


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

        deerList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountDeerOnLocation); i++){
            deerList.add(new Horse(location));
        }
        creatureMap.put(Deer.class, deerList);

        rabbitList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountRabbitOnLocation); i++){
            rabbitList.add(new Rabbit(location));
        }
        creatureMap.put(Rabbit.class, rabbitList);

        mouseList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountMouseOnLocation); i++){
            mouseList.add(new Mouse(location));
        }
        creatureMap.put(Mouse.class, mouseList);

        goatList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountGoatOnLocation); i++){
            goatList.add(new Goat(location));
        }
        creatureMap.put(Goat.class, goatList);

        sheepList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountSheepOnLocation); i++){
            sheepList.add(new Sheep(location));
        }
        creatureMap.put(Sheep.class, sheepList);

        boarList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountBoarOnLocation); i++){
            boarList.add(new Boar(location));
        }
        creatureMap.put(Boar.class, boarList);

        buffaloList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountBuffaloOnLocation); i++){
            buffaloList.add(new Buffalo(location));
        }
        creatureMap.put(Buffalo.class, buffaloList);
        duckList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountDuckOnLocation); i++){
            duckList.add(new Duck(location));
        }
        creatureMap.put(Duck.class, duckList);

        caterpillarList = new ArrayList();
        for (int i = 0; i < MyRandom.random(0,Settings.maxCountCaterpillarOnLocation); i++){
            caterpillarList.add(new Caterpillar(location));
        }
        creatureMap.put(Caterpillar.class, caterpillarList);
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
                }
            }

        }

        creatureIterator = creatureMap.entrySet().iterator();
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



