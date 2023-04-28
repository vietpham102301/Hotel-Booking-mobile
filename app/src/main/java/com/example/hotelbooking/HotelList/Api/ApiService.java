package com.example.hotelbooking.HotelList.Api;

import com.example.hotelbooking.HotelList.Model.Hotels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

}
