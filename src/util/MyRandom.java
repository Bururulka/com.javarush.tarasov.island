package util;

import entity.creature.Creature;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MyRandom {
    private MyRandom() {
    }

    public static int random(int min, int max) {
        if (min < max) {
            return ThreadLocalRandom.current().nextInt(min, max);
        } else{
            return min;
        }
    }

    public static double random(double min, double max) {
        if (min < max) {
            return ThreadLocalRandom.current().nextDouble(min, max);
        } else{
            return min;
        }
    }

    public static boolean get(int percentProbably) {
        return random(0, 100) < percentProbably;
    }

    public static Creature getRandomCreature(Map<Class, CopyOnWriteArrayList<Creature>> creatureMap, Creature eater, boolean herbivore){
        Set<Class> creatureTypes = creatureMap.keySet();
        Set<String>creatureTypesForEat = new HashSet<>();
        Class eaterType = eater.getClass();
        Creature randomCreature = null;
        Class s ;
        String creatureType;
        int minSize = 0;

        for(Class map : creatureTypes){
            if (creatureMap.get(map).size()>1){
                if((Settings.getProbabilityEat(eaterType,map)) != 0){
                    creatureTypesForEat.add(map.getName());
                }
            }
        }

        if(!creatureTypesForEat.isEmpty()) {
            creatureType = creatureTypesForEat.stream().skip(random(0, creatureTypesForEat.size())).findFirst().get();
            try {
                Class clazz = Class.forName(creatureType);
                CopyOnWriteArrayList<Creature> randomList = creatureMap.get(clazz);
                if (randomList.size()>1){
                    return  randomCreature = randomList.get(MyRandom.random(0, randomList.size() - 1));
                } else {
                    return null;
                }

            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public static Direction getRandomDirection(){
        return Direction.values()[random(0,Direction.values().length-1)];
    }

}
