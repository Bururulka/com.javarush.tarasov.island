package util;

import entity.Island;
import entity.Location;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.creature.plant.Plant;

import java.util.*;

public class Report {
    private  Island island;
    public Report(Island island) {
        this.island = island;
    }

//    public List<Map<String, Long>> getReport() {
//        List<Map<String, Long>> report = new ArrayList<>();
//        Location[][] locations = island.getLocations();
//        for (Location[] row : locations) {
//            for (Location location : row) {
//                location.getLock().lock();
//                try {
////                report.add(location.getCreatureGroupBy());
//                } finally {
//                    location.getLock().unlock();
//                }
//            }
//
//
//        }
//        return report;
//    }

    public void getStat(){

        int Boars = 0;
        int Buffalos= 0;
        int Caterpillars= 0;
        int Deers= 0;
        int Ducks= 0;
        int Goats= 0;
        int Horses= 0;
        int Mouses= 0;
        int Rabbits= 0;
        int Sheeps= 0;
        int Bears= 0;
        int Boas= 0;
        int Eagles= 0;
        int Foxes= 0;
        int Wolfs= 0;
        int Plants= 0;
        Location[][] locations = this.island.getLocations();

        for (Location[] location:locations){
            for (Location cell:location){
                cell.getLock().lock();
                Set<Class> classes = cell.creatureMap.keySet();
                for (Class c:classes){
                    Boars  = Boars + cell.creatureMap.get(Boar.class).size();
                    Buffalos = Buffalos + cell.creatureMap.get(Buffalo.class).size();
                    Caterpillars = Caterpillars + cell.creatureMap.get(Caterpillar.class).size();
                    Deers = Deers + cell.creatureMap.get(Deer.class).size();
                    Ducks = Ducks + cell.creatureMap.get(Duck.class).size();
                    Goats = Goats + cell.creatureMap.get(Goat.class).size();
                    Horses = Horses + cell.creatureMap.get(Horse.class).size();
                    Mouses = Mouses + cell.creatureMap.get(Mouse.class).size();
                    Rabbits = Rabbits + cell.creatureMap.get(Rabbit.class).size();
                    Sheeps = Sheeps + cell.creatureMap.get(Sheep.class).size();
                    Bears = Bears + cell.creatureMap.get(Bear.class).size();
                    Boas = Boas + cell.creatureMap.get(Boa.class).size();
                    Eagles = Eagles + cell.creatureMap.get(Eagle.class).size();
                    Foxes = Foxes + cell.creatureMap.get(Fox.class).size();
                    Wolfs = Wolfs + cell.creatureMap.get(Wolf.class).size();
                    Plants = Plants + cell.creatureMap.get(Plant.class).size();

                }
                cell.getLock().unlock();
            }
        }
        System.out.println("\uD83D\uDC17" + Boars +
                " " + "\uD83D\uDC03" + Buffalos +
                " " + "\uD83D\uDC1B" + Caterpillars +
                " " + "\uD83E\uDD8C" + Deers + " " +
                "\uD83E\uDD86" + Ducks + " " +
                "\uD83D\uDC10" + Goats + " " +
                "\uD83D\uDC0E" + Horses + " " +
                "\uD83D\uDC01" + Mouses + " " +
                "\uD83D\uDC07" + Rabbits + " " +
                "\uD83D\uDC11" + Sheeps + " " +
                "\uD83D\uDC3B" + Bears + " " +
                "\uD83D\uDC0D" + Boas + " " +
                "\uD83E\uDD85" + Eagles + " " +
                "\uD83E\uDD8A" + Foxes + " " +
                "\uD83D\uDC3A" + Wolfs + " " +
                "Plants " + Plants + " " );
    }
}
