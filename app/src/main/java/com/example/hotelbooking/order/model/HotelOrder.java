package com.example.hotelbooking.order.model;

public class HotelOrder {
    private String hotelName;
    private double rating;
    private String roomType;
    private String checkIn;
    private String checkOut;
    private String hotelImg;

    public HotelOrder(String hotelName, double rating, String roomType, String checkIn, String checkOut, String hotelImg) {
        this.hotelName = hotelName;
        this.rating = rating;
        this.roomType = roomType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotelImg = hotelImg;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getHotelImg() {
        return hotelImg;
    }

    public void setHotelImg(String hotelImg) {
        this.hotelImg = hotelImg;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public String toString() {
        return "HotelOrder{" +
                "hotelName='" + hotelName + '\'' +
                ", rating=" + rating +
                ", roomType='" + roomType + '\'' +
                ", checkIn='" + checkIn + '\'' +
                ", checkOut='" + checkOut + '\'' +
                ", hotelImg='" + hotelImg + '\'' +
                '}';
    }
}
