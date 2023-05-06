package com.example.hotelbooking.hotelinformation.model;

public class HotelOutfit {
    private String status;
    private String message;
    private Hotel data;

    public HotelOutfit(String status, String message, Hotel data) {
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

    public Hotel getData() {
        return data;
    }

    public void setData(Hotel data) {
        this.data = data;
    }
}
