package com.example.hotelbooking.hotelListp.api;

import com.example.hotelbooking.hotelListp.model.HotelsOutfit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("api/v1/hotels")
    Call<HotelsOutfit> getHotelList(@Query("prv") String prv,
                                 @Query("ci") String ci,
                                 @Query("co") String co,
                                    @Query("ht") String ht,
                                    @Query("rt") String rt,
                                    @Query("pri") String pri);
}
