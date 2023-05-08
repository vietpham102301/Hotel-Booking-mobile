package com.example.hotelbooking.hotelinformation.model;

public class Comments {
    private int id;
    private String modifyTime;
    private int rating;
    private String comment;
    private String name;
    private String avatar;
    private String gender;

    public Comments(int id, String modifyTime, int rating, String comment, String name, String avatar, String gender) {
        this.id = id;
        this.modifyTime = modifyTime;
        this.rating = rating;
        this.comment = comment;
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
