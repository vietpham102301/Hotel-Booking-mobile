package com.example.hotelbooking.homepage.api;

import com.example.hotelbooking.homepage.model.HomepageListApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHomepage {
    @GET("api/v1/provinces")
    Call<HomepageListApiResponse> getHomepage();
}
