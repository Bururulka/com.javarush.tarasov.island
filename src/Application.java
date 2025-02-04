import entity.Island;
import entity.Location;
import services.*;
import util.Settings;
import java.util.*;
import java.util.concurrent.*;

public class Application {
    public static Island island;
    public static void main(String[] args) {
        // ТОЧКА СБОРКИ И СТАРТА МОЕГО ПРИЛОЖЕНИЯ
        long l = System.currentTimeMillis();
        island = new Island(Settings.columnsCount, Settings.rowsCount);
        Location[][] locations = island.getLocations();
        long l1 = System.currentTimeMillis();
        System.out.println("Время создания острова(мс): " + (l1 - l));
//        List locationsList = new ArrayList();
//        for (Location[] row : locations) {
//            locationsList.addAll(Arrays.asList(row));
//        }

        //Исполнитель для запуска роста растений
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        PlantService plantService = new PlantService(island);
        scheduledExecutorService.scheduleWithFixedDelay(plantService, 0, 1, TimeUnit.SECONDS);

        StatService statService = new StatService(island);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        for (int i = 0; i < Settings.loops; i++) {
            List<Future<?>> futureList = new ArrayList<>();
            for(Location[] location : locations){
                for( Location cell : location){
                    futureList.add(executorService.submit(new LocationService(island, cell)));
                }
            }
            if (!futureList.isEmpty()) {
                while (!futureList.get(futureList.size()-1).isDone()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                executorService.submit(statService);
            }
        }

        //завершаем всё
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        scheduledExecutorService.shutdown();

        l1 = System.currentTimeMillis();
        System.out.println("Время симуляции: " + (l1 - l)/1000 + " секунд.");
    }

}


