package com.example.hotelbooking.homepage.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Homepage {
    private String id;
    private String name;
    @SerializedName("avatar")
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
