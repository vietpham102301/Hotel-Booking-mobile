package com.example.hotelbooking.barChart.model;

public class BarChartData {
    private int ind;
    private String month;
    private Float total;

    public BarChartData(int ind, String month, Float total) {
        this.ind = ind;
        this.month = month;
        this.total = total;
    }

    public int getInd() {
        return ind;
    }

    public String getMonth() {
        return month;
    }

    public Float getTotal() {
        return total;
    }
}
