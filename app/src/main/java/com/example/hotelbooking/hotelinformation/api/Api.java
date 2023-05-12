package com.example.hotelbooking.hotelinformation.api;

import com.example.hotelbooking.hotelinformation.model.CommentsOutfit;
import com.example.hotelbooking.hotelinformation.model.HotelOutfit;
import com.example.hotelbooking.hotelinformation.model.RoomOutFit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @GET("api/v1/hotels/{id}")
    Call<HotelOutfit> getHotelInformation(@Path("id") int id);

    @GET("api/v1/room-type/hotel/{id}")
    Call<RoomOutFit> getRoomInHotel(@Path("id") int id,
                                    @Query("checkin") String checkin,
                                    @Query("checkout") String checkout);

    @GET("api/v1/hotels/{id}/comments")
    Call<CommentsOutfit> getCommentsHotel(@Path("id") int id,
                                          @Query("page") int page,
                                          @Query("size") int size);

//    @GET("api/v1/room-type/hotel/1?checkin=2023-04-16&checkout=2023-04-20")
//    Call<RoomOutFit> getRoomInHotel1();
}
