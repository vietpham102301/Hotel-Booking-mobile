package com.example.hotelbooking.pieChart;

public class PieChartData {
    private int id;
    private String name;
    private int quantity;

    public PieChartData(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
