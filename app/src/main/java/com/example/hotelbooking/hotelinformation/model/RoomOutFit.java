package com.example.hotelbooking.hotelinformation.model;

public class RoomOutFit {
    private String status;
    private String message;
    private Room data;

    public RoomOutFit(String status, String message, Room data) {
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

    public Room getData() {
        return data;
    }

    public void setData(Room data) {
        this.data = data;
    }
}
