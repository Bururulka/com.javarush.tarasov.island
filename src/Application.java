import entity.Island;
import entity.Location;
import util.Settings;
import java.util.Random;

public class Application {
    public static Island island;
    static Random rand = new Random();
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ
        int counter = 0;
        island = new Island(Settings.columnsCount, Settings.rowsCount);
        Location[][] locations = island.getLocations();
        for (Location[] location:locations){
            for (Location cell:location){
                System.out.println(counter);
                cell.AnimalsDeals();
                counter+=1;
            }
        }

    }

}
