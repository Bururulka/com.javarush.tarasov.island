package util;

import entity.creature.Creature;
import entity.creature.animal.herbivore.Herbivore;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

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

    public static Creature getRandomCreature(Map<Class, List<Creature>> creatureMap, boolean herbivore){
        Set<Class> creatureTypes = creatureMap.keySet();
        Creature randomCreature = null;
        Class s = null;
        int minSize = 0;

        Iterator<Class> iterator = creatureTypes.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            if (s.getSuperclass() == Herbivore.class){
                minSize = creatureMap.get(s).size();
            }
        }
        if(minSize != 0) {
            if (creatureTypes != null) {
                s = null;
                s = creatureTypes.stream().filter(c -> c.getSuperclass() == Herbivore.class).findAny().get();
                List<? extends Creature> randomList = creatureMap.get(s);
                if (randomList.get(MyRandom.random(0, randomList.size() - 1)) instanceof Herbivore) {
                    randomCreature = randomList.get(MyRandom.random(0, randomList.size() - 1));
                } else {
                    randomCreature = MyRandom.getRandomCreature(creatureMap, true);
                }
            } else {
                return null;
            }
        } else {
            return null;
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
