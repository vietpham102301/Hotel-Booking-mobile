package com.example.hotelbooking.barChart.model;

import java.util.ArrayList;

public class RevenueRes {
    ArrayList<BarChartData> data;

    public RevenueRes(ArrayList<BarChartData> data) {
        this.data = data;
    }

    public ArrayList<BarChartData> getData() {
        return data;
    }
}
