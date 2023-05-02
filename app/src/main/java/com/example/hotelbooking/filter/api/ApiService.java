package com.example.hotelbooking.filter.api;

import com.example.hotelbooking.filter.model.ProvicesOutFit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

// link Api :http://14.225.255.238/booking/api/v1/provinces
public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://14.225.255.238/booking/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("api/v1/provinces")
    Call<ProvicesOutFit> provinces();
}
