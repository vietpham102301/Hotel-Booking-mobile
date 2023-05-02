package com.example.hotelbooking.hotelList.model;

public class HotelsOutfit {
    private String status;

    public HotelsOutfit(String status, String message, HotelList datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
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

    public HotelList getDatas() {
        return datas;
    }

    public void setDatas(HotelList datas) {
        this.datas = datas;
    }

    private String message;
    private HotelList datas;



}
