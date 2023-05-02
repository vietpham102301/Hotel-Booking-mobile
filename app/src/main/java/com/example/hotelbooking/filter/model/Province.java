package com.example.hotelbooking.filter.model;

public class Province {
    private String id;
    private String name;
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

    public Province(String id, String name, String avatar) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
    }


}
