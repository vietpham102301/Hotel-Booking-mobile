package com.example.hotelbooking.pieChart;


import java.util.ArrayList;

public class BookedRoomRes {
    ArrayList<PieChartData> data;

    public BookedRoomRes(ArrayList<PieChartData> data) {
        this.data = data;
    }

    public ArrayList<PieChartData> getData() {
        return data;
    }
}
