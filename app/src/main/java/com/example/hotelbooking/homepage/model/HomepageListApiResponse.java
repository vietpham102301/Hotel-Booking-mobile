package com.example.hotelbooking.homepage.model;

import java.util.ArrayList;

public class HomepageListApiResponse {
    private boolean status;
    private String message;
    private ArrayList<HomepageList> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<HomepageList> getData() {
        return data;
    }
}