import entity.Island;
import entity.Location;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.creature.plant.Plant;
import util.Settings;
import java.util.Random;
import java.util.Set;

public class Application {
    public static Island island;
    static Random rand = new Random();
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ
        int counter = 0;

        island = new Island(Settings.columnsCount, Settings.rowsCount);
        Location[][] locations = island.getLocations();
        getStat(locations);
        for (Location[] location:locations){
            for (Location cell:location){
                cell.AnimalsDeals();
                getStat(locations);
            }
        }

    }

    public static void getStat(Location[][] locations){

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

        for (Location[] location:locations){
            for (Location cell:location){
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
            }
        }
        System.out.println("Boars " + Boars +
                " " + "Buffalos " + Buffalos +
                " " + "Caterpillars " + Caterpillars +
                " " + "Deers " + Deers + " " +
                "Ducks " + Ducks + " " +
                "Goats " + Goats + " " +
                "Horses " + Horses + " " +
                "Mouses " + Mouses + " " +
                "Rabbits " + Rabbits + " " +
                "Sheeps " + Sheeps + " " +
                "Bears " + Bears + " " +
                "Boas " + Boas + " " +
                "Eagles " + Eagles + " " +
                "Foxes " + Foxes + " " +
                "Wolfs " + Wolfs + " " +
                "Plants " + Plants + " " );
    }

}
