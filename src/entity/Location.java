package entity;

import entity.creature.Creature;
import entity.creature.animal.Animal;
import entity.creature.animal.herbivore.Horse;
import entity.creature.animal.predator.Wolf;
import entity.creature.plant.Plant;
import util.AnimalsType;
import util.Settings;

import java.util.*;

public class Location {
    public Wolf[] wolfArray = new Wolf[30];
    public Plant[] plantArray = new Plant[200];
    public Horse[] horseArray = new Horse[20];
    public Set<Creature> creatureSet = new HashSet<>();
    public Island island;
    Random rand = new Random();
    int x;
    int y;
    public Location(int x, int y, Island island){
        Settings settings = new Settings();
        this.island = island;
        this.x = x;
        this.y = y;
        for (int i = 0; i < rand.nextInt(0,wolfArray.length); i++){
            wolfArray[i] = (Wolf) createAnimal(AnimalsType.WOLF, this);
        }
        for (int i = 0; i < plantArray.length; i++){
            plantArray[i] = new Plant();
        }
        for (int i = 0; i < rand.nextInt(0,horseArray.length); i++){
            horseArray[i] = (Horse) createAnimal(AnimalsType.HORSE, this);
        }
        creatureSet.addAll(Arrays.asList(wolfArray));
        creatureSet.addAll(Arrays.asList(horseArray));
        creatureSet.addAll(Arrays.asList(plantArray));

    }
    public Animal createAnimal(AnimalsType type, Location location){
        switch (type){
            case WOLF:
                return new Wolf();
            case HORSE:
                return new Horse();
            default:
                return null;
        }
    }

    // ЛОКАЦИЯ ДОЛЖНА ЗНАТЬ ТЕКУЩЕЕ КОЛ-ВО ЖИВОТНЫХ КОНКРЕТНОГО ВИДА
    // НА СЕБЕ
    // МАССИВ?

    // ЛОКАЦИЮ ТОЖЕ НУЖНО ПРАВИЛЬНО СОЗДАТЬ -
    // ИНИЦИАЛИЗИРОВАВ ЕЕ НА СТАРТЕ КАКИМ-ТО КОЛ-ВОМ ЖИВОТНЫХ И РАСТЕНИЙ

}
