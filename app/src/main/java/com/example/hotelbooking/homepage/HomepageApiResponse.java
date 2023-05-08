package com.example.hotelbooking.homepage;

import com.example.hotelbooking.homepage.model.Homepage;

import java.util.ArrayList;

public class HomepageApiResponse {
    private boolean status;
    private String message;
    private ArrayList<Homepage> data;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Homepage> getData() {
        return data;
    }
}