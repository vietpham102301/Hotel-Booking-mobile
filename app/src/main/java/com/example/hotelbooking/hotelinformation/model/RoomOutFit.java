package com.example.hotelbooking.hotelinformation.model;

import java.util.List;

public class RoomOutFit {
    private String status;
    private String message;
    private List<Room> data;

    public RoomOutFit(String status, String message, List<Room> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Room> getData() {
        return data;
    }

    public void setData(List<Room> data) {
        this.data = data;
    }
}
