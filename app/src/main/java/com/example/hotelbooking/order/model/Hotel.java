package com.example.hotelbooking.order.model;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String checkin;
    private String checkout;
    private double lat;
    private double lon;
    private double priceMin;
    private double priceMax;
    private String description;
    private String avatar;
    private double rating;
    private Integer numRating;
    private int userId;
    private String typeHotelId;
    private String provinceId;
    private List<RoomType> roomTypes;
    private boolean active;

    public Hotel(int id, String name, String address, String phone, String checkin, String checkout, double lat, double lon, double priceMin, double priceMax, String description, String avatar, double rating, int numRating, int userId, String typeHotelId, String provinceId, List<RoomType> roomTypes, boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.checkin = checkin;
        this.checkout = checkout;
        this.lat = lat;
        this.lon = lon;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.description = description;
        this.avatar = avatar;
        this.rating = rating;
        this.numRating = numRating;
        this.userId = userId;
        this.typeHotelId = typeHotelId;
        this.provinceId = provinceId;
        this.roomTypes = roomTypes;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(double priceMax) {
        this.priceMax = priceMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTypeHotelId() {
        return typeHotelId;
    }

    public void setTypeHotelId(String typeHotelId) {
        this.typeHotelId = typeHotelId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public List<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomType> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

