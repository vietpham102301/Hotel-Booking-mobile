package com.example.hotelbooking.filter.model;

import java.util.List;

public class ProvicesOutFit {
    private String status;
    private String message;

    public List<Province> getData() {
        return data;
    }

    public void setData(List<Province> data) {
        this.data = data;
    }

    private List<Province> data;

    public ProvicesOutFit(String status, String message, List<Province> data) {
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


}
