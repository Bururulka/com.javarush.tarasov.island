import entity.Island;
import entity.Location;
import entity.creature.animal.herbivore.*;
import entity.creature.animal.predator.*;
import entity.creature.plant.Plant;
import services.*;
import util.Report;
import util.Settings;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class Application {
    public static Island island;
    static Random rand = new Random();
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ

        long l = System.currentTimeMillis();

        island = new Island(Settings.columnsCount, Settings.rowsCount);
        Location[][] locations = island.getLocations();
        long l1 = System.currentTimeMillis();
        System.out.println("Время создания острова(мс): " + (l1 - l));



        //Исполнитель для запуска роста растений
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        PlantService plantService = new PlantService(island);
        scheduledExecutorService.scheduleWithFixedDelay(plantService, 0, 1, TimeUnit.SECONDS);
        Report report = new Report(island);
        report.getStat();


    }

}
