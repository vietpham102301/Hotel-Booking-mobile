package com.example.hotelbooking.hotelList.api;

import com.example.hotelbooking.hotelList.model.Hotels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

// link Api :http://14.225.255.238/booking/api/v1/hotels
public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://14.225.255.238/booking/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/v1/hotels/{id}")
    Call<Hotels> hotelCardView(@Path("id") int id);
    @GET("api/v1/hotels")
    Call<List<Hotels>> hotelList();

}
