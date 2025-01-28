import entity.Island;
import entity.Location;
import entity.creature.Creature;
import entity.creature.animal.Animal;
import util.Direction;
import util.Settings;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Application {
    public static Island island;
    static Random rand = new Random();
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ
        island = new Island(Settings.columnsCount, Settings.rowsCount);
        Location[][] locations = island.getLocations();
        Arrays.asList(locations).forEach(location ->{
            Arrays.asList(location).forEach(row->{
                Set<Creature> creatureSet = row.creatureSet;
                while (!creatureSet.isEmpty()) {
                    for (Creature creature : creatureSet) {
                        if (creature instanceof Animal animal) {
                                animal.eat(getRandomCreture(creatureSet), row);
//                            animal.move(getRandomDirection());
//                            animal.reproduce(getRandomCretureReproduce(creatureSet));
                        }
                    }
                }
            });
        });

    }
    public static Creature getRandomCreture(Set<Creature> creatureSet) {
        int randomIndex = ThreadLocalRandom.current().nextInt(creatureSet.size());
        return creatureSet.stream().skip(randomIndex).findFirst().orElse(null);
    }
    public static Direction getRandomDirection() {
         return Direction.values()[rand.nextInt(Direction.values().length)];
    }
    public static Animal getRandomCretureReproduce(Set<Creature> creatureSet) {
        int randomIndex = rand.nextInt(creatureSet.size());
        return null;
    }

}
