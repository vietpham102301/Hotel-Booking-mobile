package com.example.hotelbooking.order.model;

public class OrderDetail {
    private int hotelOrderId;
    private int roomTypeId;
    private Double price;
    private int quantity;

    public OrderDetail(int hotelOrderId, int roomTypeId, Double price, int quantity) {
        this.hotelOrderId = hotelOrderId;
        this.roomTypeId = roomTypeId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getHotelOrderId() {
        return hotelOrderId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

}
