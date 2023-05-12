package com.example.hotelbooking.hotelinformation.model;

public class CommentsOutfit {
    private String status;
    private String message;
    private CommentList data;

    public CommentsOutfit(String status, String message, CommentList data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getSratus() {
        return status;
    }

    public void setSratus(String sratus) {
        this.status = sratus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommentList getData() {
        return data;
    }

    public void setData(CommentList data) {
        this.data = data;
    }
}
