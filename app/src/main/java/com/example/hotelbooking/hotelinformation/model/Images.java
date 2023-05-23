package com.example.hotelbooking.hotelinformation.model;

public class Images {
    private int hotelId;
    private int imageId;
    private String url;

    public Images(int hotelId, int imageId, String url) {
        this.hotelId = hotelId;
        this.imageId = imageId;
        this.url = url;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
