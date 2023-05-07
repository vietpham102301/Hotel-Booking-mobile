package com.example.hotelbooking.homepage.api;

import static com.example.hotelbooking.filter.api.ApiService.gson;

import com.example.hotelbooking.filter.api.ApiService;
import com.example.hotelbooking.homepage.HomepageApiResponse;
import com.example.hotelbooking.homepage.model.Homepage;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiHomepage {
    @GET("/booking/api/v1/provinces")
    Call<HomepageApiResponse> getHomepage();

//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl("http://14.225.255.238/booking/")
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);
}
