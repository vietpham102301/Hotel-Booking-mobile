package com.example.hotelbooking.hotelList.api;

import com.example.hotelbooking.hotelList.model.HotelsOutfit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

// link Api :http://14.225.255.238/booking/api/v1/hotels
public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://14.225.255.238/booking/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("api/v1/hotels/{id}")
    Call<HotelsOutfit> hotelCardView(@Path("id") int id);
    @GET("api/v1/hotels")
    Call<HotelsOutfit> hotelList(@Query("prv") String prv,
                                 @Query("ci") String ci,
                                 @Query("co") String co);

}
