package services;

import entity.Island;
import entity.Location;
import entity.creature.plant.Plant;
import util.MyRandom;
import util.Settings;

public class PlantService implements Runnable{
    Location[][] locations;
    public PlantService(Island island) {
        this.locations = island.getLocations();
    }

    @Override
    public void run() {
        for (Location[] row : locations) {
            for (Location location : row) {
                location.getLock().lock();
            try {
                int needPlants = Settings.maxCountPlantOnLocation - location.creatureMap.get(Plant.class).size();
                if (needPlants > 0) {
                    int rand = MyRandom.random(0,needPlants/3);
                    for (int i = 0; i < rand; i++) {
                        Plant newPlant = new Plant(location);
                        location.creatureMap.get(Plant.class).add(newPlant);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                    location.getLock().unlock();
                }
            }

        }
    }
}
