package com.example.hotelbooking.hotelListp.model;

public class HotelsOutfit {
    private String status;
    private String message;
    private HotelList data;

    public HotelsOutfit(String status, String message, HotelList data) {
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

    public HotelList getData() {
        return data;
    }

    public void setData(HotelList data) {
        this.data = data;
    }
}
