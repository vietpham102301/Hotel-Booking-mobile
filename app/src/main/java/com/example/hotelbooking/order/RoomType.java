package com.example.hotelbooking.order;

public class RoomType {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String avatar;
    private int hotelId;
    private boolean active;


    public RoomType(int id, String name, double price, int quantity, String avatar, int hotelId, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.avatar = avatar;
        this.hotelId = hotelId;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getHotelId() {
        return hotelId;
    }

    public boolean isActive() {
        return active;
    }
}
