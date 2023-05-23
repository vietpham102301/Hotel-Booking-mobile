package com.example.hotelbooking.hotelinformation.model;

import java.util.List;

public class ImageOutFit {
    private String status;
    private String message;
    private List<Images> data;

    public ImageOutFit(String status, String message, List<Images> data) {
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

    public List<Images> getData() {
        return data;
    }

    public void setData(List<Images> data) {
        this.data = data;
    }
}
