package entity;

import entity.creature.Creature;

public class Island {
    public Location[][] locations;

    private int columnsCount;
    private int rowsCount;

    public Island(int columnsCount, int rowsCount) {
        locations = new Location[columnsCount][rowsCount];
        for (int i = 0; i < columnsCount; i++) {
            for (int j = 0; j < rowsCount; j++) {
                Location location = new Location(this, i, j);
                locations[i][j] = location;
            }
        };
    }

    public Location[][] getLocations() {
        return locations;
    }

    public int getRowsCount() {
        return locations.length;
    }

    public int getColumnsCount() {
        return locations[0].length;
    }

}
