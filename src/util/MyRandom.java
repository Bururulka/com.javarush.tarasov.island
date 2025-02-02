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

    public static Creature getRandomCreature(Map<Class, List<Creature>> creatureMap, Creature eater, boolean herbivore){
        Set<Class> creatureTypes = creatureMap.keySet();
        Set<String>creatureTypesForEat = new HashSet<>();
        Creature randomCreature = null;
        Class s ;
        String creatureType;
        int minSize = 0;

        Iterator<Class> iterator = creatureTypes.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            if (creatureMap.get(s).size()>1){
                minSize = creatureMap.get(s).size();
                Class eaterType = eater.getClass();
                int x = Settings.getProbabilityEat(eaterType, s);
                if (x!=0) {
                    creatureTypesForEat.add(s.getName());
                }
            }
        }
        if(minSize != 0 && !creatureTypesForEat.isEmpty()) {
            creatureType = creatureTypesForEat.stream().skip(random(0, creatureTypesForEat.size())).findFirst().get();
            try {
                Class clazz = Class.forName(creatureType);
                List<? extends Creature> randomList = creatureMap.get(clazz);
                return  randomCreature = randomList.get(MyRandom.random(0, randomList.size() - 1));
            } catch (Exception e){
                return null;
            }
        } else {
            return null;
        }
    }

    public static Creature getRandomCreature(Map<String, List<Creature>> creatureMap){
        Set<String> creatureTypes = creatureMap.keySet();
        String s = creatureTypes.stream().toList().get(MyRandom.random(0, creatureTypes.size() - 1));
        List<? extends Creature> randomList = creatureMap.get(s);
        return randomList.get(MyRandom.random(0, randomList.size() - 1));
    }

    public static Direction getRandomDirection(){
        return Direction.values()[random(0,Direction.values().length-1)];
    }

}
