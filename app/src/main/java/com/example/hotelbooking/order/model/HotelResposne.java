package com.example.hotelbooking.order.model;

public class HotelResposne {
    private String status;
    private String message;
    private Hotel data;

    public HotelResposne(String status, String message, Hotel data) {
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
