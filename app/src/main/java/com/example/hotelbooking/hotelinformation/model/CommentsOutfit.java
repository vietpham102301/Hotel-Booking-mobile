package com.example.hotelbooking.hotelinformation.model;

public class CommentsOutfit {
    private String sratus;
    private String message;
    private CommentList data;

    public CommentsOutfit(String sratus, String message, CommentList data) {
        this.sratus = sratus;
        this.message = message;
        this.data = data;
    }

    public String getSratus() {
        return sratus;
    }

    public void setSratus(String sratus) {
        this.sratus = sratus;
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
