package com.example.hotelbooking.order.model;

import com.example.hotelbooking.order.model.Order;

import java.util.List;

public class OrderResponse {
    private String status;
    private String message;
    private List<Order> data;

    public OrderResponse(String status, String message, List<Order> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Order> getData() {
        return data;
    }
}
