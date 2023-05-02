package com.example.hotelbooking.hotelList.model;

public class RoomTypes {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String avatar;
    private boolean isActive;
    private int hotelId;
    private Hotel hotel;

    public RoomTypes(int id, String name, double price, int quantity, String avatar, boolean isActive, int hotelId, Hotel hotel) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.avatar = avatar;
        this.isActive = isActive;
        this.hotelId = hotelId;
        this.hotel = hotel;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


}
