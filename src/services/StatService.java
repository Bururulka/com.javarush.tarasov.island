package services;

import util.Report;

public class StatService implements Runnable {
    private int loop = 1;
    private Report report;
    public StatService(Report report) {
        this.report = report;
    }

    @Override
    public void run() {
        System.out.println("\rЦикл: " + loop++);
        System.out.println("\r" + report.getReport());
    }
}
