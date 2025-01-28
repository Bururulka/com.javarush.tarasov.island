package entity;

public class Island {

    private int columnsCount;
    private int rowsCount;
    public Location[][] locations;
    public Island(int columnsCount, int rowsCount) {
        this.columnsCount = columnsCount;
        this.rowsCount = rowsCount;
        locations = new Location[this.columnsCount][this.rowsCount];
        for (int i = 0; i < columnsCount; i++) {
            for (int j = 0; j < rowsCount; j++) {
                locations[i][j] = new Location(i,j, this);
            }
        }
    }
    public Island getIsland(){
        return this;
    }

    public Location[][] getLocations(){
        return locations;
    }

}
