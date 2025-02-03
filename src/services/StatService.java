package services;

import entity.Island;
import util.Report;

public class StatService implements Runnable {
    private int loop = 1;
    private Island island;
    private Report report;
    public StatService(Island island) {
        this.island = island;
        report = new Report(island);
    }

    @Override
    public void run() {
        System.out.println("\rЦикл: " + loop++);
        report.getStat();
    }
}
