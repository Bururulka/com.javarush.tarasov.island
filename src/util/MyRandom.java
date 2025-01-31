package util;

import entity.creature.Creature;
import entity.creature.animal.herbivore.Herbivore;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class MyRandom {
    private MyRandom() {
    }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static double random(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static boolean get(int percentProbably) {
        return random(0, 100) < percentProbably;
    }

    public static Creature getRandomCreature(Map<String, List<? extends Creature>> creatureMap, boolean herbivore){
        Set<String> creatureTypes = creatureMap.keySet();
        Creature randomCreature = null;
        String s;
        s = creatureTypes.stream().toList().get(MyRandom.random(0, creatureTypes.size() - 1));
        List<? extends Creature> randomList = creatureMap.get(s);
        if (randomList.get(MyRandom.random(0, randomList.size() - 1)) instanceof Herbivore){
            randomCreature = randomList.get(MyRandom.random(0, randomList.size() - 1));
        } else {
            randomCreature = MyRandom.getRandomCreature(creatureMap, true);
        }
        return randomCreature;
    }

    public static Creature getRandomCreature(Map<String, List<Creature>> creatureMap){
        Set<String> creatureTypes = creatureMap.keySet();
        String s = creatureTypes.stream().toList().get(MyRandom.random(0, creatureTypes.size() - 1));
        List<? extends Creature> randomList = creatureMap.get(s);
        return randomList.get(MyRandom.random(0, randomList.size() - 1));
    }

    public static Direction getRandomDirection(){
        return Direction.values()[ThreadLocalRandom.current().nextInt(Direction.values().length)];
    }
}
